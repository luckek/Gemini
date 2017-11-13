import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private static LoginPanel mypanel;
    private Model_Read_Files readFile =  new Model_Read_Files();
    protected Model_MgmtAccount account = new Model_MgmtAccount();
      
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
        readFile.saveAccounts();
    }
    
    public void saveData() throws IOException
    {
        readFile.saveData();
    }
    
    String[][] loadData() throws FileNotFoundException {

        return readFile.loadData();
    }

    String[] loadAccounts() throws FileNotFoundException {

        return readFile.loadAccounts();
    }

    void loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> infoArray = readFile.loadAcctInfo();

        for(String[] acct : infoArray) {
            System.out.println("Adding " + acct[0] + "'s information");
            account.addAccount(acct);
        }
    }

    public Account getAccountInfo(String acctName) {
        return account.getAccountInfo(acctName);
    }
    
    public void newAccount(String name, String balance, String email, String description) {
        account.addAccount(new String[] {name, balance, email, description});
    }
}