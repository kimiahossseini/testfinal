package encryptiondecryption;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class symmetricencryption {

	public static void main(String[] args) throws Exception {
	SecretKey seck1=generatesecret("AES", 128);
	/*
	 * we should save seck1 in key store
	 * @see saveloadkeystore
	 */
	saveloadkeystore.saveKeyToKeyStore(seck1, "sec1", "keystorefile", "123");
	String encryptedmessage=encrypt("AES", "HEllo world", seck1);
	System.out.println("your message is encrypted: "+ encryptedmessage);
	
	SecretKey secretrive=saveloadkeystore.loadKeyFromKeyStore("sec1", "keystorefile", "123");
	String decryptmessage=decrypt("AES", encryptedmessage, secretrive);
	System.out.println("your message is decrypted: "+ decryptmessage);
	
	//-------------------------------------------------------------------
	String encryptedmessage2=encrypt("AES", "HELLO WORLD",secretKey );
	System.out.println("your message is encrypted with predefined key"+ encryptedmessage2);
	String decryptmessage2=decrypt("AES", encryptedmessage2, secretKey);
	System.out.println("your message is decrypted: "+ decryptmessage2);
	
	
	}
	
	
	//1)method generate key-------------------------------
	public static SecretKey generatesecret(String type,int level) throws NoSuchAlgorithmException {
			KeyGenerator keygen=KeyGenerator.getInstance(type);
			keygen.init(level);
			SecretKey secretkey=keygen.generateKey();
			return secretkey;
}
	     //1-1) predefined key-----------------------------------
			static String keyString = "MYSECRTKEY123456"; // 16 characters for AES-128
			static SecretKeySpec secretKey = new SecretKeySpec(keyString.getBytes(),"AES");
	
	
	//2)method encryption---------------------------------
	@SuppressWarnings("static-access")
	public static String encrypt(String type,String message, SecretKey secretkey) throws Exception {
		Cipher cipher=Cipher.getInstance(type);
		cipher.init(cipher.ENCRYPT_MODE, secretkey);
		byte[] encryptedbyte=cipher.doFinal(message.getBytes());
		return Base64.getEncoder().encodeToString(encryptedbyte);
	}
	//3)method decryption----------------------------------
	@SuppressWarnings("static-access")
	public static String decrypt(String type,String cmessage,SecretKey secretkey)throws Exception{
		Cipher cipher=Cipher.getInstance(type);
		cipher.init(cipher.DECRYPT_MODE, secretkey);
		byte[] decryptbyte=cipher.doFinal(Base64.getDecoder().decode(cmessage));
		
		//The decrypted byte array is converted to a String using the String constructor.
		return new String(decryptbyte);

		
	}
	

}
