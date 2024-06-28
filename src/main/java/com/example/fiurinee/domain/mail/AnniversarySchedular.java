package com.example.fiurinee.domain.mail;

import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.service.AnniversaryService;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnniversarySchedular {
    @Autowired
    private AnniversaryService anniversaryService;

    @Autowired
    private MailService mailService;

    @Autowired
    private MemberService memberService;

    @Scheduled(cron = "0 35 17 * * *")
    public void sendDDayZeroAnniversaryEmails() {
        List<Member> members = memberService.findAll();
        for (Member member : members) {
            List<Anniversary> anniversaries = member.getAnniversaries();
            for (Anniversary anniversary : anniversaries) {
                List<Map<String, Integer>> allDDays = anniversaryService.calculateDDay(anniversary);
                for (Map<String, Integer> dDay : allDDays) {
                    for (Map.Entry<String, Integer> entry : dDay.entrySet()) {
                        if (entry.getValue() == 0) {
                            try {
                                mailService.sendAnniversaryEmail(anniversary.getMember(), anniversary);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
