package genelectrovise.magiksmostevile.common.world.gen.ore;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class SimpleConfiguredOreFeature extends ConfiguredFeature<OreFeatureConfig, OreFeature> {

  public SimpleConfiguredOreFeature(OreFeature featureIn, OreFeatureConfig configIn) {
    super(featureIn, configIn);
  }

  public static class Builder {

    private SimpleOreFeature simpleOreFeature;
    private SimpleOreFeatureConfiguration configuration;
    private String name;

    public Builder withSimpleOreFeature(SimpleOreFeature simpleOreFeature) {
      this.simpleOreFeature = simpleOreFeature;
      return this;
    }

    public Builder withSimpleOreFeatureConfiguration(SimpleOreFeatureConfiguration configuration) {
      this.configuration = configuration;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public SimpleConfiguredOreFeature build() {
      return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
          new ResourceLocation(MagiksMostEvile.MODID, name),
          new SimpleConfiguredOreFeature(simpleOreFeature, configuration));
    }
  }

}
