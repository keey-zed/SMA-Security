package ma.enset.sma.security.signature;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class ServerContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        String encodedPBK = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIXPrf8ZnioULWb9r2oMysSSfv5jSPkagTf+4bO5ByBt1TWF5qLXOpj0SYbZh8uq257yol6d0ib56pFephXshHsCAwEAAQ==";
        AgentController serverAgent = agentContainer.createNewAgent("server", ServerAgent.class.getName(), new Object[]{encodedPBK});
        serverAgent.start();
    }
}
