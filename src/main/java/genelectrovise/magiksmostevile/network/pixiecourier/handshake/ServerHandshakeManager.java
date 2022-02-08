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

/**
 * ({@link ServerHandshakeManager#onClientJoinServerRequestCourierHashPacket(PlayerEvent.PlayerLoggedInEvent)}) Client joins. Server requests hash of {@link PixieCourier} registry contents. Client responds with hash. If hash matches server hash, client is allowed to join. Else, server replies with error code.
 */
public class ServerHandshakeManager implements HandshakeManager {

    public static final Logger LOGGER = LogManager.getLogger(ServerHandshakeManager.class);

    protected volatile Map<UUID, OngoingHandshake> ongoingHandshakes = Maps.newHashMap();

    @SubscribeEvent
    // Alternatively EntityJoinWorldEvent
    public static void onClientJoinServerRequestCourierHashPacket(PlayerEvent.PlayerLoggedInEvent event) {
        // Only run when on the dedicated server.
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER,
                /* Supplier returning a SafeRunnable */ () -> (() -> PixieCourier.getInstance().initiateNewHandshake(event)));
    }

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
}
