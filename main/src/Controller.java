import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private static LoginPanel mypanel;
	  
    void initializeScreen() {
        mypanel = new LoginPanel();
        mypanel.startPanel();
    }
    
    public void accountAccess(String username, String pass) {
    
    }
    //the following methods need to be routed into classes (didn't have time :P)
    //this is garbage
	public String[] loadAccounts() throws FileNotFoundException {
		String[] accounts = new String[7];
		Scanner inFile = new Scanner(new File("main/resources/Accounts.txt")).useDelimiter("\n");
		int i = 0;
		while (inFile.hasNext()) {
		    String temp = inFile.nextLine();
		    String[] temp2 = temp.split(",");
		    accounts[i] = temp2[0];
		    i+=1;
		}
		return accounts;
	}

	public double loadBalances() throws FileNotFoundException {
		double[] balances = new double[7];
		Scanner inFile = new Scanner(new File("main/resources/Accounts.txt")).useDelimiter("\n");
		int i = 0;
		while (inFile.hasNext()) {
		    String temp = inFile.nextLine();
		    String[] temp2 = temp.split(",");
		    balances[i] = Double.parseDouble(temp2[1]);
		    i+=1;
		}
		double result = 0;
		   for (int j = 0; j < balances.length; j++)
		     result += balances[j];
		return result;
	}

	public String[][] loadData() throws FileNotFoundException {
		//need to not hard-code string array/array size...i'll fix later
		String[][] data = new String[8][8];
		Scanner inFile = new Scanner(new File("main/resources/Transactions.txt")).useDelimiter("\n");
		int i = 0;
		while (inFile.hasNext()) {
		    String temp = inFile.nextLine();
		    String[] temp2 = temp.split(",");
		    data[i] = temp2;
		    i+=1;
		}
		return data;
	}

	public String[] getAccountInfo() {
		String [] info = new String[3];
		Model_MgmtAccount account = new Model_MgmtAccount(info[0], info[1], info[2], info[3]);
		return account.getAll(info[0], info[1], info[2], info[3]);
	}
	
	public Model_MgmtAccount newAccount(String name, String balance, String email, String description) {
		Model_MgmtAccount account = new Model_MgmtAccount(name, balance, email, description);
		return account;
	}
}