package com.example.fiurinee.domain.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceTest {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("qormoon@naver.com");
        message.setSubject("Test Email");
        message.setText("This is a test email from the application.");

        javaMailSender.send(message);
    }
}
