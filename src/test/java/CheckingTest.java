import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingTest {

    private static final double APR = 4.2;
    private static final int UNIQUE_ID = 12345678;

    Checking checking;

    @BeforeEach
    public void setUp() {
        checking = new Checking(APR, UNIQUE_ID);
    }

    @Test
    public void checking_created_with_starting_balance_0() {
        double actual = checking.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void checking_apr_value() {
        double actual = checking.getAPR();

        assertEquals(4.2, actual);
    }
}
