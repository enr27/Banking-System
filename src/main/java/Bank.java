import java.util.HashMap;
import java.util.Map;

public class Bank {
    private static Map<Integer, Account> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public static Map<Integer, Account> getAccount() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.put(account.getUniqueID(), account);
    }

    public Account getAccount(int uniqueID) {
        return accounts.get(uniqueID);
    }

    public void deposit(int uniqueID, double addMoney) {
        Account account = getAccount(uniqueID);
        account.depositBalance(addMoney);
    }

    public void withdraw(int uniqueID, double subtractMoney) {
        Account account = getAccount(uniqueID);
        account.withdrawBalance(subtractMoney);
    }
}
