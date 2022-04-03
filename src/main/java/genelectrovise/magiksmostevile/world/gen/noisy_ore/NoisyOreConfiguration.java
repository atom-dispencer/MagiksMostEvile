package genelectrovise.magiksmostevile.world.gen.noisy_ore;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Map;

@Data
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class NoisyOreConfiguration {

    /**
     * Maps a JSON resource file to a Generation object, i.e. "amethyst_ore_overworld.json": POJO"
     */
    private Map<String, Generation> generations;

    /**
     * The contents of a resource JSON file, i.e. "amethyst_ore_overworld.json"
     */
    @Data
    @AllArgsConstructor
    @ParametersAreNullableByDefault
    public static class Generation {
        private Settings normal;
        private SituationalSettings dimensions;
        private SituationalSettings biomes;
    }

    /**
     * A "dimensions" or "biomes" tag in a resource JSON file. Configures exclusions and contains settings for situations.
     */
    @Data
    @AllArgsConstructor
    @ParametersAreNonnullByDefault
    public static class SituationalSettings {
        private String[] exclude;
        private Map<String, Settings> settings;
    }

    /**
     * A "settings" or "normal" tag in a resource JSON file. Configures placement options such as vein height and distribution.
     */
    @Data
    @AllArgsConstructor
    @ParametersAreNullableByDefault
    public static class Settings {
        private int level;
        private float eccentricity;
        private int minSize;
        private int maxSize;
        private String[] replace;
        private String with;
    }
}
