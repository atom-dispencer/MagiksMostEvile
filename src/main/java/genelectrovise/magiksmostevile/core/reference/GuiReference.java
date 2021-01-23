package genelectrovise.magiksmostevile.core.reference;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;

public class GuiReference {

  /**
   * Specifies an intentional zero, not a default -- for readability
   */
  public static final int ZERO = 0;

  public static class Altar {
    public static final int GUI_ALTAR = 0;

    public static class Main {
      public static final ResourceLocation MAIN_TEXTURE =
          new ResourceLocation(MagiksMostEvile.MODID, "textures/container/altar/main.png");
      public static final int MAIN_WIDTH = 356;
      public static final int MAIN_HEIGHT = 179;
    }

    public static class VerticalInventory {
      public static final ResourceLocation PLAYER_INVENTORY_VERTICAL_TEXTURE = new ResourceLocation(
          MagiksMostEvile.MODID, "textures/container/altar/player_inventory_vertical.png");
    }

    public static class CastButton {
      public static final ResourceLocation CAST_TEXTURE =
          new ResourceLocation(MagiksMostEvile.MODID, "textures/container/altar/cast_button.png");
      public static final int CAST_WIDTH = 29;
      public static final int CAST_HEIGHT = 16;
      public static final int CAST_UNPRESSED_X = 0;
      public static final int CAST_UNPRESSED_Y = 0;
      public static final int CAST_PRESSED_X = 0;
      public static final int CAST_PRESSED_Y = 19;
    }

    public static class ToggleButtons {
      public static final ResourceLocation TEXTURE =
          new ResourceLocation(MagiksMostEvile.MODID, "textures/container/altar/toggles.png");
      public static final int LEFT_X = 0;
      public static final int LEFT_Y = 0;
      public static final int LEFT_WIDTH = 10;
      public static final int LEFT_HEIGHT = 16;

      public static final int RIGHT_X = 11;
      public static final int RIGHT_Y = 0;
      public static final int RIGHT_WIDTH = 10;
      public static final int RIGHT_HEIGHT = 16;
    }
  }
}
