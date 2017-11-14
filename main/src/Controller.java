import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private static LoginPanel mypanel;
    private Model_Read_Files readFile =  new Model_Read_Files();
    private Model_MgmtAccount account = new Model_MgmtAccount();

    void initializeScreen() {
        mypanel = new LoginPanel();
        mypanel.startPanel();
    }

    public void accountAccess(String username, String pass) {

    }

    public void addTransaction(Model_Transaction transaction) {

        account.addTransaction(transaction);
    }

    public void saveAccounts() throws IOException
    {
        readFile.saveAccounts(account);
    }

//    public void saveData() throws IOException
//    {
//        readFile.saveData();
//    }

    void removeTransaction(int index) {

        account.removeTransaction(index);
    }

    void loadData() throws FileNotFoundException {

        ArrayList<String[]> transactionArray = readFile.loadData();

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

        ArrayList<String[]> infoArray = readFile.loadAcctInfo();

        for(String[] acct : infoArray) {
            account.addAccount(acct);
        }
    }

    public Account getAccountInfo(String acctName) {
        return account.getAccountInfo(acctName);
    }

    public void newAccount(String name, String balance, String email, String description) {
        account.addAccount(new String[] {name, balance, email, description});
    }

    String[] getAcctNames() {
        return account.getAcctNames();
    }

    String[][] getTransactions() {
        return account.getTransactions();
    }

    void removeAccount(String acctName) {

        account.removeAccount(acctName);
    }
}