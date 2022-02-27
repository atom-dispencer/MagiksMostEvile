package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import com.google.common.collect.Maps;
import genelectrovise.magiksmostevile.network.pixiecourier.PixieCourier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;

public class ServerHandshakeManager implements HandshakeManager {

    public static final Logger LOGGER = LogManager.getLogger(ServerHandshakeManager.class);

    protected volatile Map<UUID, OngoingHandshake> ongoingHandshakes = Maps.newHashMap();

    @Override
    public CourierHandshakePacket getResponse(CourierHandshakePacket packet, OngoingHandshake ongoingHandshake, NetworkEvent.Context context) {
        //TODO Get server response to handshake
        return null;
    }

    @Override
    public OngoingHandshake getOngoingHandshake(ServerPlayerEntity sender) {
        UUID uuid = sender.getUUID();
        return ongoingHandshakes.get(uuid);
    }



    /**
     * When the event is fired, starts a new handshake interaction.
     *
     * @param event {@link PlayerEvent.PlayerLoggedInEvent}
     */
    public void initiateNewHandshake(PlayerEvent.PlayerLoggedInEvent event) {
        // Target must be a ServerPlayerEntity
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) {
            return;
        }

        // This method is only called on the dedicated server, so it should be working with ServerPlayerEntities.
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

        // Send an empty exchange packet to the client
        PixieCourier.getInstance().channel.send(net.minecraftforge.fml.network.PacketDistributor.PLAYER.with(() -> player), CourierHandshakePacket.getNewExchangePacket());
    }
}
