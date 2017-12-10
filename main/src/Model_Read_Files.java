
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_Read_Files {

    private String key = "This is a secret key";

    private String accountsFile = "main/resources/Accounts.txt";
    private String transactionsFile = "main/resources/Transactions.txt";
    private String codeFile = "main/resources/Codes.txt";

    // For making jar / eclipse
//    private String accountsFile = "Accounts.txt";
//    private String transactionsFile = "Transactions.txt";
//    private String codeFile = "Codes.txt";

    void saveAccounts(Model_MgmtAccount accounts) {

        // Set up FileWriter
        try(FileWriter writer = new FileWriter(accountsFile)) {

            String[] names = accounts.getAcctNames();
            StringBuilder sb = new StringBuilder();

            // Write out info for each account
            for (String name : names) {
                // Get Account info.
                String information = name + "," + accounts.getEmail(name) + "," + accounts.getDescription(name) + ","
                        + accounts.isRetired(name) + "," + accounts.getPhoneNumber(name) + "\n";

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

        } catch (IOException e) {
            System.out.println("Problem loading Accounts.txt");
            e.printStackTrace();
        }
    }

    void saveData(ArrayList<Model_Transaction> transactions) throws IOException {

        // Setup file writer
        try(FileWriter writer = new FileWriter(transactionsFile)) {

            StringBuilder sb = new StringBuilder();

            // Write out info for each transaction
            for (Model_Transaction transaction : transactions) {

                // Get transaction info
                sb.append(transaction.getName() + "," + transaction.getDate() + "," + transaction.getGross() + ","
                        + transaction.getType() + "," + transaction.getCode() + "," + transaction.isDeposit() + "," + transaction.getDesc() + ",\n");
            }

            String encryptedString = Encryption.Encrypt(sb.toString(), key);

            if (encryptedString != null) {
                writer.write(encryptedString);
            } else {
                System.out.println("Problem encrypting Transactions.txt\nFile not updated");
            }
        } catch (IOException e) {
            System.out.println("Trouble saving Transactions.txt");
            e.printStackTrace();
        }
    }
  
    // Saving/loading codes from file
    void saveCode(String code, String isExpense) throws IOException {

        try(FileWriter writer = new FileWriter(codeFile, true)) {

            writer.write(code + "," + isExpense + "\n");

        } catch(IOException e) {
            System.out.println("Trouble saving codes");
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> loadCodes() {

        ArrayList<String> codes = new ArrayList<>();

        try (Scanner inFile = new Scanner(new File(codeFile))) {

            inFile.useDelimiter("\n");

            while (inFile.hasNext()) {
                String temp = inFile.nextLine();
                codes.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find Codes.txt");
            e.printStackTrace();
        }
        return codes;
    }
    
    ArrayList<String[]> loadEncryptedData() throws FileNotFoundException {

        ArrayList<String[]> data = new ArrayList<>();

        try(Scanner inFile = new Scanner(new File(transactionsFile))) {

            // Grab line & decrypt it
            String toDecrypt = inFile.nextLine();
            String decrypted = Encryption.Decrypt(toDecrypt, key);

            String[] tmp;

            if (decrypted != null) {
                tmp = decrypted.split("\n");
            } else {
                System.out.println("Problem decrypting file");
                tmp = new String[0];
            }

            for (String line : tmp) {
                data.add(line.split(","));
            }
        } catch(FileNotFoundException e) {
            System.out.println("Cannot find Transactions.txt");
            e.printStackTrace();
        }
        return data;
    }

    ArrayList<String[]> loadEncryptedAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();

        // Set up scanner
        try(Scanner scan = new Scanner(new File(accountsFile))) {

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
        }
        return acctInfo;
    }

    // Kept around just in case problems occur
    public ArrayList<String[]> loadData() throws FileNotFoundException {

        ArrayList<String[]> data = new ArrayList<>();
        Scanner inFile = new Scanner(new File(transactionsFile)).useDelimiter("\n");

        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            data.add(temp.split(","));
        }
        return data;
    }

    public ArrayList<String[]> loadAcctInfo() throws FileNotFoundException {

        ArrayList<String[]> acctInfo = new ArrayList<>();
        Scanner inFile = new Scanner(new File(accountsFile)).useDelimiter("\n");

        while (inFile.hasNext()) {
            String temp = inFile.nextLine();
            acctInfo.add(temp.split(","));
        }
        return acctInfo;
    }
}
