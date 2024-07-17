import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDTest {

    private static final double APR = 6.2;
    private static final int UNIQUE_ID = 12345678;
    private static final double BALANCE = 5000.25;

    CD cd;

    @BeforeEach
    public void setUp() {
        cd = new CD(APR, UNIQUE_ID, BALANCE);
    }

    @Test
    public void cd_specified_balance() {
        double actual = cd.getBalance();

        assertEquals(5000.25, actual);
    }

    @Test
    public void cd_apr_value() {
        double actual = cd.getAPR();

        assertEquals(6.2, actual);
    }
}
