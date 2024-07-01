package com.example.fiurinee.domain.anniversary.service;

import com.example.fiurinee.domain.anniversary.dto.AnniversaryRequestDTO;
import com.example.fiurinee.domain.anniversary.dto.AnniversaryResponseDTO;
import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.entity.AnniversaryType;
import com.example.fiurinee.domain.anniversary.repository.AnniversaryRepository;
import com.example.fiurinee.domain.mail.MailService;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.repository.MemberRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnniversaryService {
    @Autowired
    private AnniversaryRepository anniversaryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MailService mailService;

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
                .name(requestDTO.getName())
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
        anniversary.setName(requestDTO.getName());
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


        public List<Map<String, Integer>> calculateDDay(Anniversary anniversary) {
            List<Map<String, Integer>> dDayList = new ArrayList<>();
            ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
            LocalDate today = LocalDate.now(koreaZoneId);
            LocalDateTime anniversaryDateTime = anniversary.getAnniversaryDate().toLocalDateTime();
            LocalDate anniversaryDate = anniversaryDateTime.toLocalDate(); //기념일의 날짜 부분만 추출
            long yearsDifference = ChronoUnit.YEARS.between(anniversaryDate, today); //기념일 날짜와 오늘 날짜 사이의 년 차이를 계산

            if (anniversary.getType() == AnniversaryType.연인) {
                int daysPassed = (int) ChronoUnit.DAYS.between(anniversaryDate, today); //기념일 이후 오늘까지 경과된 일 수를 계산
                int nextDay = ((daysPassed / 100) + 1) * 100;

                for (int i = 0; i < 2; i++) {
                    LocalDate hundredDays = anniversaryDate.plusDays(nextDay + i * 100);
                    if (!hundredDays.isBefore(today)) {
                        Map<String, Integer> dDay = new HashMap<>();
                        dDay.put((nextDay + i * 100) + "days", (int) ChronoUnit.DAYS.between(today, hundredDays) - 1);
                        dDayList.add(dDay);
                    }
                }
            }

            boolean isTodayAnniversary = false;
            for (int i = 1; i <= yearsDifference + 1; i++) {
                LocalDate yearAnniversary = anniversaryDate.plusYears(i);
                int daysBetween = (int) ChronoUnit.DAYS.between(today, yearAnniversary);
                if (daysBetween == 0) {
                    Map<String, Integer> dDay = new HashMap<>();
                    dDay.put("year", daysBetween);
                    dDayList.add(dDay);
                    isTodayAnniversary = true;
                    break;
                }
            }

            if (!isTodayAnniversary) {
                for (int i = 1; i <= yearsDifference + 1; i++) {
                    LocalDate yearAnniversary = anniversaryDate.plusYears(i);
                    if (!yearAnniversary.isBefore(today)) {
                        Map<String, Integer> dDay = new HashMap<>();
                        dDay.put("year", (int) ChronoUnit.DAYS.between(today, yearAnniversary));
                        dDayList.add(dDay);
                        break;
                    }
                }
            }

            return dDayList;
        }

    public List<AnniversaryResponseDTO> getDDayZeroAnniversaries(List<Anniversary> anniversaries) {
        List<AnniversaryResponseDTO> dDayZeroList = new ArrayList<>();

        for (Anniversary anniversary : anniversaries) {
            List<Map<String, Integer>> allDDays = calculateDDay(anniversary);
            List<Map<String, Integer>> zeroDDays = new ArrayList<>();

            for (Map<String, Integer> dDay : allDDays) {
                for (Map.Entry<String, Integer> entry : dDay.entrySet()) {
                    if (entry.getValue() == 0) {
                        Map<String, Integer> zeroDay = new HashMap<>();
                        zeroDay.put(entry.getKey(), entry.getValue());
                        zeroDDays.add(zeroDay);
                    }
                }
            }

            if (!zeroDDays.isEmpty()) {
                dDayZeroList.add(AnniversaryResponseDTO.of(anniversary, zeroDDays));
            }
        }

        if (dDayZeroList.isEmpty()) {
            dDayZeroList.add(AnniversaryResponseDTO.empty());
        }

        return dDayZeroList;
    }


}
