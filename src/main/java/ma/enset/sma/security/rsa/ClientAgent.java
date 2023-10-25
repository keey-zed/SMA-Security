package ma.enset.sma.security.rsa;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                String message = "Hey server-ssi :)";
                String encodedPbK = (String) getArguments()[0];
                byte[] decodedPbK = Base64.getDecoder().decode(encodedPbK);
                try {
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPbK));
                    Cipher cipher = Cipher.getInstance("RSA");
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                    byte[] encryptMsg = cipher.doFinal(message.getBytes());
                    String encryptEncodedMsg = Base64.getEncoder().encodeToString(encryptMsg);
                    ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                    aclMessage.setContent(encryptEncodedMsg);
                    aclMessage.addReceiver(new AID("server", AID.ISLOCALNAME));
                    send(aclMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}