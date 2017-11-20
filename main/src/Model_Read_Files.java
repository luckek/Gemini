import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Model_Read_Files {

    public void saveAccounts(Model_MgmtAccount accounts) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {   
        // Set up FileWriter
        FileWriter writer = null;
        
        // Added a filepath for eclipse for future use
        //writer = new FileWriter("Accounts.txt");
        
        writer = new FileWriter("main/resources/Accounts.txt");

        String[] names = accounts.getAcctNames();

        // Write out info for each account
        for(int i = 0; i < names.length ; i++)
        {
            // Write Account info to file.
            String information = names[i] + "," + accounts.getAccountBalance(names[i]) + "," + accounts.getEmail(names[i]) + "," + accounts.getDescription(names[i]) + "\n";
            //Encrypt(information);
            writer.write(names[i] + "," + accounts.getAccountBalance(names[i]) + "," + accounts.getEmail(names[i]) + "," + accounts.getDescription(names[i]) + "\n");
        }
        writer.close();
    }

    public void saveData(ArrayList<Model_Transaction> transactions) throws IOException
    {
        // Set up FileWriter
        FileWriter writer = null;
        
        // Added a filepath for eclipse for future use
        //writer = new FileWriter("Transactions.txt");
        
        writer = new FileWriter("main/resources/Transactions.txt");

        // Write out info for each transaction
        for(int i = 0; i < transactions.size() ; i++)
        {
            // Write transaction info to file.
            Model_Transaction currTransaction = transactions.get(i);
            writer.write(currTransaction.getName() + "," + currTransaction.getDate()+ "," + currTransaction.getNet() + ","
                    + currTransaction.getType() + "," + currTransaction.getCode() + "," +currTransaction.isDeposit() + ",\n");
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

//    public String Encrypt (String Finput) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
//    {
//        String input = Finput;
//        String key = "Zyx98765Abc54321";
//        
//        SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        
//        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//        byte[] encrypted = cipher.doFinal(input.getBytes());
//        String encryptedText = DatatypeConverter.printBase64Binary(encrypted);
//        System.out.println(encryptedText);
//        
//        return encryptedText;
//    }
    
//    public String Decrypt(String Finput)
//    {
//        
//    }
        
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
