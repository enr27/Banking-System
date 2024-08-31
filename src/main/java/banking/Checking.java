package banking;

import java.util.ArrayList;

public class Checking extends Account {

    public Checking(String id, double apr) {
        super.id = id;
        super.apr = apr;
        super.balance = 0;
        super.accountHistory = new ArrayList<>();
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}
