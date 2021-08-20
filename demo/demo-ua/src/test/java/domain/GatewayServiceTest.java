package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GatewayServiceTest {

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
    public void callByDomain(String domainName) {
        GatewayService gatewayService = new GatewayService();
        assertTrue(gatewayService.callByDomain(domainName));
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
    public void callByDomain02(String domainName) {
        GatewayService gatewayService = new GatewayService();
        assertTrue(gatewayService.callByDomain02(domainName));
    }
}