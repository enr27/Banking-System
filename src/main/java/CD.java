public class CD extends Account {

    public CD(String id, double apr, double cdStartingBalance) {
        super.id = id;
        super.apr = apr;
        super.balance = cdStartingBalance;
    }

    @Override
    public String getAccountType() {
        return "CD";
    }
}
