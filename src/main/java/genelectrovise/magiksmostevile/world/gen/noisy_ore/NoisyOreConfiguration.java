package genelectrovise.magiksmostevile.world.gen.noisy_ore;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class NoisyOreConfiguration {

    private Settings normal;
    private SituationalSettings dimensions;
    private SituationalSettings biomes;

    @Data
    @AllArgsConstructor
    public static class Settings {
        private int level;
        private float eccentricity;
        private int minSize;
        private int maxSize;
    }

    @Data
    @AllArgsConstructor
    public static class SituationalSettings {
        private String[] exclude;
        private Map<String, Settings> settings;
    }
}
