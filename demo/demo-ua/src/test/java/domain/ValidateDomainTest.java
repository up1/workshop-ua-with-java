package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateDomainTest {

    @ParameterizedTest
    @CsvSource({
            "example.org",
            "example.undefinedtld",
            "example.recentTld",
            "example.accountants",
            "exâmple.org",
            "xn--exmple-xta.org",
            "exâmple.ไทย",
            "ไทยร่วมใจ.com",
            "exâmple.xn--o3cw4h",
            "xn--exmple-xta.xn--o3cw4h",
    })
    public void should_valid_domain_name_with_traditional(String domainName) {
        ValidateDomain validateDomain = new ValidateDomain();
        assertTrue(validateDomain.isValidWithTraditional(domainName));
    }

    @ParameterizedTest
    @CsvSource({
            "example.org",
            "example.undefinedtld",
            "example.recentTld",
            "example.accountants",
            "exâmple.org",
            "xn--exmple-xta.org",
            "exâmple.ไทย",
            "ไทยร่วมใจ.com",
            "exâmple.xn--o3cw4h",
            "xn--exmple-xta.xn--o3cw4h",
    })
    public void should_valid_domain_name_with_common_validator_library(String domainName) {
        ValidateDomain validateDomain = new ValidateDomain();
        assertTrue(validateDomain.isValidWithCommonValidator(domainName));
    }

    @ParameterizedTest
    @CsvSource({
            "example.org",
            "example.undefinedtld",
            "example.recentTld",
            "example.accountants",
            "exâmple.org",
            "xn--exmple-xta.org",
            "exâmple.ไทย",
            "ไทยร่วมใจ.com",
            "exâmple.xn--o3cw4h",
            "xn--exmple-xta.xn--o3cw4h",
    })
    public void should_valid_domain_name_with_icu(String domainName) {
        ValidateDomain validateDomain = new ValidateDomain();
        assertTrue(validateDomain.isValidWithICU4j(domainName));
    }

    @ParameterizedTest
    @CsvSource({
            "example.org, example.org",
            "example.undefinedtld, example.undefinedtld",
            "example.recentTld, example.recenttld",
            "example.accountants, example.accountants",
            "exâmple.org, xn--exmple-xta.org",
            "xn--exmple-xta.org, xn--exmple-xta.org",
            "exâmple.ไทย, xn--exmple-xta.xn--o3cw4h",
            "ไทยร่วมใจ.com, xn--82c3a4adfy1rc3b.com",
            "exâmple.xn--o3cw4h, xn--exmple-xta.xn--o3cw4h",
            "xn--exmple-xta.xn--o3cw4h, xn--exmple-xta.xn--o3cw4h",
    })
    void normalizeData(String domain, String expectedResult) {
        ValidateDomain validateDomain = new ValidateDomain();
        assertEquals(expectedResult, validateDomain.normalizeData(domain));
    }
}