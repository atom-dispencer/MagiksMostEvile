package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PixieCourier {

  // Protocol
  public static final String MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ID_TO_SERVER = 1;
  public static final int ID_TO_CLIENT = 2;
  public static final Supplier<ResourceLocation> channelLocationSupplier = () -> new ResourceLocation(MagiksMostEvile.MODID, "evile_pixie_courier");


  // Channel
  public SimpleChannel channel;

  // Processing
  protected PacketDistributor distributor = new PacketDistributor();
  protected PacketEncoder encoder = new PacketEncoder();

  public PixieCourier() {}

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) throws CourierException {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by PixieCourier!");



    MagiksMostEvile.COURIER.channel =
        NetworkRegistry.newSimpleChannel(channelLocationSupplier.get(), () -> MESSAGE_PROTOCOL_VERSION, (ver) -> PacketEncoder.isValidVersion(ver), (ver) -> PacketEncoder.isValidVersion(ver));
    MagiksMostEvile.COURIER.channel.registerMessage(ID_TO_CLIENT, PixiePacket.class,

        // Encode packet
        (t, u) -> {
          try {
            PacketEncoder.encode(t, u);
          } catch (CourierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        },
        // Decode packet
        PacketEncoder::decode,
        // Recieve packet
        PixieCourier::recieve);
  }

  public static void recieve(final PixiePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
    MagiksMostEvile.COURIER.distributor.forwardPacketToProcessor(message, contextSupplier.get());
  }

  public static void send() {

  }

}
