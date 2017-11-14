import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_Read_Files {

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

    public ArrayList<String[]> loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();
        Scanner inFile = new Scanner(new File("main/resources/Accounts.txt")).useDelimiter("\n");
        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            acctInfo.add(temp.split(","));
        }
        return acctInfo;
    }
    
    public ArrayList<String> loadCustomCodes() throws FileNotFoundException {
    	ArrayList<String> customCodes = new ArrayList<>();
    	Scanner inFile = new Scanner(new File("main/resources/Codes.txt"));
    	while (inFile.hasNextLine()) {
    		customCodes.add(inFile.next());
    	}
    	return customCodes;
    }
}
