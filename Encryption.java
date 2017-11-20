import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Encryption
{   
    //private static byte[] encrypted;
    //private static String encryptedText;
    //private static String decrypted;
    
    public static String Encrypt(String Finput) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        String input = Finput;
        String key = "Bar12345Bar54321";
        
        // Create secret key which determines how strings get encrypted
        SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
        
        // Create instance of a cipher and give it an algorithim to use
        Cipher cipher = Cipher.getInstance("AES");
        
        // Initialize cipher in a mode, and pass in the secret key
        cipher.init(cipher.ENCRYPT_MODE, aesKey);
        
        // do the encryption
        byte[] encrypted = cipher.doFinal(input.getBytes());
        
        // Convert to a string
        String encryptedText = DatatypeConverter.printBase64Binary(encrypted);
        
        return encryptedText;
    }
    
    public static String Decrypt (String Finput) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        String input = Finput;
        
        String key = "Bar12345Bar54321";
        
        // Create secret key which determines how strings get decrypted
        SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
        
        // Create instance of a cipher and give it an algorithim to use
        Cipher cipher = Cipher.getInstance("AES");
        
        // Initialize cipher in a mode, and pass in the secret key
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        
        // Do the decryption
        byte[] decrypted = cipher.doFinal(input.getBytes());
        
        // Convert to a string
        String decryptedText = DatatypeConverter.printBase64Binary(decrypted);
        
        // Alternate way of doing this along with static variables
        //encrypted = DatatypeConverter.parseBase64Binary(encryptedText);
        //decrypted = new String(cipher.doFinal(encrypted));
        
        return Finput;
    }
    
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        System.out.println(Encrypt("All,0.0,-,-,-"));
        System.out.println(Decrypt(Encrypt("WOESEBTo4TFSrYnV3TXhiA==")));
    }
}