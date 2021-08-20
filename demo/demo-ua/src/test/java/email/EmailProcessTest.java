package email;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailProcessTest {

    @Test
    public void send() {
        EmailProcess emailProcess = new EmailProcess();
        assertTrue(emailProcess.send("demo@demo.com"));
    }
}