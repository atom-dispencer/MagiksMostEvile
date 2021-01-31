package genelectrovise.magiksmostevile.network.inscription_table.button_pressed;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class ButtonPressedMessageToServer {
  private static boolean messageIsValid;

  private ResourceLocation buttonName;

  public ButtonPressedMessageToServer(ResourceLocation signumName) {
    messageIsValid = true;
    this.buttonName = signumName;
  }

  public void encode(PacketBuffer buffer) {
    MagiksMostEvile.LOGGER.debug("Encoding AltarCastButtonPressedMessageToServer.");
    buffer.writeResourceLocation(buttonName);
  }

  public static ButtonPressedMessageToServer decode(PacketBuffer buffer) {
    MagiksMostEvile.LOGGER.debug("Decoding AltarCastButtonPressedMessageToServer");

    ResourceLocation buttonName = buffer.readResourceLocation();

    return new ButtonPressedMessageToServer(buttonName);
  }
  
  public ResourceLocation getSignumName() {
    return buttonName;
  }

  public boolean isValid() {
    return messageIsValid;
  }

}
