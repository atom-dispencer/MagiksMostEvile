package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClientHandshakeManager implements HandshakeManager {
    protected OngoingHandshake ongoingHandshake;

    private static final Logger LOGGER = LogManager.getLogger(ClientHandshakeManager.class);

    @Override
    public CourierHandshakePacket getResponse(CourierHandshakePacket packet, OngoingHandshake ongoingHandshake, NetworkEvent.Context context) {
        //TODO Get client response to handshake
        return null;
    }

    @Override
    public OngoingHandshake getOngoingHandshake(ServerPlayerEntity sender) {
        return ongoingHandshake;
    }

    @Override
    public void initiateNewHandshake(PlayerEvent.PlayerLoggedInEvent event) {
        LOGGER.warn(getClass().getSimpleName() + " cannot initiate new handshakes.");
    }
}
