public class Checking extends Account {

    public Checking(String id, double apr) {
        super.id = id;
        super.apr = apr;
        super.balance = 0;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}
