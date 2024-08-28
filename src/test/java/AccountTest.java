import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
    private static final String ID = "12345678";
    private static final double APR = 2.5;
    private static final double BALANCE = 5000;

    @Test
    public void checking_account_works() {
        Checking account = new Checking(ID, APR);
        assertEquals(ID, account.getID());
        assertEquals(APR, account.getAPR());
    }

    @Test
    public void savings_account_works() {
        Savings account = new Savings(ID, APR);
        assertEquals(ID, account.getID());
        assertEquals(APR, account.getAPR());
    }

    @Test
    public void cd_account_works() {
        CD account = new CD(ID, APR, BALANCE);
        assertEquals(ID, account.getID());
        assertEquals(APR, account.getAPR());
        assertEquals(BALANCE, account.getBalance());
    }
}

