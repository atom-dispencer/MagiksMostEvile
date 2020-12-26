/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.glyph;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class GlyphMessageHandlerOnServer {

  public static void onMessageReceived(final GlyphMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      MagiksMostEvile.LOGGER.warn("Invalid message received on server.");
      return;
    }
    if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
      MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message));
  }

  private static void processMessage(GlyphMessageToServer message) {
    return;
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return GlyphNetworkingManager.GLYPH_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }
}
