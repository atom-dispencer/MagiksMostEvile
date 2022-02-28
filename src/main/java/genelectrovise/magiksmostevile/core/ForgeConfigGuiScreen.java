package genelectrovise.magiksmostevile.core;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

/**
 * Allows editing common and client config in game. Currently, freezes the game GUI and you have to ESC out.
 */
public class ForgeConfigGuiScreen extends Screen {

    public ForgeConfigGuiScreen() {
        super(new StringTextComponent("Magiks Most Evile Config"));
    }


}
