package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeProcessorTest {

    CommandProcessor passTimeProcessor;
    Bank bank;
    int age;
    double balance;
    Account checking;
    Account savings;
    Account cd;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        passTimeProcessor = new PassTimeProcessor(bank);

        checking = new Checking("12345678", 2.5);
        checking.deposit(50);
        savings = new Savings("11223344", 5.5);
        savings.deposit(400);
        cd = new CD("11110000", 8, 1000);
    }

    @Test
    public void pass_time_ages_accounts() {
        bank.addAccount(checking);
        String command = "pass 5";
        passTimeProcessor.process(command);
        age = bank.getAccountByID("12345678").getAge();
        assertEquals(5, age);
    }

    @Test
    public void pass_time_increases_savings_account_balance_based_on_the_apr() {
        bank.addAccount(savings);
        String command = "pass 3";
        passTimeProcessor.process(command);
        balance = bank.getAccountByID("11223344").getBalance();
        assertEquals(405.5252468460648, balance);
    }

    @Test
    public void pass_time_increases_cd_account_balance_based_on_the_apr() {
        bank.addAccount(cd);
        String command = "pass 3";
        passTimeProcessor.process(command);
        balance = bank.getAccountByID("11110000").getBalance();
        assertEquals(1082.9995068075111, balance);
    }

    @Test
    public void pass_time_causes_checking_to_lose_money_since_its_balance_is_less_than_100() {
        bank.addAccount(checking);
        String command = "pass 3";
        passTimeProcessor.process(command);
        balance = bank.getAccountByID("12345678").getBalance();
        assertEquals(30.187890896267362, balance);
    }

    @Test
    public void pass_time_command_is_not_caps_sensitive() {
        bank.addAccount(checking);
        String command = "PaSs 10";
        passTimeProcessor.process(command);
        age = bank.getAccountByID("12345678").getAge();
        assertEquals(10, age);
    }
}
