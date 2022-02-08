package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

public class OngoingHandshake {
    protected long initiationTime;
    protected String clientRegistryHash;
    protected String serverResponse;

    public long getInitiationTime() {
        return initiationTime;
    }

    public void setInitiationTime(long initiationTime) {
        this.initiationTime = initiationTime;
    }

    public String getClientRegistryHash() {
        return clientRegistryHash;
    }

    public void setClientRegistryHash(String clientRegistryHash) {
        this.clientRegistryHash = clientRegistryHash;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
    }
}
