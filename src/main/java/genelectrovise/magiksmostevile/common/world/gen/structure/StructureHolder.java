package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.HashMap;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.StructureFeatureHolder;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;

public class StructureHolder<T extends Structure<FC>, FC extends IFeatureConfig> {

  private RegistryObject<Structure<FC>> structure;
  private Decoration stage;
  private final HashMap<String, StructureFeatureHolder<FC, ? extends Structure<FC>>> features =
      new HashMap<>();

  public StructureHolder(RegistryObject<Structure<FC>> structure, Decoration stage) {
    this.structure = structure;
    this.stage = stage;
  }

  /**
   * Automatically registers a structure feature of the given name (structure_id + _ + feature_name)
   * 
   * @param name
   * @param configuration
   * @return The configured feature
   */
  public StructureFeatureHolder<FC, Structure<FC>> withStructureFeature(String name,
      FC configuration, EnumFeatureLocation... enumFeatureLocations) {

    StructureFeature<FC, ? extends Structure<FC>> rawStructureFeature =
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name,
            structure.get().withConfiguration(configuration));

    StructureFeatureHolder<FC, Structure<FC>> holder =
        new StructureFeatureHolder<FC, Structure<FC>>(rawStructureFeature, enumFeatureLocations);

    features.put(structure.getId() + "_" + name, holder);

    return holder;
  }

  //

  public RegistryObject<Structure<FC>> getStructure() {
    return structure;
  }

  public Decoration getStage() {
    return stage;
  }

  public HashMap<String, StructureFeatureHolder<FC, ? extends Structure<FC>>> getFeatures() {
    return features;
  }

}
