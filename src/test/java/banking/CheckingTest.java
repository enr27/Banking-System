package banking;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CheckingTest {
    private static final String ID = "12345678";
    private static final double APR = 2.5;

    Checking account;

    @BeforeEach
    public void setUp() {
        account = new Checking(ID, APR);
    }

    @Test
    public void checking_created_with_starting_balance_0() {
        double actual = account.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void checking_apr_value() {
        double actual = account.getAPR();

        assertEquals(APR, actual);
    }
}
