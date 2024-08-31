import java.util.ArrayList;

public class CD extends Account {

    public CD(String id, double apr, double cdStartingBalance) {
        super.id = id;
        super.apr = apr;
        super.balance = cdStartingBalance;
        super.accountHistory = new ArrayList<>();
    }

    @Override
    public String getAccountType() {
        return "Cd";
    }
}
