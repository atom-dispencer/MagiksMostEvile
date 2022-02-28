package genelectrovise.magiksmostevile.network.pixiecourier;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.pixiecourier.handshake.ClientHandshakeManager;
import genelectrovise.magiksmostevile.network.pixiecourier.handshake.CourierHandshakePacket;
import genelectrovise.magiksmostevile.network.pixiecourier.handshake.HandshakeManager;
import genelectrovise.magiksmostevile.network.pixiecourier.handshake.ServerHandshakeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

//@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PixieCourier {

    public static final Logger LOGGER = LogManager.getLogger(PixieCourier.class);

    // Protocol
    public static final String MESSAGE_PROTOCOL_VERSION = "1.0";
    public static final int ID_TO_SERVER = 1;
    public static final int ID_TO_CLIENT = 2;
    public static final Supplier<ResourceLocation> channelLocationSupplier = () -> new ResourceLocation(MagiksMostEvile.MODID, "evile_pixie_courier");
    private static final PixieCourier INSTANCE = new PixieCourier();
    // Channel
    public SimpleChannel channel;
    // Processing
    protected PacketDistributor distributor = new PacketDistributor();
    protected volatile HandshakeManager handshakeManager = ((FMLEnvironment.dist == Dist.DEDICATED_SERVER) ? new ServerHandshakeManager() : new ClientHandshakeManager());

    private PixieCourier() {
        //
    }

    public static PixieCourier getInstance() {
        return INSTANCE;
    }

    public HandshakeManager getHandshakeManager() {
        return handshakeManager;
    }

    // @SubscribeEvent // Subscribed manually
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by PixieCourier!");

        INSTANCE.channel = NetworkRegistry.newSimpleChannel(channelLocationSupplier.get(), () -> MESSAGE_PROTOCOL_VERSION, PacketEncoder::isValidVersion, PacketEncoder::isValidVersion);
        INSTANCE.channel.registerMessage(ID_TO_CLIENT, PixiePacket.class,

                // Encode packet
                (t, u) -> {
                    try {
                        PacketEncoder.encode(t, u);
                    } catch (CourierException e) {
                        e.printStackTrace();
                    }
                },
                // Decode packet
                (t) -> {
                    try {
                        return PacketEncoder.decode(t);
                    } catch (CourierException e) {
                        e.printStackTrace();
                    }
                    return null;
                },
                // Recieve packet
                PixieCourier::recieve);

        // A register CourierHandshakePacket
        INSTANCE.channel.registerMessage(1_569_373, // A big random-ish number
                CourierHandshakePacket.class, //
                CourierHandshakePacket::encode, //
                CourierHandshakePacket::decode, //
                CourierHandshakePacket::handleMessage);
    }

    public static void recieve(final PixiePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        try {
            INSTANCE.distributor.processPacketWithChecks(message, contextSupplier.get(), INSTANCE.distributor.findProcessor(message, PixieProcessor.Registry.getInstance()));
        } catch (CourierException c) {
            c.printStackTrace();
        }
    }

    public static void send(PacketTarget target, PixiePacket packet) {
        try {
            INSTANCE.channel.send(target, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @SubscribeEvent // Subscribed manually
    // Alternatively EntityJoinWorldEvent
    public static void onClientJoinServer_requestCourierHashPacket(PlayerEvent.PlayerLoggedInEvent event) {
        LOGGER.debug("PlayerLoggedInEvent heard by PixieCourier. Will attempt handshake.");

        // Only run when on the dedicated server.
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER,
                /* Supplier returning a SafeRunnable */ () -> (() -> getInstance()
                        .getHandshakeManager()
                        .initiateNewHandshake(event)));
    }
}
