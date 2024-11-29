package encryptiondecryption2;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAencryption {

	public static void main(String[] args) {
		try {
            // Generate RSA Key Pair
            KeyPair keyPair = generateRSAKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

          // Encrypt the message using the Public Key
            String originalMessage = "Hello, this is a secret message!";
            String encryptedMessage = encrypt(originalMessage, publicKey);
            System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message using the Private Key
            String decryptedMessage = decrypt(encryptedMessage, privateKey);
            System.out.println("Decrypted Message: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	    // 1. Generate RSA Key Pair (Public and Private Key)
	    public static KeyPair generateRSAKeyPair() throws Exception {
	        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	        keyPairGen.initialize(2048); // Key size (2048 bits for secure RSA)
	        return keyPairGen.generateKeyPair();
	    }

	    // 2. Encrypt a message using the Public Key
	    public static String encrypt(String message, PublicKey publicKey) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64 for readability
	    }

	    // 3. Decrypt a message using the Private Key
	    public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage); // Decode from Base64
	        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	        return new String(decryptedBytes); // Convert to String
	    }


}

