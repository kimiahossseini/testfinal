package encryptiondecryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

import javax.crypto.SecretKey;

public class saveloadkeystore {
   //save a key in  key store--------------------------------------------------------------------------------
	public static void saveKeyToKeyStore(SecretKey secretKey, String keyAlias, String keyStoreFile, String keyStorePassword) throws Exception {
	    KeyStore keyStore = KeyStore.getInstance("JCEKS");
	    keyStore.load(null, null); // Initialize KeyStore
	    KeyStore.SecretKeyEntry entry = new KeyStore.SecretKeyEntry(secretKey);
	    KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());
	    keyStore.setEntry(keyAlias, entry, password);

	    try (FileOutputStream fos = new FileOutputStream(keyStoreFile)) {
	        keyStore.store(fos, keyStorePassword.toCharArray());
	    }
	    System.out.println("Secret key saved to KeyStore: " + keyStoreFile);
	}
	
	//loading a key from key store-------------------------------------------------------------------
	public static SecretKey loadKeyFromKeyStore(String keyAlias, String keyStoreFile, String keyStorePassword) throws Exception {
	    KeyStore keyStore = KeyStore.getInstance("JCEKS");
	    try (FileInputStream fis = new FileInputStream(keyStoreFile)) {
	        keyStore.load(fis, keyStorePassword.toCharArray());
	    }
	    return (SecretKey) keyStore.getKey(keyAlias, keyStorePassword.toCharArray());
	}
	
}
