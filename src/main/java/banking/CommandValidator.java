package banking;

public class CommandValidator {

    private final Bank bank;
    CreateValidator createValidator;
    DepositValidator depositValidator;
    WithdrawValidator withdrawValidator;
    TransferValidator transferValidator;
    PassTimeValidator passTimeValidator;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return this.bank;
    }

    boolean missingBank() {
        return getBank() == null;
    }

    public boolean validate(String command) {

        createValidator = new CreateValidator(bank);
        depositValidator = new DepositValidator(bank);
        withdrawValidator = new WithdrawValidator(bank);
        transferValidator = new TransferValidator(bank);
        passTimeValidator = new PassTimeValidator(bank);

        return createValidator.validate(command) || depositValidator.validate(command)
                || withdrawValidator.validate(command) || transferValidator.validate(command)
                || passTimeValidator.validate(command);
    }
}
