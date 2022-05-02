package genelectrovise.magiksmostevile.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;

public class PrimitiveWidgetFactory implements IWidgetFactory {

    @Override
    public <T extends ForgeConfigSpec.ConfigValue<?>> Widget create(String name, T value) {

        if (value instanceof ForgeConfigSpec.BooleanValue) {
            return new ToggleWidget(0, 0, 0, 0, get((ForgeConfigSpec.BooleanValue) value));
        }

        if (value instanceof ForgeConfigSpec.IntValue) {
            return new TextFieldWidget(Minecraft.getInstance().font, 0,0,0,0, new StringTextComponent(name));
        }


        return null;
    }

    private <V> V get(ForgeConfigSpec.ConfigValue<V> in) {
        return in.get();
    }
}
