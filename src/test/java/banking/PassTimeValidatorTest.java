package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidatorTest {

    PassTimeValidator passTimeValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        passTimeValidator = new PassTimeValidator(bank);
    }

    @Test
    public void empty_command_returns_false() {
        String command = " ";
        assertFalse(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_without_time_returns_false() {
        String command = "pass";
        assertFalse(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_without_pass_returns_false() {
        String command = "30";
        assertFalse(passTimeValidator.validate(command));
    }

    @Test
    public void typo_in_pass_returns_false() {
        String command = "pazz 30";
        assertFalse(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_with_invalid_time_returns_false() {
        String command = "pass 100";
        assertFalse(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_with_valid_time_returns_true() {
        String command = "pass 45";
        assertTrue(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_has_more_than_one_time_returns_false() {
        String command = "pass 15 20";
        assertFalse(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_is_not_caps_sensitive() {
        String command = "PaSs 5";
        assertTrue(passTimeValidator.validate(command));
    }

    @Test
    public void pass_command_with_time_0_returns_false() {
        String command = "pass 0";
        assertFalse(passTimeValidator.validate(command));
    }
}
