public abstract class Account {
    private double APR;
    protected double balance;
    private int uniqueID;

    public Account(double APR, int uniqueID) {
        this.APR = APR;
        this.balance = balance;
        this.uniqueID = uniqueID;
    }

    public double getAPR() {
        return APR;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public double getBalance() {
        return balance;
    }

    public void depositBalance(double addToBalance) {
        if (addToBalance >= 0) {
            balance += addToBalance;
        } else {
            System.out.print("You can only deposit a positive amount");
        }
    }

    public void withdrawBalance(double subtractFromBalance) {
        if (subtractFromBalance >= 0) {
            balance -= subtractFromBalance;
            if (balance < 0) {
                balance = 0;
            }
        } else {
            System.out.println("You can only withdraw a positive amount");
        }
    }
}
