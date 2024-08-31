package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferProcessorTest {

    CommandProcessor transferProcessor;
    Bank bank;
    Account checking;
    Account savings;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        transferProcessor = new TransferProcessor(bank);

        checking = new Checking("12345678", 2.5);
        checking.deposit(100);
        savings = new Savings("11223344", 5.5);
        savings.deposit(200);
    }

    @Test
    public void transfer_between_checking_accounts_works_correctly() {
        Account checking2 = new Checking("87654321", 2.5);
        bank.addAccount(checking);
        bank.addAccount(checking2);
        String command = "transfer 12345678 87654321 90";
        transferProcessor.process(command);

        Account account1 = bank.getAccountByID("12345678");
        Account account2 = bank.getAccountByID("87654321");
        assertEquals(10, account1.getBalance());
        assertEquals(90, account2.getBalance());
    }

    @Test
    public void transfer_between_savings_accounts_works_correctly() {
        Account savings2 = new Checking("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer 11223344 11335577 100";
        transferProcessor.process(command);

        Account account1 = bank.getAccountByID("11223344");
        Account account2 = bank.getAccountByID("11335577");
        assertEquals(100, account1.getBalance());
        assertEquals(100, account2.getBalance());
    }

    @Test
    public void can_transfer_between_checking_and_savings() {
        bank.addAccount(checking);
        bank.addAccount(savings);
        String command = "transfer 12345678 11223344 50";
        transferProcessor.process(command);

        Account account1 = bank.getAccountByID("12345678");
        Account account2 = bank.getAccountByID("11223344");
        assertEquals(50, account1.getBalance());
        assertEquals(250, account2.getBalance());
    }
}
