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
