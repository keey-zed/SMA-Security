package ma.enset.sma.security.signature;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String encodedPbK = (String) getArguments()[0];
        byte[] decodedPbK = Base64.getDecoder().decode(encodedPbK);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receive = receive();
                if (receive != null) {
                    String documentSign = receive.getContent();
                    String[] documentSplit = documentSign.split("##-##");
                    byte[] document = Base64.getDecoder().decode(documentSplit[0]);
                    byte[] sign = Base64.getDecoder().decode(documentSplit[1]);
                    try {
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPbK));
                        Signature signature = Signature.getInstance("SHA256withRSA");
                        signature.initVerify(publicKey);
                        signature.update(document);
                        boolean verify = signature.verify(sign);
                        System.out.println(verify?"Signature ok":"Signature not ok");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
