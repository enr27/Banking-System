import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
    private List<String> invalidCommands;
    private Bank bank;

    public CommandStorage(Bank bank) {
        this.bank = bank;
        this.invalidCommands = new ArrayList<>();
    }
    public List<String> getInvalidCommands() {
        return invalidCommands;
    }
    public void addInvalidCommand(String invalidCommand) {
        invalidCommands.add(invalidCommand);
    }
    public ArrayList<Object> getOutput() {
        ArrayList<Object> output = new ArrayList<>();

        for (String accountID: bank.getAccounts().keySet()) {
            Account account = bank.getAccountByID(accountID);
            output.add(account.getAccountInfo());
        }
        output.addAll(invalidCommands);
        return output;
    }
}
