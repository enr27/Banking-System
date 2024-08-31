package banking;

public class WithdrawValidator extends CommandValidator {
    public WithdrawValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {

        if (missingBank()) {
            return false;
        }

        String[] arguments = command.split(" ");

        if (arguments.length == 3) {
            return validateWithdrawCommand(arguments);
        } else {
            return false;
        }
    }

    private boolean validateWithdrawCommand(String[] arguments) {

        if (!arguments[0].equalsIgnoreCase("withdraw")) {
            return false;
        }

        if (!arguments[1].matches("\\d{8}") || !arguments[2].matches("\\d*\\.?\\d+")) {
            return false;
        }

        if (getBank().getAccounts().isEmpty() || !getBank().getAccounts().containsKey(arguments[1])) {
            return false;
        }

        Account account = getBank().getAccountByID(arguments[1]);
        if (account.getAccountType().equalsIgnoreCase("cd")) {
            if (account.getAge() < 12) {
                return false;
            }
            return true;
        }

        double amount = Double.parseDouble(arguments[2]);
        if (validWithdrawAmount(account, amount)) {
            return true;
        }
        return false;
    }

    private boolean validWithdrawAmount(Account account, double amount) {
        switch (account.getAccountType().toLowerCase()) {
            case "savings":
            case "checking":
                return ((amount >= 0 && amount <= 1000));
            case "cd":
                return (amount >= account.getBalance());
            default:
                return false;
        }
    }
}
