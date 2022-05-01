package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import com.google.common.collect.Maps;
import genelectrovise.magiksmostevile.config.Config;
import genelectrovise.magiksmostevile.network.pixiecourier.PixieCourier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class ServerHandshakeManager implements HandshakeManager {

    public static final Logger LOGGER = LogManager.getLogger(ServerHandshakeManager.class);

    protected volatile Map<UUID, OngoingHandshake> ongoingHandshakes = Maps.newHashMap();


    /**
     * When the event is fired, starts a new handshake interaction. Delegated from {@link PixieCourier}. Only called from the dedicated server using {@link net.minecraftforge.fml.DistExecutor#safeRunWhenOn(Dist, Supplier)}
     *
     * @param event {@link PlayerEvent.PlayerLoggedInEvent}
     */
    @Override
    public void initiateNewHandshake(PlayerEvent.PlayerLoggedInEvent event) {

        // Target must be a ServerPlayerEntity
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) {
            LOGGER.debug("Aborting handshake as PlayerLoggedInEvent was not fired by a ServerPlayerEntity.");
            return;
        }

        // Abort handshake if configured to
        if(!Config.SERVER.useCourierHandshake.get()){
            LOGGER.debug("Player joining, but handshake is disabled.");
            return;
        }

        // Only called on dedicated server, so it should be working with ServerPlayerEntities.
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

        // Send an empty exchange packet to the client
        PixieCourier.getInstance().channel.send(net.minecraftforge.fml.network.PacketDistributor.PLAYER.with(() -> player), CourierHandshakePacket.getNewExchangePacket());
    }

    @Override
    public CourierHandshakePacket getResponse(CourierHandshakePacket packet, OngoingHandshake ongoingHandshake, NetworkEvent.Context context) {

        if (!packet.getClientRegistryHash().equals(CourierHandshakePacket.DEFAULT_CLIENT_REGISTRY_HASH)) {
            // TODO Check hash and reply success or failure
            return null;
        }

        //TODO Get server response to handshake
        return null;
    }

    @Override
    public OngoingHandshake getOngoingHandshake(ServerPlayerEntity sender) {
        UUID uuid = sender.getUUID();
        return ongoingHandshakes.get(uuid);
    }
}
