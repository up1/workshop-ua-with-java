package email;

import com.ibm.icu.text.IDNA;
import com.ibm.icu.text.Normalizer2;
import domain.ValidateDomain;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;
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
        sendEmail(normalizedEmail);

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


    public void sendEmail(String email) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", "localhost");
            props.put("mail.mime.allowutf8", true);
            props.put("mail.smtp.port", 1025);

            Session session = Session.getInstance(props, null);

            MimeMessage msg = new MimeMessage(session);

            // Set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("testing-java@eai.com", "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse("testing-java@eai.com", false));

            msg.setSubject("X.com subscription!", "UTF-8");
            msg.setText("Congratulation, you are subscribed to X.com!", "UTF-8");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

            // Send
            Transport.send(msg);

            System.out.println("Email sent successfully!!");
        } catch (Exception e) {
            throw new RuntimeException("Can't send email");
        }
    }

}
