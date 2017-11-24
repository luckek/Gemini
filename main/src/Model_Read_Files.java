
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_Read_Files {

    private String key = "This is a secret key";

    void saveAccounts(Model_MgmtAccount accounts) {
        // Set up FileWriter
        FileWriter writer = null;

        try {
            // Added a filepath for eclipse for future use
            //writer = new FileWriter("Accounts.txt");

            writer = new FileWriter("main/resources/Accounts.txt");

            String[] names = accounts.getAcctNames();

            StringBuilder sb = new StringBuilder();
            // Write out info for each account
            for (String name : names) {
                // Get Account info.
                String information = name + "," + accounts.getAccountBalance(name) + "," + accounts.getEmail(name) + "," + accounts.getDescription(name) + "\n";

                // Append information to string
                sb.append(information);
            }

            String encryptedStr = Encryption.Encrypt(sb.toString(), key);

            if (encryptedStr != null) {
                writer.write(encryptedStr);
                writer.flush();
            } else {
                System.out.println("Problem encrypting Accounts.txt.\n File not updated");
            }

        } catch (Exception e) {
            System.out.println("Problem loading Accounts.txt");
            e.printStackTrace();
        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException I) {
                I.printStackTrace();
            }
        }
    }

    void saveData(ArrayList<Model_Transaction> transactions) throws IOException {
        // Set up FileWriter
        FileWriter writer = null;
        
        // Added a filepath for eclipse for future use
        //writer = new FileWriter("Transactions.txt");
        
        writer = new FileWriter("main/resources/Transactions.txt");

        StringBuilder sb = new StringBuilder();

        // Write out info for each transaction
        for(Model_Transaction transaction : transactions) {

            // Get transaction info
            sb.append(transaction.getName() + "," + transaction.getDate()+ "," + transaction.getGross() + ","
                    + transaction.getType() + "," + transaction.getCode() + "," + transaction.isDeposit() + ",\n");
        }

        String encryptedString = Encryption.Encrypt(sb.toString(), key);

        if(encryptedString != null) {
            writer.write(encryptedString);
        } else {
            System.out.println("Problem encrypting Transactions.txt\nFile not updated");
        }
        writer.close();
    }

    ArrayList<String[]> loadEncryptedData() throws FileNotFoundException {
        ArrayList<String[]> data = new ArrayList<>();
        // Added a filepath for eclipse for future use
        //Scanner inFile = new Scanner(new File("Transactions.txt")).useDelimiter("\n");

        Scanner inFile = new Scanner(new File("main/resources/Transactions.txt"));

        String toDecrypt = inFile.nextLine();
        String decrypted = Encryption.Decrypt(toDecrypt, key);

        String[] tmp;

        if(decrypted != null) {
            tmp = decrypted.split("\n");
        } else {
            System.out.println("Problem decrypting file");
            tmp = new String[0];
        }

        for(String line : tmp) {
            data.add(line.split(","));
        }
        return data;
    }

    ArrayList<String[]> loadEncryptedAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();
        // Added a filepath for eclipse for future use
        //Scanner inFile = new Scanner(new File("Accounts.txt")).useDelimiter("\n");

        Scanner scan = null;

        try {

            // Read in file
            scan = new Scanner(new File("main/resources/Accounts.txt"));

            // Grab and decrypt full string
            String decrypted = Encryption.Decrypt(scan.nextLine(), key);

            String[] tmp;

            if(decrypted != null) {
                // Split into rows
                tmp = decrypted.split("\n");
            } else {
                System.out.println("Null decrypted string");
                tmp = new String[0];
            }

            // Split into tokens
            for(String line : tmp) {
                acctInfo.add(line.split(","));
            }

        } catch (Exception e) {
            System.out.println("Problem loading Accounts.txt");
            e.printStackTrace();
        } finally {
            if ( scan != null) {
                scan.close();
            }
        }
        return acctInfo;
    }

    // Kept around just in case problems occur
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
