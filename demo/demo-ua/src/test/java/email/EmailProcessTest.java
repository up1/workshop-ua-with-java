package email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailProcessTest {

    @Test
    public void send() {
        EmailProcess emailProcess = new EmailProcess();
        assertTrue(emailProcess.send("demo@demo.com"));
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
    public void sendMoreEmail(String email) {
        EmailProcess emailProcess = new EmailProcess();
        assertTrue(emailProcess.send(email));
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
    void testSend(String email) {
        EmailProcess emailProcess = new EmailProcess();
        emailProcess.sendEmail(email);
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
    void testSendWithNormalize(String email) {
        EmailProcess emailProcess = new EmailProcess();
        String normalizedEmail = emailProcess.normalizeEmail(email);
        emailProcess.sendEmail(normalizedEmail);
    }
}