import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Model_Read_Files {

	    private static Model_Read_Files instance;
	    
	    private Model_Read_Files(){}
	    
	    public static synchronized Model_Read_Files getInstance(){
	        if(instance == null){
	            instance = new Model_Read_Files();
	        }
	        return instance;
	    }
	    
	public void saveAccounts(Model_MgmtAccount accounts) throws IOException
    {
        // Set up FileWriter
        FileWriter writer = null;

        writer = new FileWriter("main/resources/Accounts.txt");

        String[] names = accounts.getAcctNames();

        // Write out info for each account
        for(int i = 0; i < names.length ; i++)
        {
            // Write Account info to file.
            writer.write(names[i] + "," + accounts.getAccountBalance(names[i]) + "," + accounts.getEmail(names[i]) + "," + accounts.getDescription(names[i]) + "\n");

            // TODO: Popup message when save is complete
        }
        writer.close();
    }

//    public void saveData(ArrayList<Model_Transaction> transactions) throws IOException
//    {
//        // Set up FileWriter
//        FileWriter writer = null;
//
//        writer = new FileWriter("main/resources/Transactions.txt");
//
//        // Write out info for each transaction
//        for(int i = 0; i < transactions.size() ; i++)
//        {
//            // Write transaction info to file.
//            // TODO: Hash out what exactly should be saved and in what order
//            Model_Transaction currTransaction = transactions.get(i);
//            writer.write(currTransaction.getName() + "," + currTransaction.getType()+ "," + currTransaction.getCode() + ","
//                    + currTransaction.getNet() + "," + currTransaction.isDeposit(i) + "," +currTransaction.getGross() + "," + currTransaction.getDate() + ",\n");
//
//            // TODO: Popup message when save is complete
//
//        }
//
//        writer.close();
//    }

        public ArrayList<String[]> loadData() throws FileNotFoundException {
        //need to not hard-code string array/array size...i'll fix later
        ArrayList<String[]> data = new ArrayList<>();
        Scanner inFile = new Scanner(new File("main/resources/Transactions.txt")).useDelimiter("\n");

        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            data.add(temp.split(","));
        }
        return data;
    }

    public ArrayList<String[]> loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();
        Scanner inFile = new Scanner(new File("main/resources/Accounts.txt")).useDelimiter("\n");
        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            acctInfo.add(temp.split(","));
        }
        return acctInfo;
    }
}