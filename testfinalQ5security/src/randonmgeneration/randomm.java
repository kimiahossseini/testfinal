package randonmgeneration;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class randomm {

	public static void main(String[] args) {
		Random random=new Random();
		//create random integer--------------
		int randomint=random.nextInt();
		System.out.println("first random: "+ randomint);
		int radomintbounded=random.nextInt(100);
		System.out.println("second random bound: "+ radomintbounded);
		
		//create random double--------------
		double randomdouble=random.nextDouble();
		System.out.println("third random double: "+ randomdouble);
		
		//secure random ------------------------
		SecureRandom securerandom=new SecureRandom();
		int randomintsec=securerandom.nextInt();
		System.out.println("secure random: "+randomintsec);
		
		// Generate random bytes
				byte[] randomBytes = new byte[16]; // 16 bytes
				securerandom.nextBytes(randomBytes); // Fill the array with random bytes
	    System.out.println("Random Bytes (Base64): " + Base64.getEncoder().encodeToString(randomBytes));
		
	 // Generate a random long
	 		long randomLong = securerandom.nextLong(); // Random long value
	 		System.out.println("Random Long: " + randomLong);

	}

}
