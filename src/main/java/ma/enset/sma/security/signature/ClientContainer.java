package ma.enset.sma.security.signature;

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
        String encodedPRK = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAhc+t/xmeKhQtZv2vagzKxJJ+/mNI+RqBN/7hs7kHIG3VNYXmotc6mPRJhtmHy6rbnvKiXp3SJvnqkV6mFeyEewIDAQABAkATUCVJ43WrlxvyeHz/7na5pndD2iqnIYczAMust/DqYhplPrsJ5bgSeMkhdTawpJAhE6/INPlB3aSWzoE2mDLBAiEAv50ABt9tmn3U3WZpLXl4Y3tZknP6VFd4IO8p61oZRJsCIQCyxmudOPxlLpzgJOwWEbJFwVwlAJ8RQrHeTGj2di2NoQIhAINX4BvV7XBXzqDQwUndmMtAUz7TZwWHS53vsqFUCjv/AiBpgJd5jWvF+qAx1xhMXd0rNVU/sQIumYGGguleqZdwgQIgQcIzmApisUjMfsObRoRrLqWa7afZ4CoZ/+EoljqZUOQ=";
        AgentController clientAgent = agentContainer.createNewAgent("client", ClientAgent.class.getName(), new Object[]{encodedPRK});
        clientAgent.start();
    }
}
