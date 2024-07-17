import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    private static final double APR = 4.2;
    private static final int UNIQUE_ID = 12345678;

    Account checking;

    @BeforeEach
    public void setUp() {
        checking = new Checking(APR, UNIQUE_ID);
    }

    @Test
    public void apr_created_with_supplied_value() {
        double actual = checking.getAPR();

        assertEquals(4.2, actual);
    }

    @Test
    public void unique_id_for_account() {
        double actual = checking.getUniqueID();

        assertEquals(12345678, actual);
    }

    @Test
    public void deposit_money_into_accounts() {
        checking.depositBalance(50);
        double actual = checking.getBalance();

        assertEquals(50, actual);
    }

    @Test
    public void withdraw_money_from_accounts() {
        checking.depositBalance(100);
        checking.withdrawBalance(60);
        double actual = checking.getBalance();

        assertEquals(40, actual);

    }

    @Test
    public void balance_goes_to_0_if_withdraw_goes_over_amount() {
        checking.depositBalance(10);
        checking.withdrawBalance(50);

        double actual = checking.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void deposit_twice_into_same_account() {
        checking.depositBalance(200);
        checking.depositBalance(300);
        double actual = checking.getBalance();

        assertEquals(500, actual);
    }

    @Test
    public void withdraw_money_twice_from_same_account() {
        checking.balance = 50;
        checking.withdrawBalance(25);
        checking.withdrawBalance(10);
        double actual = checking.getBalance();

        assertEquals(15, actual);
    }
}
