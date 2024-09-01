package banking;

public class WithdrawProcessor extends CommandProcessor {
    public WithdrawProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void process(String command) {

        String[] arguments = command.split("\\s+");
        this.accountID = arguments[1];
        this.withdrawAmountForAccount = Double.parseDouble(arguments[2]);

        bank.withdrawMoneyByID(withdrawAmountForAccount, accountID);
    }
}
