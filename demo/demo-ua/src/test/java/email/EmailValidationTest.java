package email;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidationTest {

    @ParameterizedTest
    @CsvSource({
            "demo@gmail.com",
            "อีเมลทดสอบ@ยูเอทดสอบ.ไทย",
            "email-test@universal-acceptance-test.international",
            "email-test@universal-acceptance-test.קום",
            "电子邮件测试@普遍适用测试。我爱你",
            "email-test@xn-----ctdbabcfhu9c2b9l1acccr4c.xn--mgbah1a3hjkrd",
    })
    public void isValidWithRegex(String email) {
        EmailValidation validation = new EmailValidation();
        assertTrue(validation.isValidWithRegex(email));
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
    public void isValidWithRegex02(String email) {
        EmailValidation validation = new EmailValidation();
        assertTrue(validation.isValidWithRegex02(email));
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
    public void isValidWithJakartaMail(String email) {
        EmailValidation validation = new EmailValidation();
        assertTrue(validation.isValidWithJakartaMail(email));
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
    public void isValidWithApacheCommons(String email) {
        EmailValidation validation = new EmailValidation();
        assertTrue(validation.isValidWithApacheCommons(email));
    }
}