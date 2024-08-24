import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

    private static final String ID1 = "12345678";
    private static final String ID2 = "87654321";
    private static final double APR1 = 2.5;
    private static final double APR2 = 3.5;

    Account account;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void bank_is_created_with_no_accounts() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    public void add_one_account_to_bank() {
        account = new Checking(ID1, APR1);
        bank.addAccount(account);
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    public void add_two_accounts_to_bank() {
        account = new Checking(ID1, APR1);
        Account account2 = new Savings(ID2, APR2);
        bank.addAccount(account);
        bank.addAccount(account2);
        assertEquals(2, bank.getAccounts().size());
    }

    @Test
    public void deposit_into_checking_account_by_id_through_bank() {
        account = new Checking(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(1000, ID1);
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void withdraw_from_checking_account_by_id_through_bank() {
        account = new Checking(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(600, ID1);
        bank.withdrawMoneyByID(100, ID1);
        assertEquals(500, account.getBalance());
    }

    @Test
    public void deposit_into_savings_account_by_id_through_bank() {
        account = new Savings(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(800, ID1);
        assertEquals(800, account.getBalance());
    }

    @Test
    public void withdraw_from_savings_account_by_id_through_bank() {
        account = new Savings(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(700, ID1);
        bank.withdrawMoneyByID(200, ID1);
        assertEquals(500, account.getBalance());
    }

    @Test
    public void deposit_into_multiple_accounts_at_once() {
        account = new Checking(ID1, APR1);
        Account account2 = new Savings(ID2, APR2);
        bank.addAccount(account);
        bank.addAccount(account2);
        bank.depositMoneyByID(75, ID1);
        bank.depositMoneyByID(25, ID2);
        assertEquals(75, account.getBalance());
        assertEquals(25, account2.getBalance());
    }

    @Test
    public void withdraw_from_multiple_accounts_at_once() {
        account = new Checking(ID1, APR1);
        Account account2 = new Savings(ID2, APR2);
        bank.addAccount(account);
        bank.addAccount(account2);
        bank.depositMoneyByID(500, ID1);
        bank.depositMoneyByID(300, ID2);
        bank.withdrawMoneyByID(400, ID1);
        bank.withdrawMoneyByID(300, ID2);
        assertEquals(100, account.getBalance());
        assertEquals(0, account2.getBalance());
    }

    @Test
    public void deposit_multiple_times_into_same_account() {
        account = new Checking(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(1000, ID1);
        bank.depositMoneyByID(1000, ID1);
        assertEquals(2000, account.getBalance());
    }

    @Test
    public void withdraw_multiple_times_into_same_account() {
        account = new Checking(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(5000, ID1);
        bank.withdrawMoneyByID(50.5, ID1);
        bank.withdrawMoneyByID(49.5, ID1);
        assertEquals(4900, account.getBalance());
    }

    @Test
    public void account_returns_0_when_withdrawing_higher_amount_than_balance() {
        account = new Savings(ID1, APR1);
        bank.addAccount(account);
        bank.depositMoneyByID(50, ID1);
        bank.withdrawMoneyByID(100, ID1);
        assertEquals(0, account.getBalance());
    }
}
