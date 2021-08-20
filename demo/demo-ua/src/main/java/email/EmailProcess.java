package email;

import com.ibm.icu.text.IDNA;
import com.ibm.icu.text.Normalizer2;
import domain.ValidateDomain;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

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
        String normalizedEmail = normalizeEmail(email);
        System.out.println(">>>>" + normalizedEmail);
        if(!isEmailValid(normalizedEmail)) {
            throw new RuntimeException("Email invalid after normalized");
        }

        // TODO Send Email

        return true;
    }

    public String normalizeEmail(String email) {
        // Normalize localpart
        String localpart = email.substring(0, email.lastIndexOf("@"));
        Normalizer2 normalizer2 = Normalizer2.getNFCInstance();
        String localpartNormalized = normalizer2.normalize(localpart);

        // Domain name
        String domain = email.substring(email.lastIndexOf("@") + 1);
        StringBuilder output = new StringBuilder();
        IDNA validator = IDNA.getUTS46Instance(
                IDNA.NONTRANSITIONAL_TO_ASCII
                        | IDNA.NONTRANSITIONAL_TO_UNICODE
                        | IDNA.CHECK_BIDI
                        | IDNA.CHECK_CONTEXTJ
                        | IDNA.CHECK_CONTEXTO
                        | IDNA.USE_STD3_RULES);
        IDNA.Info info = new IDNA.Info();

        validator.nameToASCII(domain, output, info);
        return localpartNormalized + "@" + output.toString();
    }

    public boolean isEmailValid(String email) {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

}
