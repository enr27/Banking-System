import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    public void empty_command_returns_false() {
        String command = " ";
        assertFalse(commandValidator.validate(command));
    }

    @Test
    public void valid_create_command() {
        String command = "create checking 12345678 2.5";
        assertTrue(commandValidator.validate(command));
    }

    @Test
    public void invalid_create_command() {
        String command = "createee checking 12345678 2.5";
        assertFalse(commandValidator.validate(command));
    }

    @Test
    public void valid_deposit_command() {
        Account checking = new Checking("12345678", 2.5);
        bank.addAccount(checking);
        String command = "deposit 12345678 500";
        assertTrue(commandValidator.validate(command));
    }

    @Test
    public void invalid_deposit_command() {
        Account checking = new Checking("12345678", 2.5);
        bank.addAccount(checking);
        String command = "diposid 12345678 500";
        assertFalse(commandValidator.validate(command));
    }
}
