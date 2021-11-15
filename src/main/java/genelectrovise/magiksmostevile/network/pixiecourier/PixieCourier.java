package genelectrovise.magiksmostevile.network.pixiecourier;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PixieCourier {

  // Energy update
  public static SimpleChannel channel;
  public static final ResourceLocation channel_rl = new ResourceLocation(MagiksMostEvile.MODID, "evile_pixie_courier");
  public static final String MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ID_TO_SERVER = 1;
  public static final int ID_TO_CLIENT = 2;

  protected static PacketDistributor distributor = new PacketDistributor();

  private PixieCourier() {}

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by AltarNetworkingManager!");
    channel = NetworkRegistry.newSimpleChannel(channel_rl, () -> MESSAGE_PROTOCOL_VERSION, (ver) -> PacketEncoder.isValidVersion(ver), (ver) -> PacketEncoder.isValidVersion(ver));
    channel.registerMessage(ID_TO_CLIENT, PixiePacket.class, PacketEncoder::encode, PacketEncoder::decode, PacketEncoder::recievePacket);
  }

  public static void recieve(PixiePacket packet, Context context) {
    distributor.forwardPacketToProcessor(packet, context);
  }

}
