package domain;

import com.ibm.icu.text.IDNA;
import org.apache.commons.validator.routines.DomainValidator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.net.InetAddress.getAllByName;

public class ValidateDomain {

    public boolean isValidWithTraditional(String domainName) {
        try {
            getAllByName(domainName);
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValidWithCommonValidator(String domainName) {
        DomainValidator validator = DomainValidator.getInstance();
        return validator.isValid(domainName);
    }

    public boolean isValidWithICU4j(String domainName) {
        IDNA validator = IDNA.getUTS46Instance(
                IDNA.NONTRANSITIONAL_TO_ASCII
                        | IDNA.NONTRANSITIONAL_TO_UNICODE
                        | IDNA.CHECK_BIDI
                        | IDNA.CHECK_CONTEXTJ
                        | IDNA.CHECK_CONTEXTO
                        | IDNA.USE_STD3_RULES);
        StringBuilder output = new StringBuilder();
        IDNA.Info info = new IDNA.Info();
        validator.nameToASCII(domainName, output, info);
        return !info.hasErrors();
    }
}
