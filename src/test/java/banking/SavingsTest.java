package banking;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SavingsTest {
    private static final String ID = "12345678";
    private static final double APR = 5.5;

    Savings account;

    @BeforeEach
    public void setUp() {
        account = new Savings(ID, APR);
    }

    @Test
    public void savings_created_with_starting_balance_0() {
        double actual = account.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void savings_apr_value() {
        double actual = account.getAPR();

        assertEquals(APR, actual);
    }
}

