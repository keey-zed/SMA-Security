package ma.enset.sma.security.signature;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                String document = "Hey server-ssi :)";
                String encodedPrK = (String) getArguments()[0];
                byte[] decodedPrK = Base64.getDecoder().decode(encodedPrK);
                try {
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrK));
                    Signature signature = Signature.getInstance("SHA256withRSA");
                    signature.initSign(privateKey);
                    signature.update(document.getBytes());
                    byte[] sign = signature.sign();
                    String encodedSign = Base64.getEncoder().encodeToString(sign);
                    String encodeDocument = Base64.getEncoder().encodeToString(document.getBytes());
                    String documentSign = encodeDocument + "##-##" + encodedSign;
                    ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                    aclMessage.setContent(documentSign);
                    aclMessage.addReceiver(new AID("server", AID.ISLOCALNAME));
                    send(aclMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}