package banking;

public class TransferValidator extends CommandValidator {

    private final WithdrawValidator withdrawValidator;
    private final DepositValidator depositValidator;

    public TransferValidator(Bank bank) {
        super(bank);
        withdrawValidator = new WithdrawValidator(bank);
        depositValidator = new DepositValidator(bank);
    }

    @Override
    public boolean validate(String command) {
        if (missingBank()) {
            return false;
        }

        String[] arguments = command.split("\\s+");

        if (arguments.length == 4 && "transfer".equalsIgnoreCase(arguments[0])) {
            return validateTransferCommand(arguments);
        }
        return false;
    }

    private boolean validateTransferCommand(String[] arguments) {

        String fromAccountID = arguments[1];
        String toAccountID = arguments[2];
        String transferAmount = arguments[3];

        if (fromAccountID.equals(toAccountID)) {
            return false;
        }

        if (!toAccountID.matches("\\d{8}") || !fromAccountID.matches("\\d{8}")) {
            return false;
        }

        if (!transferAmount.matches("\\d*\\.?\\d+")) {
            return false;
        }

        return transfer(fromAccountID, toAccountID, transferAmount);
    }

    private boolean transfer(String fromAccountID, String toAccountID, String transferAmount) {

        String withdrawCommand = "withdraw " + fromAccountID + " " + transferAmount;
        String depositCommand = "deposit " + toAccountID + " " + transferAmount;

        boolean withdrawValid = withdrawValidator.validate(withdrawCommand);
        boolean depositValid = depositValidator.validate(depositCommand);

        return withdrawValid && depositValid;
    }
}
