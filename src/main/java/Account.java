import java.util.List;

public abstract class Account {
    String id;
    double apr;
    double balance;
    int accountAge;
    List<String> accountHistory;

    abstract public String getAccountType();

    public String getID() {
        return id;
    }

    public double getAPR() {
        return apr;
    }

    public double getBalance() {
        return balance;
    }

    public int getAge() {
        return accountAge;
    }

    public void setAge(int newAccountAge) {
        accountAge = newAccountAge;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

    public void accrueInterest() {
        double initialBalance = getBalance();
        double newBalance = initialBalance;

        if (getAccountType().equalsIgnoreCase("cd")) {
            for (int i = 0; i < 4; i++) {
                newBalance += calculateMonthlyInterest(newBalance);
            }
        } else {
            newBalance += calculateMonthlyInterest(initialBalance);
        }
        setBalance(newBalance);
    }

    private double calculateMonthlyInterest(double balance) {
        double APR = getAPR();
        return balance * (APR / 100.0 / 12);
    }

    public void deposit(double amountDeposited) {
        balance += amountDeposited;
    }

    public void withdraw(double amountWithdrawn) {
        if (amountWithdrawn <= balance) {
            balance -= amountWithdrawn;
        } else {
            balance = 0;
        }
    }

    public String getAccountInfo() {
        String info = String.format("%s %s %.2f %.2f", getAccountType(), getID(), getBalance(), getAPR());
        return info;
    }

    public List<String> getAccountHistory() {
        return accountHistory;
    }

    public void addToAccountHistory(String command) {
        accountHistory.add(command);
    }
}
