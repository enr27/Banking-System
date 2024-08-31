import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {
    CommandProcessor commandProcessor;
    Bank bank;
    Account account;


    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    public void valid_create_command_works_correctly() {
        String command = "create checking 12345678 1.0";
        commandProcessor.process(command);
        account = bank.getAccountByID("12345678");
        assertEquals("12345678", account.getID());
        assertEquals(1.0, account.getAPR());
    }

    @Test
    public void valid_deposit_command_works_correctly() {
        Account account = new Checking("12345678", 2.5);
        bank.addAccount(account);
        String command = "deposit 12345678 500";
        commandProcessor.process(command);
        account = bank.getAccountByID("12345678");
        assertEquals(500, account.getBalance());
    }

    @Test
    public void valid_withdraw_command_works_correctly() {
        Account account = new Checking("12345678", 2.5);
        bank.addAccount(account);
        account.deposit(5);
        String command = "withdraw 12345678 3";
        commandProcessor.process(command);
        account = bank.getAccountByID("12345678");
        assertEquals(2, account.getBalance());
    }

    @Test
    public void valid_transfer_command_works_correctly() {
        Account checking = new Checking("12345678", 2.5);
        bank.addAccount(checking);
        checking.deposit(100);
        Account savings = new Savings("11223344", 5.5);
        bank.addAccount(savings);
        String command = "transfer 12345678 11223344 25";
        commandProcessor.process(command);
        Account accountWithdrawn = bank.getAccountByID("12345678");
        Account accountDeposited = bank.getAccountByID("11223344");
        assertEquals(75, accountWithdrawn.getBalance());
        assertEquals(25, accountDeposited.getBalance());
    }

    @Test
    public void valid_pass_time_command_works_correctly() {
        Account checking = new Checking("12345678", 2.5);
        checking.deposit(150);
        bank.addAccount(checking);
        String command = "Pass 6";
        commandProcessor.process(command);
        int actualAge = bank.getAccountByID("12345678").getAge();
        assertEquals(6, actualAge);
    }
}
