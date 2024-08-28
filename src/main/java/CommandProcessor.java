public class CommandProcessor {
    String accountID;
    double depositAmountForAccount;

    Bank bank;
    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void process(String command) {

        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);

        String validCommandType = command.split("\\s+")[0].toLowerCase();

        switch (validCommandType.toLowerCase()) {
            case "create":
                createProcessor.process(command);
                break;
            case "deposit":
                depositProcessor.process(command);
                bank.getAccountByID(depositProcessor.accountID);
                break;
            default:
                break;
        }
    }
}
