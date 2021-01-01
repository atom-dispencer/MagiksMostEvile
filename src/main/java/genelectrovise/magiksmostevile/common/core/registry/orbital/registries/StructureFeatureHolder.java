package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import java.util.Arrays;
import java.util.Collection;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureFeatureHolder<FC extends IFeatureConfig, T extends Structure<FC>> {

  private StructureFeature<FC, ? extends Structure<FC>> feature;
  private Collection<EnumFeatureLocation> categories;

  public StructureFeatureHolder(StructureFeature<FC, ? extends Structure<FC>> feature,
      EnumFeatureLocation... enumFeatureLocations) {

    this.feature = feature;
    this.categories = Arrays.asList(enumFeatureLocations);
  }

  public StructureFeature<FC, ? extends Structure<FC>> getFeature() {
    return feature;
  }

  public Collection<EnumFeatureLocation> getLocations() {
    return categories;
  }

}
