package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidatorTest {

    CreateValidator createValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        createValidator = new CreateValidator(bank);
    }

    @Test
    public void create_command_missing_everything_after_create_returns_false() {
        String command = "create";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void create_command_missing_id_and_apr_returns_false() {
        String command = "create checking";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void create_command_missing_apr_returns_false() {
        String command = "create checking 12345678";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void create_command_missing_starting_balance_when_creating_cd_returns_false() {
        String command = "create cd 12345678 6.5";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void typo_when_saying_create_returns_false() {
        String command = "idk checking 12345678 2.5";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void invalid_account_type_when_creating_returns_false() {
        String command = "create something 12345678 2.5";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void invalid_id_when_creating_returns_false() {
        String command = "create checking 1234567890 2.5";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void invalid_apr_when_creating_returns_false() {
        String command = "create checking 12345678 12.5";
        assertFalse(createValidator.validate(command));
    }

    @Test
    public void create_and_account_type_are_not_caps_sensitive() {
        String command = "CREATE CHECKING 12345678 2.5";
        assertTrue(createValidator.validate(command));
    }
}
