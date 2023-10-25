package ma.enset.sma.security.rsa;

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
        String encodedPRK = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAt7BtnE6XHAWvPK+ovZo8Lhj42asr4xKlTcV6gfSl2L4hLUhrecIOzHINrr6uimzh16rABJ6+Eem4O6qBkqXUSwIDAQABAkAD5970x2EEKgTHXA7Q9UIZUtObwT1+JwvXwv6vW7XZDWycUSsC8FtoSI9q0z/Y8C/p/Iqmk3qjY/556PMFkHABAiEAwbSNKh2lolnTG63gVYP7ZrkRYTNZopvPtA1DxMpqpEsCIQDyw0Q+IA/eI3KiYtjlh2bC04/K0T8BZgCwVdIg5tOQAQIgKrfbrd8N6SjwEpmc8J8DsVq9B3egm+jKvIV0XZDaRdMCIQDTRuhEnUjSDs280ARutsnTc6aoYDXSdMDSts46/klQAQIgaqffOAQQ/ux98L/2goiE7ADJToMiZIo00sN8uWVE+rM=";
        AgentController serverAgent = agentContainer.createNewAgent("server", ServerAgent.class.getName(), new Object[]{encodedPRK});
        serverAgent.start();
    }
}
