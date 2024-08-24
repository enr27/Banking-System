public class DepositValidator extends CommandValidator {
    public DepositValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {

        if (missingBank()) {
            return false;
        }

        String[] arguments = command.split("\\s+");

        if (arguments.length == 3) {
            return createDepositCommand(arguments);
        } else {
            return false;
        }
    }

    private boolean createDepositCommand(String[] arguments) {

        Account account = getBank().getAccountByID(arguments[1]);
        if (account.getAccountType().equalsIgnoreCase("cd")) {
            return false;
        }

        if (!arguments[0].equalsIgnoreCase("deposit")) {
            return false;
        }

        if (!arguments[1].matches("\\d{8}") || !arguments[2].matches("\\d*\\.?\\d+")) {
            return false;
        }

        if (getBank().getAccounts().isEmpty() || !getBank().getAccounts().containsKey(arguments[1])) {
            return false;
        }

        double amount = Double.parseDouble(arguments[2]);
        if (validDepositAmount(account, amount)) {
            return true;
        }
        return false;
    }

    private boolean validDepositAmount(Account account, double amount) {
        switch (account.getAccountType().toLowerCase()) {
            case "checking":
            case "savings":
                return (amount >= 0 && amount <= 10000);
            default:
                return false;
        }
    }
}
