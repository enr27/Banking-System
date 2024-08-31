package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositProcessorTest {

    CommandProcessor depositProcessor;
    Account checking;
    Account savings;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        depositProcessor = new DepositProcessor(bank);

        checking = new Checking("12345678", 2.5);
        checking.deposit(50);
        savings = new Savings("11223344", 5.5);
        savings.deposit(100);
    }

    @Test
    public void depositing_into_new_checking_account_works_correctly() {
        Account brandNewChecking = new Checking("87654321", 3);
        bank.addAccount(brandNewChecking);
        String command = "deposit 87654321 1000";
        depositProcessor.process(command);
        Account account = bank.getAccountByID("87654321");
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void depositing_into_new_savings_account_works_correctly() {
        Account brandNewSavings = new Savings("88776655", 5);
        bank.addAccount(brandNewSavings);
        String command = "deposit 88776655 5000";
        depositProcessor.process(command);
        Account account = bank.getAccountByID("88776655");
        assertEquals(5000, account.getBalance());
    }

    @Test
    public void depositing_into_existing_checking_account_that_already_has_money_works_correctly() {
        bank.addAccount(checking);
        String command = "deposit 12345678 500";
        depositProcessor.process(command);
        Account account = bank.getAccountByID("12345678");
        assertEquals(550, account.getBalance());
    }

    @Test
    public void depositing_into_existing_savings_account_that_already_has_money_works_correctly() {
        bank.addAccount(savings);
        String command = "deposit 11223344 900.50";
        depositProcessor.process(command);
        Account account = bank.getAccountByID("11223344");
        assertEquals(1000.50, account.getBalance());
    }
}
