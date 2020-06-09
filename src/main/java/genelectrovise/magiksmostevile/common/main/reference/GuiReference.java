package genelectrovise.magiksmostevile.common.main.reference;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;

public class GuiReference {

	/**
	 * Specifies an intentional zero, not a default -- for readability
	 */
	public static final int ZERO = 0;

	public static class Altar {
		public static final int GUI_ALTAR = 0;

		public static class Main {
			public static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(MagiksMostEvile.MODID, "textures/container/altar/main.png");
			public static final int MAIN_WIDTH = 356;
			public static final int MAIN_HEIGHT = 179;
		}

		public static class VerticalInventory {
			public static final ResourceLocation PLAYER_INVENTORY_VERTICAL_TEXTURE = new ResourceLocation(MagiksMostEvile.MODID, "textures/container/altar/player_inventory_vertical.png");
		}

		public static class Button {
			public static final ResourceLocation CAST_TEXTURE = new ResourceLocation(MagiksMostEvile.MODID, "textures/container/altar/cast_button.png");
			public static final int CAST_WIDTH = 28;
			public static final int CAST_HEIGHT = 16;
			public static final int CAST_UNPRESSED_X = 0;
			public static final int CAST_UNPRESSED_Y = 0;
			public static final int CAST_PRESSED_X = 0;
			public static final int CAST_PRESSED_Y = 19;
		}
	}
}
