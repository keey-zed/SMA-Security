package ma.enset.sma.security.rsa;

import java.security.*;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512); // Precise la taille de la cle, le minimum c'eat 512 (RSA)
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String encodedPRK = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodedPBK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("Private key : " + encodedPRK);
        System.out.println("Public key : " + encodedPBK);

    }
}
