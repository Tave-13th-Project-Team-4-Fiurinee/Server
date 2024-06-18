package com.example.fiurinee.domain.anniversary.service;

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

    public Anniversary addAnniversary(Long memberId, LocalDate date, String type) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        AnniversaryType anniversaryType;
        try {
            anniversaryType = AnniversaryType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid anniversary type");
        }

        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.of("UTC"));
        Timestamp timestamp = Timestamp.from(zonedDateTime.toInstant());

        Anniversary anniversary = Anniversary.builder()
                .anniversaryDate(timestamp)
                .type(anniversaryType)
                .member(member)
                .build();
        anniversaryRepository.save(anniversary);
        return anniversary;
    }

}
