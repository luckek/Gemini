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

    // TODO: test this method
    public void addAccount(String[] info) {

        String newName = info[0];
        Account acct = new Account(info[0], info[1], info[2], info[3]);

        subAccounts.put(newName, acct);
    }

    public void addTransaction(Model_Transaction transaction) {

        transactions.add(transaction);
    }
    
    public int getNumAccounts()
    {
    	return subAccounts.keySet().size();
    }
    
    public Set getKeySet()
    {
    	return subAccounts.keySet();
    }
    
    public String getAccountName(String acctName)
    {
    	return subAccounts.get(acctName).getName();
    }
    
    public int getNumTransactions()
    {
        return transactions.size();
    }
    
    public String getName(int index)
    {
        return transactions.get(index).getName();
    }
    
    public String getType(int index)
    {
        return transactions.get(index).getType();
    }
    
    public double getCode(int index)
    {
        return transactions.get(index).getCode();
    }
    
    public float getNet(int index)
    {
        return transactions.get(index).getNet();
    }
    
    public boolean getCD(int index)
    {
        return transactions.get(index).getCD();
    }
    
    public float getGross(int index)
    {
        return transactions.get(index).getGross();
    }
    
    public String getDate(int index)
    {
        return transactions.get(index).getDate();
    }
}
