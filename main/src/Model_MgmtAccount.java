
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Model_MgmtAccount {

    private String username;
    private String password;

    private HashMap<String, Account> subAccounts;
    private ArrayList<Model_Transaction> transactions;

    public Model_MgmtAccount(HashMap<String, Account> subAccounts, ArrayList<Model_Transaction> transactions) {
        this.subAccounts = subAccounts;
        this.transactions = transactions;
    }

    Model_MgmtAccount() {
        this.subAccounts = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    String[] getAcctNames() {

        Set<String> keys = subAccounts.keySet();
        return keys.toArray(new String[keys.size()]);
    }

    String[] getAvailableAccts() {

        Set<String> keys = subAccounts.keySet();
        String[] names = keys.toArray(new String[keys.size()]);
        ArrayList<String> availAccts = new ArrayList<>();

        for(String name : names) {
            if(!subAccounts.get(name).isRetired()) {
                availAccts.add(name);
            }
        }
        return availAccts.toArray(new String[availAccts.size()]);

    }

    Account getAccountInfo(String acctName) {
        return subAccounts.get(acctName);
    }

    double getAccountBalance(String acctName) {
        return subAccounts.get(acctName).getBalance();
    }

    String getEmail(String acctName) {
        return subAccounts.get(acctName).getEmail();
    }

    String getDescription(String acctName) {
        return subAccounts.get(acctName).getDescription();
    }

    String[][] getTransactionArray() {

        String[][] tmp = new String[transactions.size()][7];

        for(int i = 0; i < transactions.size(); i++) {
            tmp[i] = transactions.get(i).getTransactionInfo();
        }
        return tmp;
    }

    ArrayList<Model_Transaction> getTransactionList() {
        return transactions;
    }

    boolean isRetired(String name) {
        return subAccounts.get(name).isRetired();
    }

    public void setAccountBalance(String acctName, double newBalance) {
        subAccounts.get(acctName).setBalance(newBalance);
    }

    public void setEmail(String acctName, String newEmail) {
        subAccounts.get(acctName).setEmail(newEmail);
    }

    public void setDesc(String acctName, String desc) {
        subAccounts.get(acctName).setDesc(desc);
    }

    void addAccount(String[] info) {

        boolean isRetired = false;
        String newName = info[0];

        if(info[3].equalsIgnoreCase("True")) {
            isRetired = true;
        }

        // Create new account
        Account acct = new Account(info[0], info[1], info[2], isRetired);

        // Add account
        subAccounts.put(newName, acct);
    }

    void removeAccount (String acctName) {
        subAccounts.remove(acctName);
    }

    void addTransaction(Model_Transaction transaction) {

        // Add transaction to list
        transactions.add(transaction);

        // Update account
        double value = transaction.getGross();
        String acctName = transaction.getName();

        // If adding expense, should decrease balance
        if(transaction.getType().startsWith("E")) {
            value = -value;
        }
        subAccounts.get(acctName).modifyBalance(value);
    }

    void removeTransaction(Model_Transaction transaction) {

        // Get transaction and remove it.
        transactions.remove(transaction);

        // Update account
        double value = transaction.getGross();
        String acctName = transaction.getName();

        // If removing deposit, should decrease balance
        if(transaction.getType().startsWith("D")) {
            value = -value;
        }
        subAccounts.get(acctName).modifyBalance(value);
    }

    void retireAccount(String acctToRetire) {
        subAccounts.get(acctToRetire).setIsRetired(true);
    }
}
