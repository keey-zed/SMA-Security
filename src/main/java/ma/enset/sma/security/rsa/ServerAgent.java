package ma.enset.sma.security.rsa;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String encodedPrK = (String) getArguments()[0];
        byte[] decodedPrK = Base64.getDecoder().decode(encodedPrK);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receive = receive();
                if (receive != null) {
                    String encryptEncodedMSg = receive.getContent();
                    byte[] encryptMsg = Base64.getDecoder().decode(encryptEncodedMSg);
                    try {
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrK));
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        byte[] decryptMsg = cipher.doFinal(encryptMsg);
                        System.out.println(new String(decryptMsg));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
