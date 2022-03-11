package genelectrovise.magiksmostevile.world.gen.customore;

import lombok.Data;
import net.minecraft.util.ResourceLocation;

@Data
public class CustomOreGenerationSettings {

    private ResourceLocation settingsLocation;

    public CustomOreGenerationSettings(ResourceLocation settingsLocation) {
        this.settingsLocation = settingsLocation;
    }
}
