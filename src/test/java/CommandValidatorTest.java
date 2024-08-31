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

    @Test
    public void valid_withdraw_command() {
        Account checking = new Checking("12345678", 2.5);
        bank.addAccount(checking);
        checking.deposit(100);
        String command = "withdraw 12345678 50";
        assertTrue(commandValidator.validate(command));
    }

    @Test
    public void invalid_withdraw_command() {
        Account checking = new Checking("12345678", 2.5);
        bank.addAccount(checking);
        checking.deposit(100);
        String command = "withdrawwwww 12345678 50";
        assertFalse(commandValidator.validate(command));
    }

    @Test
    public void valid_transfer_command() {
        Account checking = new Checking("12345678", 2.5);
        Account checking2 = new Checking("87654321", 3);
        bank.addAccount(checking);
        bank.addAccount(checking2);
        checking.deposit(125);
        String command = "transfer 12345678 87654321 25";
        assertTrue(commandValidator.validate(command));
    }

    @Test
    public void invalid_transfer_command() {
        Account checking = new Checking("12345678", 2.5);
        Account checking2 = new Checking("87654321", 3);
        bank.addAccount(checking);
        bank.addAccount(checking2);
        checking.deposit(125);
        String command = "transaction 12345678 87654321 25";
        assertFalse(commandValidator.validate(command));
    }

    @Test
    public void valid_pass_time_command() {
        String command = "pass 10";
        assertTrue(commandValidator.validate(command));
    }

    @Test
    public void invalid_pass_time_command() {
        String command = "pass 1000";
        assertFalse(commandValidator.validate(command));
    }
}
