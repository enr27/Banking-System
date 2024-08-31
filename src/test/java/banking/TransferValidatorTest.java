package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {

    CommandValidator transferValidator;
    Bank bank;
    Account checking;
    Account savings;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        transferValidator = new TransferValidator(bank);
        checking = new Checking("12345678", 2.5);
        savings = new Savings("11223344", 5.5);
    }

    @Test
    public void transfer_between_two_checking_accounts_returns_true() {
        Account checking2 = new Checking("87654321", 3);
        bank.addAccount((checking));
        bank.addAccount(checking2);
        String command = "transfer 12345678 87654321 100";
        assertTrue(transferValidator.validate(command));
    }

    @Test
    public void transfer_between_two_savings_accounts_returns_true() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer 11223344 11335577 100";
        assertTrue(transferValidator.validate(command));
    }

    @Test
    public void transfer_with_two_ids_missing_and_amount_missing_returns_false() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer";
        assertFalse(transferValidator.validate(command));
    }

    @Test
    public void transfer_missing_one_id_and_amount_returns_false() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer 11223344";
        assertFalse(transferValidator.validate(command));
    }

    @Test
    public void transfer_missing_amount_returns_false() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer 11223344 11335577";
        assertFalse(transferValidator.validate(command));
    }
    @Test
    public void transfer_command_is_not_caps_sensitive() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transFER 11223344 11335577 100";
        assertTrue(transferValidator.validate(command));
    }

    @Test
    public void transfer_between_checking_and_savings_returns_true() {
        bank.addAccount(checking);
        bank.addAccount(savings);
        String command = "transfer 12345678 11223344 5";
        assertTrue(transferValidator.validate(command));
    }

    @Test
    public void transfer_command_between_real_account_and_invalid_account_returns_false() {
        bank.addAccount(checking);
        bank.addAccount(savings);
        String command = "transfer 12345678 11110000 100";
        assertFalse(transferValidator.validate(command));
    }

    @Test
    public void transfer_between_an_account_and_itself_returns_false() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer 11223344 11223344 100";
        assertFalse(transferValidator.validate(command));
    }

    @Test
    public void transfer_with_negative_amount_returns_false() {
        Account savings2 = new Savings("11335577", 5.5);
        bank.addAccount(savings);
        bank.addAccount(savings2);
        String command = "transfer 11223344 11335577 -200";
        assertFalse(transferValidator.validate(command));
    }

}
