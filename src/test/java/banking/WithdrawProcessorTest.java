package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawProcessorTest {

    CommandProcessor withdrawProcessor;
    Bank bank;
    Account checking;
    Account savings;
    Account cd;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        withdrawProcessor = new WithdrawProcessor(bank);
        checking = new Checking("12345678", 2.5);
        checking.deposit(10);
        savings = new Savings("11223344", 5.5);
        savings.deposit(20);
        cd = new CD("11111111", 6.5, 30);
    }

    @Test
    public void withdrawing_from_checking_account_works_correctly() {
        bank.addAccount(checking);
        String command = "withdraw 12345678 7";
        withdrawProcessor.process(command);
        Account account = bank.getAccountByID("12345678");
        assertEquals(3, account.getBalance());
    }

    @Test
    public void withdrawing_from_savings_account_works_correctly() {
        bank.addAccount(savings);
        String command = "withdraw 11223344 5";
        withdrawProcessor.process(command);
        Account account = bank.getAccountByID("11223344");
        assertEquals(15, account.getBalance());
    }

    @Test
    public void withdrawing_from_cd_account_works_correctly() {
        bank.addAccount(cd);
        String command = "withdraw 11111111 25";
        withdrawProcessor.process(command);
        Account account = bank.getAccountByID("11111111");
        assertEquals(5, account.getBalance());
    }

    @Test
    public void withdrawing_more_than_balance_from_checking_account_returns_0() {
        bank.addAccount(checking);
        String command = "withdraw 12345678 50.50";
        withdrawProcessor.process(command);
        Account account = bank.getAccountByID("12345678");
        assertEquals(0, account.getBalance());
    }

    @Test
    public void withdrawing_more_than_balance_from_savings_account_returns_0() {
        bank.addAccount(savings);
        String command = "withdraw 11223344 60.50";
        withdrawProcessor.process(command);
        Account account = bank.getAccountByID("11223344");
        assertEquals(0, account.getBalance());
    }

    @Test
    public void withdrawing_more_than_balance_from_cd_account_returns_0() {
        bank.addAccount(cd);
        String command = "withdraw 11111111 70.50";
        withdrawProcessor.process(command);
        Account account = bank.getAccountByID("11111111");
        assertEquals(0, account.getBalance());
    }
}
