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
  
  // Energy update
  public static SimpleChannel channel;
  public static final ResourceLocation channelRl = new ResourceLocation(MagiksMostEvile.MODID, "evile_pixie_courier");
  public static final String MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ID_TO_SERVER = 1;
  public static final int ID_TO_CLIENT = 2;

  public PixieCourier() {}

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by AltarNetworkingManager!");
    channel = NetworkRegistry.newSimpleChannel(channelRl, () -> MESSAGE_PROTOCOL_VERSION, (ver) -> Message.isProtocolGoodVersion(ver), (ver) -> Message.isProtocolGoodVersion(ver));
    channel.registerMessage(ID_TO_CLIENT, Message.class, Message::encode, Message::decode, PixieCourier::onMessageReceived);
  }

  public static void onMessageReceived(final Message<?> message, Supplier<NetworkEvent.Context> ctxSupplier) {
    ctxSupplier.get().setPacketHandled(true);
  }

}
