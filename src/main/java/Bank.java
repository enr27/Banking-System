import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Object> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Object> getAccounts() {
        return accounts;
    }

    public Account getAccountByID(String retrievedID) {
        return (Account) accounts.get(retrievedID);
    }

    public void addAccount(Account account) {
        accounts.put(account.getID(), account);
    }

    public void closeAccount(Object account) {
        accounts.remove(account);
    }

    public void depositMoneyByID(double amount, String id) {
        Account account = getAccountByID(id);
        account.deposit(amount);
    }

    public void withdrawMoneyByID(double amount, String id) {
        Account account = getAccountByID(id);
        account.withdraw(amount);
    }
}
