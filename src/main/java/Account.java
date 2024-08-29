//this is a test
public abstract class Account {
    protected String id;
    protected double apr;
    protected double balance;
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
}
