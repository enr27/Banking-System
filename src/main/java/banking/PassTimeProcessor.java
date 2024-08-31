package banking;

import java.util.ArrayList;

public class PassTimeProcessor extends CommandProcessor {
    private int timePassed;

    public PassTimeProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void process(String command) {
        deductMinimumBalanceFees();
        deleteEmptyAccounts();

        String[] arguments = command.split("\\s+");
        timePassed = Integer.parseInt(arguments[1]);
        for (int i = 0; i < timePassed; i++) {
            calculateInterest();
        }
        ageOfAccount();
    }

    private void deductMinimumBalanceFees() {
        for (String accountID : bank.getAccounts().keySet()) {
            Account account = bank.getAccountByID(accountID);
            if (account.getBalance() < 100) {
                account.withdraw(20);
            }
        }
    }
    private void deleteEmptyAccounts() {
        ArrayList<String> emptyAccounts = new ArrayList<>();
        for (String accountID : bank.getAccounts().keySet()) {
            Account account = bank.getAccountByID(accountID);
            if (account.getBalance() == 0) {
                emptyAccounts.add(accountID);
            }
        }
        for (String accountID : emptyAccounts) {
            bank.closeAccount(accountID);
        }
    }

    private void calculateInterest() {
        for (String accountID : bank.getAccounts().keySet()) {
            Account account = bank.getAccountByID(accountID);
            account.accrueInterest();
        }
    }

    private void ageOfAccount() {
        for (String accountID : bank.getAccounts().keySet()) {
            Account account = bank.getAccountByID(accountID);
            int previousAge = account.getAge();
            int newAge = previousAge + timePassed;
            account.setAge(newAge);
        }
    }
}
