import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {

    private static final double APR = 5.2;
    private static final int UNIQUE_ID = 12345678;

    Savings savings;

    @BeforeEach
    public void setUp() {
        savings = new Savings(APR, UNIQUE_ID);
    }

    @Test
    public void savings_created_with_starting_balance_0() {
        double actual = savings.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void savings_apr_value() {
        double actual = savings.getAPR();

        assertEquals(5.2, actual);
    }
}
