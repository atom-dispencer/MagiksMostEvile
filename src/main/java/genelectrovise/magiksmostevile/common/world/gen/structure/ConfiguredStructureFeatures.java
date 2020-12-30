package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.StructureOrbitalRegistry;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

public class ConfiguredStructureFeatures {
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DEFAULT =
      StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
          .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT));
}
