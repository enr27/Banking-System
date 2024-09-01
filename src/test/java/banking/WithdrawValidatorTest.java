package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WithdrawValidatorTest {

    WithdrawValidator withdrawValidator;
    Bank bank;
    Account checking;
    Account savings;
    Account cd;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        withdrawValidator = new WithdrawValidator(bank);
        checking = new Checking("12345678", 2.5);
        savings = new Savings("11223344", 5.5);
        cd = new CD("11111111", 6.5, 2000);
    }

    @Test
    public void command_that_is_empty_returns_false() {
        bank.addAccount(checking);
        String command = " ";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void withdraw_command_missing_id_and_amount_returns_false() {
        bank.addAccount(checking);
        String command = "withdraw";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void withdraw_command_missing_amount_returns_false() {
        bank.addAccount(checking);
        String command = "withdraw 12345678";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void typo_when_saying_withdraw_returns_false() {
        bank.addAccount(checking);
        String command = "with 12345678 1";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void invalid_id_returns_false() {
        bank.addAccount(checking);
        String command = "withdraw 1234567890 1";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void invalid_amount_returns_false() {
        bank.addAccount(checking);
        String command = "withdraw 12345678 -2";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_1000_from_checking_is_valid() {
        bank.addAccount(checking);
        bank.depositMoneyByID(1500, "12345678");
        String command = "withdraw 12345678 1000";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_more_than_1000_from_checking_is_invalid() {
        bank.addAccount(checking);
        bank.depositMoneyByID(1500, "12345678");
        String command = "withdraw 12345678 1400";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_1000_from_savings_is_valid() {
        bank.addAccount(savings);
        bank.depositMoneyByID(1500, "11223344");
        String command = "withdraw 11223344 1000";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_more_than_1000_from_savings_is_invalid() {
        bank.addAccount(savings);
        bank.depositMoneyByID(1500, "11223344");
        String command = "withdraw 11223344 1200";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void withdraw_command_is_not_caps_sensitive() {
        bank.addAccount(checking);
        bank.depositMoneyByID(2, "12345678");
        String command = "WithDRAW 12345678 1";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_from_cd_account_less_than_12_months_old_returns_false() {
        bank.addAccount(cd);
        String command = "withdraw 11111111 2000";
        assertFalse(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_from_cd_account_12_months_old_returns_true() {
        bank.addAccount(cd);
        cd.setAge(12);
        String command = "withdraw 11111111 2000";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_from_cd_account_older_than_12_months_returns_true() {
        bank.addAccount(cd);
        cd.setAge(15);
        String command = "withdraw 11111111 2000";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_more_than_balance_of_cd_account_returns_true() {
        bank.addAccount(cd);
        cd.setAge(12);
        String command = "withdraw 11111111 9000";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_0_from_checking_account_returns_true() {
        bank.addAccount(checking);
        bank.depositMoneyByID(200, "12345678");
        String command = "withdraw 12345678 0";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_between_0_and_1000_in_checking_account_returns_true() {
        bank.addAccount(checking);
        bank.depositMoneyByID(200, "12345678");
        String command = "withdraw 12345678 100";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_0_from_savings_account_returns_true() {
        bank.addAccount(savings);
        bank.depositMoneyByID(200, "11223344");
        String command = "withdraw 11223344 0";
        assertTrue(withdrawValidator.validate(command));
    }

    @Test
    public void withdrawing_between_0_and_1000_in_savings_account_returns_true() {
        bank.addAccount(savings);
        bank.depositMoneyByID(200, "11223344");
        String command = "withdraw 11223344 100";
        assertTrue(withdrawValidator.validate(command));
    }
}
