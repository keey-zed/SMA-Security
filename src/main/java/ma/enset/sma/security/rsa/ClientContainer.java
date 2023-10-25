package ma.enset.sma.security.rsa;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class ClientContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        String encodedPBK = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALewbZxOlxwFrzyvqL2aPC4Y+NmrK+MSpU3FeoH0pdi+IS1Ia3nCDsxyDa6+rops4deqwASevhHpuDuqgZKl1EsCAwEAAQ==";
        AgentController clientAgent = agentContainer.createNewAgent("client", ClientAgent.class.getName(), new Object[]{encodedPBK});
        clientAgent.start();
    }
}
