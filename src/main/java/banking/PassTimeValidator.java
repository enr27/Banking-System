package banking;

public class PassTimeValidator extends CommandValidator {
    public PassTimeValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String stringToValidate) {

        if (missingBank()) {
            return false;
        }

        String[] arguments = stringToValidate.split("\\s+");

        if (arguments.length == 2) {
            return validatePassTimeCommand(arguments);
        } else {
            return false;
        }
    }

    private boolean validatePassTimeCommand(String[] arguments) {

        if ("pass".equalsIgnoreCase(arguments[0]) && arguments[1].matches("\\d+")) {
            double time = Double.parseDouble(arguments[1]);
            if ((time >= 1) && (time <= 60)) {
                return true;
            }
        }
        return false;
    }
}
