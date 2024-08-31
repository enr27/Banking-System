package banking;

public class CreateValidator extends CommandValidator {
    public CreateValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (missingBank()) {
            return false;
        }

        String[] arguments = command.split(" ");

        if (arguments.length == 4) {
            return validateCheckingOrSavingsAccount(arguments);
        } else if (arguments.length == 5) {
            return validateCDAccount(arguments);
        }
        return false;
    }

    private boolean validateCheckingOrSavingsAccount(String[] arguments) {
        if (arguments[0].equalsIgnoreCase("create")
                && (arguments[1].equalsIgnoreCase("checking") || arguments[1].equalsIgnoreCase("savings"))) {
            return validate(arguments[2], arguments[3], null);
        }
        return false;
    }

    private boolean validateCDAccount(String[] arguments) {
        if (arguments[0].equalsIgnoreCase("create") && arguments[1].equalsIgnoreCase("cd")) {
            return validate(arguments[2], arguments[3], arguments[4]);
        }
        return false;
    }

    private boolean validate(String accountID, String apr, String amount) {

        if (!accountID.matches("\\d{8}")) {
            return false;
        }

        if (!apr.matches("\\d*\\.?\\d+")) {
            return false;
        }

        double aprRange = Double.parseDouble(apr);
        if (aprRange < 0 || aprRange > 10) {
            return false;
        }

        if (amount != null) {
            double startingBalance;
            try {
                startingBalance = Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                return false;
            }
            if (startingBalance < 1000 || startingBalance > 10000) {
                return false;
            }
        }

        return getBank().getAccounts().isEmpty() || !getBank().getAccounts().containsKey(accountID);
    }
}
