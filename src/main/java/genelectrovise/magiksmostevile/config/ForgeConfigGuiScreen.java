package genelectrovise.magiksmostevile.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;

/**
 * Allows editing common and client config in game. Currently, freezes the game GUI and you have to ESC out.
 *
 * @see net.minecraft.client.gui.widget.Widget
 */
public class ForgeConfigGuiScreen extends Screen {

    private Minecraft minecraft;
    private Screen parentScreen;

    /**
     * @param minecraft    The global {@link Minecraft} instance.
     * @param parentScreen I suppose that this is the parent screen which spawned this screen?
     */
    public ForgeConfigGuiScreen(Minecraft minecraft, Screen parentScreen) {
        super(new StringTextComponent(MagiksMostEvile.MODID + " Configuration"));
    }

    @Override
    protected void init() {
        super.init();
        // Close screen immediately because it doesn't do anything right now.
        onClose();
    }

    @Override
    public void removed() {
        super.removed();
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    /**
     * @param keyId       The numerical ID of the key pressed.
     * @param unknownInt1 Unknown purpose. Other GUIs ignore so I will too.
     * @param unknownInt2 Unknown purpose. Other GUIs ignore so I will too.
     * @return Whatever the endless chain of super.keyPressed passes back down.
     */
    @Override
    public boolean keyPressed(int keyId, int unknownInt1, int unknownInt2) {
        return super.keyPressed(keyId, unknownInt1, unknownInt2);
    }

    /**
     * Don't forget about {@link AbstractGui#blit(MatrixStack, int, int, int, int, int, int)}
     *
     * @param stack        The stack on which to render things.
     * @param unknownInt1  Unknown purpose. Other GUIs ignore so I will too.
     * @param unknownInt2  Unknown purpose. Other GUIs ignore so I will too.
     * @param unknownFloat Unknown purpose. Other GUIs ignore so I will too.
     */
    @Override
    public void render(@Nonnull MatrixStack stack, int unknownInt1, int unknownInt2, float unknownFloat) {

        super.render(stack, unknownInt1, unknownInt2, unknownFloat);
    }

    @Override
    public void renderBackground(@Nonnull MatrixStack stack) {
        super.renderBackground(stack);
    }
}
