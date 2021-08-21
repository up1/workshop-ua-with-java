package com.example.uaspringboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SendEmailTest {

    @Autowired
    private SendEmail sendEmail;

    @Test
    public void sendEmail() {
        sendEmail.sendEmail("demo@target.com");
    }

    @ParameterizedTest
    @CsvSource({
            "demo@gmail.com",
            "อีเมลทดสอบ@ยูเอทดสอบ.ไทย",
            "email-test@universal-acceptance-test.international",
            "email-test@universal-acceptance-test.קום",
            "电子邮件测试@普遍适用测试。我爱你",
            "email-test@xn-----ctdbabcfhu9c2b9l1acccr4c.xn--mgbah1a3hjkrd",
    })
    public void sendEmails(String email) {
        sendEmail.sendEmail(email);
    }
}