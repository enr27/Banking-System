package banking;

public class TransferProcessor extends CommandProcessor {
    String accountID;
    String toAccountID;
    String transferAmount;
    String withdrawCommand;
    String depositCommand;

    public TransferProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void process(String command) {
        withdrawProcessor = new WithdrawProcessor(bank);
        depositProcessor = new DepositProcessor(bank);

        String[] arguments = command.split("\\s+");

        this.toAccountID = arguments[1];
        this.accountID = arguments[2];
        this.transferAmount = arguments[3];

        if (Double.parseDouble(transferAmount) > bank.getAccountByID(toAccountID).getBalance()) {
            transferAmount = String.valueOf((bank.getAccountByID(toAccountID).getBalance()));
        }

        withdrawCommand = String.join(" ", "withdraw", toAccountID, transferAmount);
        depositCommand = String.join(" ", "deposit", accountID, transferAmount);

        withdrawProcessor.process(withdrawCommand);
        depositProcessor.process(depositCommand);
    }
}
