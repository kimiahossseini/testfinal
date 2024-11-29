package encryptiondecryption2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class saveloadkeystorepair {

	 // 1. Save RSA Key Pair to KeyStore
    public static void saveKeyPairToKeyStore(KeyPair keyPair, String alias, String keyStoreFile, String keyStorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JCEKS"); // Use JCEKS for secret key support
        char[] passwordArray = keyStorePassword.toCharArray();

        // Create a self-signed certificate (required by KeyStore)
        X509Certificate certificate = generateSelfSignedCertificate(keyPair);

        // Initialize KeyStore (create new or load existing)
        try (FileInputStream fis = new FileInputStream(keyStoreFile)) {
            keyStore.load(fis, passwordArray);
        } catch (Exception e) {
            keyStore.load(null, passwordArray); // If KeyStore does not exist, create a new one
        }

        // Save the private key along with the certificate
        keyStore.setKeyEntry(alias, keyPair.getPrivate(), passwordArray, new Certificate[]{certificate});

        // Save the KeyStore to file
        try (FileOutputStream fos = new FileOutputStream(keyStoreFile)) {
            keyStore.store(fos, passwordArray);
        }

        System.out.println("KeyPair saved to KeyStore: " + keyStoreFile);
    }

    
    
    // 2. Load RSA Key Pair from KeyStore
    public static KeyPair loadKeyPairFromKeyStore(String alias, String keyStoreFile, String keyStorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        char[] passwordArray = keyStorePassword.toCharArray();

        // Load the KeyStore
        try (FileInputStream fis = new FileInputStream(keyStoreFile)) {
            keyStore.load(fis, passwordArray);
        }

        // Retrieve the private key
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, passwordArray);

        // Retrieve the public key from the certificate
        Certificate certificate = keyStore.getCertificate(alias);
        PublicKey publicKey = certificate.getPublicKey();

        return new KeyPair(publicKey, privateKey);
    }

    // Helper Method: Generate a Self-Signed Certificate
    private static X509Certificate generateSelfSignedCertificate(KeyPair keyPair) throws Exception {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        // A basic dummy certificate (for KeyStore purposes only)
        String certData = "-----BEGIN CERTIFICATE-----\n" +
                Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()) +
                "\n-----END CERTIFICATE-----";
        return (X509Certificate) certFactory.generateCertificate(new java.io.ByteArrayInputStream(certData.getBytes()));
    }
	
	
	
	
}
