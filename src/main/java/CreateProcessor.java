public class CreateProcessor extends CommandProcessor {
    public CreateProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void process(String command) {

        String[] arguments = command.split("\\s+");

        if (arguments.length == 4) {
            if (arguments[1].equalsIgnoreCase("checking")) {
                processCheckingAccount(arguments[2], Double.parseDouble(arguments[3]));
            } else if (arguments[1].equalsIgnoreCase("savings")) {
                processSavingsAccount(arguments[2], Double.parseDouble(arguments[3]));
            }
        }

        if (arguments.length == 5) {
            processCDAccount(arguments[2], Double.parseDouble(arguments[3]), Double.parseDouble(arguments[4]));
        }
    }

    public void processCheckingAccount(String id, double apr) {
        Account account = new Checking(id, apr);
        bank.addAccount(account);
    }

    public void processSavingsAccount(String id, double apr) {
        Account account = new Savings(id, apr);
        bank.addAccount(account);
    }

    public void processCDAccount(String id, double apr, double amount) {
        Account account = new CD(id, apr, amount);
        bank.addAccount(account);
    }
}