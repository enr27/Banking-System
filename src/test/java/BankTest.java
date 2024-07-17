import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void bank_is_created_with_no_accounts() {
        bank = new Bank();

        assertTrue(Bank.getAccount().isEmpty());
    }

    @Test
    public void add_one_account_to_bank() {
        bank.addAccount(new Checking(4.2, 12345678));

        int actual = bank.getAccount().size();

        assertEquals(1, actual);
    }

    @Test
    public void add_two_accounts_to_bank() {
        bank.addAccount(new Checking(4.2, 12345678));
        bank.addAccount(new Savings(6.2, 87654321));

        int actual = bank.getAccount().size();

        assertEquals(2, actual);
    }

    @Test
    public void retrieve_one_account_from_bank_and_the_correct_account_is_retrieved() {
        Checking checkingAccount = new Checking(4.2, 11223344);
        bank.addAccount(checkingAccount);

        Account accountRetrieved = bank.getAccount(11223344);

        assertEquals(checkingAccount, accountRetrieved);
    }

    @Test
    public void deposit_money_by_id_through_bank_and_the_correct_account_gets_the_money() {
        bank.addAccount(new Checking(4.2, 19283746));
        bank.deposit(19283746, 1000);

        Account accountRetrieved = bank.getAccount(19283746);

        assertEquals(1000, accountRetrieved.getBalance());
    }

    @Test
    public void withdraw_money_by_id_through_bank_and_the_correct_account_loses_the_money() {
        bank.addAccount(new Checking(4.2, 19283746));
        bank.deposit(19283746, 500);
        bank.withdraw(19283746, 400);

        Account accountRetrieved = bank.getAccount(19283746);

        assertEquals(100, accountRetrieved.getBalance());
    }

    @Test
    public void deposit_twice_through_bank() {
        bank.addAccount(new Checking(4.2, 19283746));
        bank.deposit(19283746, 100.25);
        bank.deposit(19283746, 101.24);

        Account accountRetrieved = bank.getAccount(19283746);

        assertEquals(201.49, accountRetrieved.getBalance());
    }

    @Test
    public void withdraw_twice_through_bank() {
        bank.addAccount(new Checking(4.2, 19283746));
        bank.deposit(19283746, 2000);
        bank.withdraw(19283746, 500.25);
        bank.withdraw(19283746, 400.24);

        Account accountRetrieved = bank.getAccount(19283746);

        assertEquals(1099.51, accountRetrieved.getBalance());
    }
}
