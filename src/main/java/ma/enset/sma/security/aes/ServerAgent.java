package ma.enset.sma.security.aes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String password = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receive = receive();
                if (receive != null) {
                    String encryptEncodedMSg = receive.getContent();
                    byte[] encryptMsg = Base64.getDecoder().decode(encryptEncodedMSg);
                    SecretKey secretKey = new SecretKeySpec(password.getBytes(), "AES");
                    try {
                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, secretKey);
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
