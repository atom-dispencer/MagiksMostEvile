/**
 * 
 */
package genelectrovise.magiksmostevile.network.glyph;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author GenElectrovise 19 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GlyphNetworkingManager {

  // Glyph
  public static SimpleChannel CGlyph;
  public static final ResourceLocation RLGlyph =
      new ResourceLocation(MagiksMostEvile.MODID, "glyph");
  public static final String GLYPH_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int GLYPH_TO_SERVER = 11;
  public static final int GLYPH_TO_CLIENT = 12;

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by GlyphNetworkManager!");
    CGlyph = NetworkRegistry.newSimpleChannel(RLGlyph, () -> GLYPH_MESSAGE_PROTOCOL_VERSION,
        GlyphMessageHandlerOnClient::isProtocolAccepted,
        GlyphMessageHandlerOnServer::isProtocolAccepted);
    CGlyph.registerMessage(GLYPH_TO_SERVER, GlyphMessageToServer.class,
        GlyphMessageToServer::encode, GlyphMessageToServer::decode,
        GlyphMessageHandlerOnServer::onMessageReceived);
    CGlyph.registerMessage(GLYPH_TO_CLIENT, GlyphMessageToClient.class,
        GlyphMessageToClient::encode, GlyphMessageToClient::decode,
        GlyphMessageHandlerOnClient::onMessageReceived);
  }

}
