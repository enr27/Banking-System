package banking;

public class CommandProcessor {
    String accountID;
    double depositAmountForAccount;
    double withdrawAmountForAccount;
    Bank bank;

    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;
    WithdrawProcessor withdrawProcessor;
    TransferProcessor transferProcessor;
    PassTimeProcessor passTimeProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void process(String command) {

        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);
        withdrawProcessor = new WithdrawProcessor(bank);
        transferProcessor = new TransferProcessor(bank);
        passTimeProcessor = new PassTimeProcessor(bank);

        String validCommandType = command.split("\\s+")[0].toLowerCase();

        switch (validCommandType.toLowerCase()) {
            case "create":
                createProcessor.process(command);
                break;
            case "deposit":
                depositProcessor.process(command);
                bank.getAccountByID(depositProcessor.accountID).addToAccountHistory(command);
                break;
            case "withdraw":
                withdrawProcessor.process(command);
                bank.getAccountByID(withdrawProcessor.accountID).addToAccountHistory(command);
                break;
            case "transfer":
                transferProcessor.process(command);
                bank.getAccountByID(transferProcessor.accountID).addToAccountHistory(command);
                break;
            case "pass":
                passTimeProcessor.process(command);
                break;
            default:
                break;
            }
    }
}
