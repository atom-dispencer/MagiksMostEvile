package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import lombok.Data;

@Data
public class OngoingHandshake {
    protected long initiationTime;
    protected String clientRegistryHash;
    protected String serverResponse;
    protected HandshakePhase handshakePhase;
}
