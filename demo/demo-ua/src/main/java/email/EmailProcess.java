package email;

import domain.ValidateDomain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailProcess {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile(".*@+.*\\..*");

    public boolean send(String email) {

        // 1. Validate email
        EmailValidation emailValidation = new EmailValidation();
        if(!emailValidation.isValidWithApacheCommons(email)) {
            throw new RuntimeException("Email invalid");
        }

        // 2. Validate Domain name from email
        String domain = email.substring(email.lastIndexOf("@") + 1);
        ValidateDomain validateDomain = new ValidateDomain();
        if(!validateDomain.isValidWithICU4j(domain)) {
            throw new RuntimeException("Domain invalid");
        }

        // 3. Send email
        return true;
    }

    public boolean isEmailValid(String email) {
        try {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.find();
        } catch (Exception e) {
            return false;
        }
    }

}
