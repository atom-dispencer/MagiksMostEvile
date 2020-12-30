package genelectrovise.magiksmostevile.common.world.gen.structure;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureAndSFeaturePair<S extends Structure<C>, C extends IFeatureConfig> {
  private final Structure<C> structure;
  private final StructureFeature<C, S> feature;

  public StructureAndSFeaturePair(Structure<C> structure, StructureFeature<C, S> feature) {
    this.structure = structure;
    this.feature = feature;
  }

  public Structure<C> getStructure() {
    return structure;
  }

  public StructureFeature<C, S> getFeature() {
    return feature;
  }
}
