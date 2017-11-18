
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_Read_Files {

    public void saveAccounts(Model_MgmtAccount accounts) throws IOException {
        // Set up FileWriter
        FileWriter writer;
        
        // Added a filepath for eclipse for future use
        //writer = new FileWriter("Accounts.txt");
        
        writer = new FileWriter("main/resources/Accounts.txt");

        // Get all accounts(not just retired ones)
        String[] names = accounts.getAcctNames();

        // Write out info for each account
        for(String name : names) {
            // Write Account info to file.
            writer.write(name + "," + accounts.getAccountBalance(name) + "," + accounts.getEmail(name)
                    + "," + accounts.getDescription(name) + "," + accounts.isRetired(name) + "\n");
        }
        writer.close();
    }

    public void saveData(ArrayList<Model_Transaction> transactions) throws IOException
    {
        // Set up FileWriter
        FileWriter writer;
        
        // Added a filepath for eclipse for future use
        //writer = new FileWriter("Transactions.txt");
        
        writer = new FileWriter("main/resources/Transactions.txt");

        // Write out info for each transaction
        for(Model_Transaction transaction : transactions) {
            // Write transaction info to file.
            writer.write(transaction.getName() + "," + transaction.getDate()+ "," + transaction.getGross() + ","
                    + transaction.getType() + "," + transaction.getCode() + "," + transaction.isDeposit() + "\n");

        }
        writer.close();
    }

    public ArrayList<String[]> loadData() throws FileNotFoundException {
        //need to not hard-code string array/array size...i'll fix later
        ArrayList<String[]> data = new ArrayList<>();
        // Added a filepath for eclipse for future use
        //Scanner inFile = new Scanner(new File("Transactions.txt")).useDelimiter("\n");
        
        Scanner inFile = new Scanner(new File("main/resources/Transactions.txt")).useDelimiter("\n");

        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            data.add(temp.split(","));
        }
        return data;
    }

    public ArrayList<String[]> loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();
        // Added a filepath for eclipse for future use
        //Scanner inFile = new Scanner(new File("Accounts.txt")).useDelimiter("\n");
        
        Scanner inFile = new Scanner(new File("main/resources/Accounts.txt")).useDelimiter("\n");
        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            acctInfo.add(temp.split(","));
        }
        return acctInfo;
    }
}
