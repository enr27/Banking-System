package banking;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CDTest {
    private static final String ID = "12345678";
    private static final double APR = 6.5;
    private static final double BALANCE = 5000;

    CD account;

    @BeforeEach
    public void setUp() {
        account = new CD(ID, APR, BALANCE);
    }

    @Test
    public void cd_specified_balance() {
        double actual = account.getBalance();

        assertEquals(BALANCE, actual);
    }

    @Test
    public void cd_apr_value() {
        double actual = account.getAPR();

        assertEquals(APR, actual);
    }
}
