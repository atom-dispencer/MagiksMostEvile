package genelectrovise.magiksmostevile.common.network.altar.energy_update;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 24 May 2020
 */
@Deprecated
public class AltarIchorUpdateMessageToServer {

  private static boolean messageIsValid;

  public AltarIchorUpdateMessageToServer() {
    messageIsValid = true;
  }

  public void encode(PacketBuffer buffer) {
    MagiksMostEvile.LOGGER.debug("Encoding message to server");
  }

  public static AltarIchorUpdateMessageToServer decode(PacketBuffer buffer) {
    MagiksMostEvile.LOGGER.debug("Decoding message to server");
    return new AltarIchorUpdateMessageToServer();
  }

  public boolean isValid() {
    return messageIsValid;
  }
}
