import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_Read_Files {

    Controller controller = new Controller();
    
    public void saveAccounts() throws IOException
    {
        // Set up FileWriter
        FileWriter writer = null;
        
        writer = new FileWriter("main/resources/Accounts.txt");

        String[] names = (String[]) controller.account.getKeySet().toArray();
        
        // Write out info for each account
        for(int i = 0; i < names.length ; i++)
        {
            // Write Account info to file.
            writer.write(controller.account.getAccountName(names[i]) + "," + controller.account.getAccountBalance(names[i]) + "," + controller.account.getEmail(names[i]) + "," + controller.account.getDescription(names[i]) + ",\n");
            
            // TODO: Popup message when save is complete
            
        }
          
          writer.close();
    }
    
    
    public void saveData() throws IOException
    {
        // Set up FileWriter
        FileWriter writer = null;
        
        writer = new FileWriter("main/resources/Transactions.txt");

        // Write out info for each transaction
        for(int i = 0; i < controller.account.getNumTransactions() ; i++)
        {
            // Write transaction info to file.
            // TODO: Hash out what exactly should be saved and in what order
            writer.write(controller.account.getName(i) + "," + controller.account.getType(i) + "," + controller.account.getCode(i) + ","
            + controller.account.getNet(i) + "," + controller.account.getCD(i) + "," + controller.account.getGross(i) + "," + controller.account.getDate(i) + ",\n");
            
            // TODO: Popup message when save is complete
            
        }
          
        writer.close();
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

    public ArrayList<String[]> loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();
        Scanner inFile = new Scanner(new File("main/resources/Accounts.txt")).useDelimiter("\n");
        int i = 0;
        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            acctInfo.add(temp.split(","));
        }
        return acctInfo;

    }
}
