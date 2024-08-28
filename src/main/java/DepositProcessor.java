public class DepositProcessor extends CommandProcessor{
    public DepositProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void process(String command) {

        String[] arguments = command.split("\\s+");
        this.accountID = arguments[1];
        this.depositAmountForAccount = Double.parseDouble(arguments[2]);

        bank.depositMoneyByID(depositAmountForAccount, accountID);
    }
}
