
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    private static String CIPHER_TYPE = "AES/CBC/PKCS5PADDING";
    private static int BYTE_KEY_SIZE = 16;
    private static byte[] key;

    public static String Encrypt(String Finput, String key) {

        try {
            setKey(key);

            // Create secret key which determines how strings get encrypted
            SecretKeySpec aesKey = new SecretKeySpec(Encryption.key, "AES");

            // Create instance of a cipher and give it an algorithm to use
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

            // Initialize initial vector for cipher block chaining
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);

            // Initialize cipher in encrypt mode, and pass in the secret key
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParams);

            // Do the encryption
            byte[] encrypted = cipher.doFinal(Finput.getBytes("UTF-8"));

            // Encode and return encrypted string
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            System.out.println("Problem while encrypting message");
            e.printStackTrace();
        }
        return null;
    }

    public static String Decrypt (String Finput, String key) {
        try {
            setKey(key);

            // Create secret key which determines how strings get decrypted
            SecretKeySpec aesKey = new SecretKeySpec(Encryption.key, "AES");

            // Create instance of a cipher and give it an algorithim to use
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

            // Initialize initial vector for cipher block chaining
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);

            // Initialize cipher in decrypt mode, and pass in the secret key
            cipher.init(Cipher.DECRYPT_MODE, aesKey, ivParams);

            // Do the decryption
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(Finput));

            return new String(decrypted, "UTF-8");

        } catch (Exception e) {
            System.out.println("Problem while decrypting message");
            e.printStackTrace();
        }
        return null;
    }

    private static void setKey(String key) {
        Encryption.key = Arrays.copyOf(key.getBytes(), BYTE_KEY_SIZE);
    }
}