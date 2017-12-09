import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private Model_Read_Files readFile =  new Model_Read_Files();
    private Model_MgmtAccount account = new Model_MgmtAccount();

    void initializeScreen() {
        LoginPanel mypanel = new LoginPanel();
        mypanel.startPanel();
    }

    public void accountAccess(String username, String pass) {

    }

    void saveAccounts() throws IOException {
        readFile.saveAccounts(account);
    }

    void saveData() throws IOException {
        readFile.saveData(account.getTransactionList());
    }

    void loadData() throws FileNotFoundException {

        ArrayList<String[]> transactionArray = readFile.loadEncryptedData();

        for(String[] transaction : transactionArray) {

            Model_Transaction newTransaction;
            int code = new Integer(transaction[4]);
            double amount = new Double(transaction[2]);

            // Decide type of transaction
            if(transaction[3].equalsIgnoreCase("Cash")) {

                newTransaction = new Model_Cash(transaction[3], transaction[0], code, transaction[5], amount, transaction[1]);
            }

            else if(transaction[3].equalsIgnoreCase("Credit Card")) {

                newTransaction = new Model_Credit(transaction[3], transaction[0], code, transaction[5], amount, transaction[1]);

            } else { // Check

                newTransaction = new Model_Check(transaction[3], transaction[0], code, transaction[5], amount, transaction[1]);
            }

            // Add transaction to model
            addTransaction(newTransaction);
        }
    }

    void loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> infoArray = readFile.loadEncryptedAcctInfo();

        for(String[] acct : infoArray) {
            account.addAccount(acct);
        }
    }

    Account getAccountInfo(String acctName) {
        return account.getAccountInfo(acctName);
    }

    String[] getAllAccounts() {
        return account.getAcctNames();
    }

    String[][] NamesAndBalances() {
        String[] accounts = account.getAcctNames();
        int numFields = 2;
        ArrayList<String[]> nameAndBalance = new ArrayList<>(accounts.length - 1);

        for(int i = 1; i < accounts.length; i ++) {
            nameAndBalance.add(account.nameAndBalance(accounts[i]));
        }
        return nameAndBalance.toArray(new String[accounts.length - 1][numFields]);
    }

    String[] getAvailableAccts() {
        return account.getAvailableAccts();
    }

    String[][] getTransactions() {
        return account.getTransactionArray();
    }

    void newAccount(String name, String email, String description, String phoneNumber) {
        account.addAccount(new String[] {name, email, description, "False", phoneNumber});
    }

    void removeAccount(String acctName) {
        account.removeAccount(acctName);
    }

    void retireAccount(String acctToRetire) {
        account.retireAccount(acctToRetire);
    }

    void addTransaction(Model_Transaction transaction) {
        account.addTransaction(transaction);
    }

    void removeTransaction(Model_Transaction transaction) {
        account.removeTransaction(transaction);
    }

    String getAcctEmail(String acctName) {
        return account.getEmail(acctName);
    }

    public void setEmail(String acctName, String newEmail) {
        account.setEmail(acctName, newEmail);
    }

    public void setDesc(String acctName, String newDesc) {
        account.setDesc(acctName, newDesc);
    }

    public void setNumber(String acctName, String newNumber) {
        account.setNumber(acctName, newNumber);
    }
}
