package ma.enset.sma.security.aes;

import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class ServerContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        String password = "1234567890abcdef"; // 128 bit - AES
        AgentController serverAgent = agentContainer.createNewAgent("server", ServerAgent.class.getName(), new Object[]{password});
        serverAgent.start();
    }
}
