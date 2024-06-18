package com.example.fiurinee.domain.anniversary.service;

import com.example.fiurinee.domain.anniversary.dto.AnniversaryRequestDTO;
import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.entity.AnniversaryType;
import com.example.fiurinee.domain.anniversary.repository.AnniversaryRepository;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class AnniversaryService {
    @Autowired
    private AnniversaryRepository anniversaryRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Anniversary addAnniversary(Long memberId, AnniversaryRequestDTO requestDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        AnniversaryType anniversaryType;
        try {
            anniversaryType = AnniversaryType.valueOf(requestDTO.getType());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid anniversary type");
        }

        ZonedDateTime zonedDateTime = requestDTO.getDate().atStartOfDay(ZoneId.of("UTC"));
        Timestamp timestamp = Timestamp.from(zonedDateTime.toInstant());

        Anniversary anniversary = Anniversary.builder()
                .anniversaryDate(timestamp)
                .type(anniversaryType)
                .member(member)
                .build();
        anniversaryRepository.save(anniversary);
        return anniversary;
    }

    public Anniversary updateAnniversary(Long memberId, Long anniversaryId, AnniversaryRequestDTO requestDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Anniversary anniversary = anniversaryRepository.findById(anniversaryId).orElseThrow(() -> new IllegalArgumentException("Invalid anniversary ID"));

        if (!anniversary.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("Anniversary does not belong to the member");
        }

        validateAnniversaryType(requestDTO.getType());

        ZonedDateTime zonedDateTime = requestDTO.getDate().atStartOfDay(ZoneId.of("UTC"));
        Timestamp timestamp = Timestamp.from(zonedDateTime.toInstant());

        anniversary.setAnniversaryDate(timestamp);
        anniversary.setType(AnniversaryType.valueOf(requestDTO.getType()));
        anniversaryRepository.save(anniversary);
        return anniversary;
    }

    public void deleteAnniversary(Long memberId, Long anniversaryId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Anniversary anniversary = anniversaryRepository.findById(anniversaryId).orElseThrow(() -> new IllegalArgumentException("Invalid anniversary ID"));

        if (!anniversary.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("Anniversary does not belong to the member");
        }

        anniversaryRepository.delete(anniversary);
    }

    private void validateAnniversaryType(String type) {
        if (!type.matches("생일|연인|배우자|가족|기타")) {
            throw new IllegalArgumentException("Invalid anniversary type");
        }
    }


}
