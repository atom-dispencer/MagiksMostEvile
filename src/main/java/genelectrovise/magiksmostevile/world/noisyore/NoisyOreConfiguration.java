package genelectrovise.magiksmostevile.world.noisyore;

import com.google.common.collect.Maps;
import genelectrovise.magiksmostevile.gson.GsonConfigurator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraftforge.event.AddReloadListenerEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Map;

@Data
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class NoisyOreConfiguration {

    protected static volatile NoisyOreConfiguration INSTANCE;

    public static void onResourceManagerReload(AddReloadListenerEvent event) {
        event.addListener(new NoisyOreResourceManagerReloadListener(GsonConfigurator.newConfiguredInstance(), "unknown"));
    }

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
        public static final Generation DUMMY = new Generation(0, Settings.DUMMY, SituationalSettings.DUMMY, SituationalSettings.DUMMY);
        /**
         * The number of times the seed for the simplex noise generator should be hashed. This means that each generation type can have a unique worldwide distribution.
         */
        private int simplexSeedHashes;
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
        public static final SituationalSettings DUMMY = new SituationalSettings(new String[]{}, Maps.newHashMap());

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
        public static final Settings DUMMY = new Settings(0, 0, 0, 0, new String[]{}, "minecraft:stone");

        private int level;
        private float eccentricity;
        private int minSize;
        private int maxSize;
        private String[] replace;
        private String with;
    }
}
