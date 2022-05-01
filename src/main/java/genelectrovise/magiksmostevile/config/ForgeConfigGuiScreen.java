package genelectrovise.magiksmostevile.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

/**
 * Allows editing common and client config in game. Currently, freezes the game GUI and you have to ESC out.
 */
public class ForgeConfigGuiScreen extends Screen {

    public ForgeConfigGuiScreen() {
        super(new StringTextComponent("Magiks Most Evile Config"));
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

    @Override
    public boolean keyPressed(int keyId, int p_231046_2_, int p_231046_3_) {
        return super.keyPressed(keyId, p_231046_2_, p_231046_3_);
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    @Override
    public void renderBackground(MatrixStack p_230446_1_) {
        super.renderBackground(p_230446_1_);
    }
}
