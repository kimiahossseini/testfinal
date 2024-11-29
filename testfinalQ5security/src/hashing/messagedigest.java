package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class messagedigest {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		//1)--------hashing-----------------------------
		String pass="123aQ";
		MessageDigest messagedigest=MessageDigest.getInstance("MD5");
		byte[] hashbyte=messagedigest.digest(pass.getBytes());
		String hashstring=Base64.getEncoder().encodeToString(hashbyte);
		System.out.println("hash pass by md5: "+ hashstring);
		
		
		//2)-----------hashing with salt-----------------------
		
		String input = "Hello, World!";
		byte[] salt = generatesalt(16);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		// Combine the salt and the input
		digest.update(salt);
		byte[] hashBytes = digest.digest(input.getBytes());
		String base64Salt = Base64.getEncoder().encodeToString(salt);
		String base64Hash = Base64.getEncoder().encodeToString(hashBytes);
		// Print the resulting salt and hash
		System.out.println("hash input with salt: " + base64Hash);
		System.out.println("Salt (Base64): " + base64Salt);
		
		//3)------hshing PBkDf2---------------------------------
		String pass2="256K";
		byte[] salt2=generatesalt(16);
		PBEKeySpec spec = new PBEKeySpec(pass2.toCharArray(), salt2,	10000, 256);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		
		byte[] hash = factory.generateSecret(spec).getEncoded();
		String hashpass2=Base64.getEncoder().encodeToString(hash);
		System.out.println("hasing by method PBKDf2: "+ hashpass2);
	
		
	}
	public static byte[] generatesalt(int length) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[length];
		secureRandom.nextBytes(salt);
		return salt;
	}

}





/**
 * https://www.cs.usfca.edu/~galles/visualization/OpenHash.html see animation
 * Use https://crackstation.net/ 482c811da5d5b4bc6d497ffa98491e38 -> password123
 */