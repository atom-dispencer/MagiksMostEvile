package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

public enum HandshakePhase {
    CLIENT_ATTEMPTING_TO_JOIN,
    SERVER_REQUESTING_HASH,
    CLIENT_RETURNING_HASH,
    SERVER_SENDING_STATUS,
    COMPLETE
}
