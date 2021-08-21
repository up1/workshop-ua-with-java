package com.example.uaspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@demo.com");
        message.setTo(email);
        message.setSubject("Hello");
        message.setText("Hello");
        emailSender.send(message);
    }

}


