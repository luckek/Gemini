
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

    String getAccountBalance(String acctName) {
        return subAccounts.get(acctName).getBalance();
    }

    String getEmail(String acctName) {
        return subAccounts.get(acctName).getEmail();
    }

    String getDescription(String acctName) {
        return subAccounts.get(acctName).getDescription();
    }

    String[][] getTransactions() {

        String[][] tmp = new String[transactions.size()][7];

        for(int i = 0; i < transactions.size(); i++) {
            tmp[i] = transactions.get(i).getAll();
        }
        return tmp;
    }

    ArrayList<Model_Transaction> getTransactionsList() {
        return transactions;
    }

    boolean isRetired(String name) {
        return subAccounts.get(name).isRetired();
    }

    public void setAccountBalance(String acctName, String newBalance) {

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

        if(info[4].equalsIgnoreCase("True")) {
            isRetired = true;
        }

        Account acct = new Account(info[0], info[1], info[2], info[3], isRetired);

        subAccounts.put(newName, acct);
    }

    void removeAccount (String acctName) {
        subAccounts.remove(acctName);
    }

    void addTransaction(Model_Transaction transaction) {
        transactions.add(transaction);
    }

    void removeTransaction(int index) {
        transactions.remove(index);
    }

    void retireAccount(String acctToRetire) {
        subAccounts.get(acctToRetire).setIsRetired(true);
    }
}
