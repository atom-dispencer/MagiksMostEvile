/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.arrow_toggles;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

/**
 * @author GenElectrovise 12 Jun 2020
 */
public class AltarToggleButtonMessageToClient {

  private ResourceLocation location;

  /**
   * @param resourceLocation
   */
  public AltarToggleButtonMessageToClient(ResourceLocation resourceLocation) {
    this.location = resourceLocation;
  }

  public void encode(PacketBuffer buffer) {
    buffer.writeResourceLocation(location);
  }

  public static AltarToggleButtonMessageToClient decode(PacketBuffer buffer) {
    ResourceLocation rl = buffer.readResourceLocation();

    return new AltarToggleButtonMessageToClient(rl);
  }

  public boolean isValid() {
    if (this.location == null) {
      return false;
    }

    return true;
  }

  /**
   * @return the location
   */
  public ResourceLocation getLocation() {
    return location;
  }
}
