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

    public Model_MgmtAccount() {
        this.subAccounts = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public String getAccountBalance(String acctName) {
        return subAccounts.get(acctName).getBalance();
    }

    public String getEmail(String acctName) {
        return subAccounts.get(acctName).getEmail();
    }

    public String getDescription(String acctName) {
        return subAccounts.get(acctName).getDescription();
    }

    public Account getAccountInfo(String acctName) {
        return subAccounts.get(acctName);
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

    public void addAccount(String[] info) {

        String newName = info[0];
        Account acct = new Account(info[0], info[1], info[2], info[3]);

        subAccounts.put(newName, acct);
    }

    void removeAccount (String acctName) {
        subAccounts.remove(acctName);
    }

    public void addTransaction(Model_Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(int index) {
        transactions.remove(index);
    }

    public String[] getAcctNames() {

        Set<String> keys = subAccounts.keySet();
        return keys.toArray(new String[keys.size()]);
    }

    public String[] getAvailableAccts() {

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

    public String[][] getTransactions() {

        String[][] tmp = new String[transactions.size()][7];

        for(int i = 0; i < transactions.size(); i++) {
            tmp[i] = transactions.get(i).getAll();
        }
        return tmp;
    }

    public ArrayList<Model_Transaction> getTransactionsList() {
        return transactions;
    }
}
