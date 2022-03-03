package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import genelectrovise.magiksmostevile.network.pixiecourier.PixieCourier;
import lombok.*;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * <ol>
 *     <li> ({@link PixieCourier#onClientJoinServer_requestCourierHashPacket(PlayerEvent.PlayerLoggedInEvent)}) Client joins.
 *     <li> Server requests hash of {@link PixieCourier} registry contents.
 *     <li> Client responds with hash.
 *     <li> If hash matches server hash, client is allowed to join. Else, server replies with error code.
 * </ol>
 */
@Getter
@ToString
public enum HandshakePhase {
    CLIENT_ATTEMPTING_TO_JOIN("Player {player} is attempting to join the server (initiating " + PixieCourier.class.getSimpleName() + " handshake)"),
    SERVER_REQUESTING_HASH("Server requesting " + PixieCourier.class.getSimpleName() + " registry hash from {player}"),
    CLIENT_RETURNING_HASH("Player {player} returned registry hash {hash}"),
    SERVER_SENDING_STATUS("Server replying to {player} with status {status}"),
    SUCCESS("Player {player} successfully shook hands with the server (" + PixieCourier.class.getSimpleName() + ")"),
    FAILURE("Player {player} failed to shake hands with the server (" + PixieCourier.class.getSimpleName() + ") for reason: {failed}");

    private final String message;

    HandshakePhase(String message) {
        this.message = message;
    }
}
