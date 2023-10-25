package ma.enset.sma.security.aes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                String message = "Hey server-ssi :)";
                String password = (String) getArguments()[0]; //password
                SecretKey secretKey = new SecretKeySpec(password.getBytes(), "AES");
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    byte[] encryMsg = cipher.doFinal(message.getBytes());
                    /* In computer programming, Base64 is a group of binary-to-text encoding schemes that represent
                    binary data in sequences of 24 bits that can be represented by four 6-bit Base64 digits */
                    String encryptEncodedMsg = Base64.getEncoder().encodeToString(encryMsg);
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
