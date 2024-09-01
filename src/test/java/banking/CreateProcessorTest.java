package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateProcessorTest {
    CommandProcessor commandProcessor;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandProcessor = new CreateProcessor(bank);
    }

    @Test
    public void new_checking_account_is_added_to_bank() {
        String command = "create checking 12345678 2.5";
        commandProcessor.process(command);
        Account account = bank.getAccountByID("12345678");
        assertEquals("12345678", account.getID());
        assertEquals(2.5, account.getAPR());
        assertEquals("Checking", account.getAccountType());
    }

    @Test
    public void new_savings_account_is_added_to_bank() {
        String command = "create savings 12345678 5.5";
        commandProcessor.process(command);
        Account account = bank.getAccountByID("12345678");
        assertEquals("12345678", account.getID());
        assertEquals(5.5, account.getAPR());
        assertEquals("Savings", account.getAccountType());
    }

    @Test
    public void new_cd_account_is_added_to_bank() {
        String command = "create cd 12345678 6.5 5000";
        commandProcessor.process(command);
        Account account = bank.getAccountByID("12345678");
        assertEquals("12345678", account.getID());
        assertEquals(6.5, account.getAPR());
        assertEquals("Cd", account.getAccountType());
    }
}
