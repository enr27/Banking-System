import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {
    DepositValidator depositValidator;
    Bank bank;
    Account checking;
    Account savings;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        depositValidator = new DepositValidator(bank);
        checking = new Checking("12345678", 2.5);
        savings = new Savings("11223344", 5.5);
    }

    @Test
    public void deposit_into_existing_checking_account() {
        bank.addAccount(checking);
        String command = "deposit 12345678 50";
        assertTrue(depositValidator.validate(command));
    }

    @Test
    public void deposit_into_existing_savings_account() {
        bank.addAccount(savings);
        String command = "deposit 11223344 50";
        assertTrue(depositValidator.validate(command));
    }

    @Test
    public void depositing_more_than_2500_dollars_into_checking_account_returns_false() {
        bank.addAccount(checking);
        String command = "deposit 12345678 2510";
        assertFalse(depositValidator.validate(command));
    }

    @Test
    public void depositing_less_than_0_dollars_into_checking_account_returns_false() {
        bank.addAccount(checking);
        String command = "deposit 12345678 -10";
        assertFalse(depositValidator.validate(command));
    }

    @Test
    public void depositing_more_than_2500_dollars_into_savings_account_returns_false() {
        bank.addAccount(savings);
        String command = "deposit 11223344 10000";
        assertFalse(depositValidator.validate(command));
    }

    @Test
    public void depositing_less_than_0_dollars_into_savings_account_returns_false() {
        bank.addAccount(savings);
        String command = "deposit 11223344 -20";
        assertFalse(depositValidator.validate(command));
    }

    @Test
    public void deposit_is_not_caps_sensitive() {
        bank.addAccount(checking);
        String command = "DEPOSIt 12345678 100";
        assertTrue(depositValidator.validate(command));
    }
}
