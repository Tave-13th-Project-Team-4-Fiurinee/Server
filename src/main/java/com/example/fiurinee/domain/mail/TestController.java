package com.example.fiurinee.domain.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MailServiceTest mailServiceTest;

    @GetMapping("/sendEmail")
    public String sendEmail() {
        try {
            mailServiceTest.sendTestEmail();
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Email send failed: " + e.getMessage();
        }
    }
}
