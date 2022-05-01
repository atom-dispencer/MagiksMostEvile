package genelectrovise.magiksmostevile.config;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Allows editing common and client config in game. Currently, freezes the game GUI and you have to ESC out.
 *
 * @see net.minecraft.client.gui.widget.Widget
 */
public class ForgeConfigGuiScreen extends Screen {

    private static final Logger LOGGER = LogManager.getLogger(ForgeConfigGuiScreen.class);

    private Set<? extends UnmodifiableConfig.Entry> values;
    private Map<String, Widget> widgets = Maps.newHashMap();

    public ForgeConfigGuiScreen() {
        super(new StringTextComponent(MagiksMostEvile.MODID + " Configuration"));
        values = Sets.newHashSet();
    }

    @Override
    protected void init() {
        super.init();

        UnmodifiableConfig commonValues = Config.COMMON_SPEC.getValues();
        values = commonValues.entrySet();

        // onClose();
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
        this.renderBackground(stack);

        int offset = 1;
        List<Widget> widgets = Lists.newArrayList();
        for (UnmodifiableConfig.Entry entry : values) {
            drawString(
                    stack,
                    Minecraft.getInstance().font,
                    new StringTextComponent(entry.getKey())
                            .withStyle(TextFormatting.WHITE), //
                    offset * 20, // x coord
                    offset * 20, // y coord
                    offset * 20); // No idea
            new TextFieldWidget(
                    Minecraft.getInstance().font, //
                    offset * 40, // x coord
                    offset * 40, //y coord
                    offset * 20, // width
                    offset * 40, // height
                    new StringTextComponent("default")
                            .withStyle(TextFormatting.WHITE))
                    .render(stack, unknownInt1, unknownInt2, unknownFloat);
            offset++;
        }
        super.render(stack, unknownInt1, unknownInt2, unknownFloat);
    }

    @Override
    public void renderBackground(@Nonnull MatrixStack stack) {
        this.renderBackground(stack, 0);
    }

    @Override
    public void renderBackground(@Nonnull MatrixStack stack, int unknownOverload) {
        this.renderDirtBackground(unknownOverload);
    }
}
