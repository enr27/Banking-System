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
}
