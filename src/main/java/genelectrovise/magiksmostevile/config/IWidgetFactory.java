package genelectrovise.magiksmostevile.config;

import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.common.ForgeConfigSpec;

public interface IWidgetFactory {

    /**
     *
     * @param value An instance of ConfigValue from a ConfigSpec somewhere.
     * @param <T> The generic type of the entering ConfigValue
     * @return A new widget appropriate for displaying the value
     */
    <T extends ForgeConfigSpec.ConfigValue<?>> Widget create(String name, T value);
}
