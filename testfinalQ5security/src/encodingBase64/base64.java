package encodingBase64;

import java.util.Base64;

public class base64 {

	public static void main(String[] args) {
		//-----------encoding----------------
		byte[] originalByteArray = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		String encoding=Base64.getEncoder().encodeToString(originalByteArray);
		System.out.println("your encoding: "+encoding);
		//------------decoding------------------
		byte[] decoding=Base64.getDecoder().decode(encoding);
		for (byte b : decoding) {
			System.out.println("decoding is: "+ b);
		}

	}

}
