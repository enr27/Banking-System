public class CommandValidator {

    private final Bank bank;
    CreateValidator createValidator;
    DepositValidator depositValidator;

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

        return createValidator.validate(command) ||
                depositValidator.validate(command);
    }
}
