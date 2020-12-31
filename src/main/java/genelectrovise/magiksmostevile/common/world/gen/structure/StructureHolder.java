package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.HashMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;

public class StructureHolder<T extends Structure<FC>, FC extends IFeatureConfig> {

  private RegistryObject<Structure<FC>> structure;
  private Decoration stage;
  private HashMap<String, FC> configurations;
  private HashMap<String, StructureFeature<FC, ? extends Structure<FC>>> features;

  public StructureHolder(RegistryObject<Structure<FC>> structure, Decoration stage) {
    this.structure = structure;
    this.stage = stage;
  }

  public void withConfiguration(String name, FC configuration) {
    configurations.put(name, configuration);
  }

  /**
   * Automatically registers a structure feature of the given name (structure_id + _ +
   * feature_name)
   * 
   * @param name
   * @param configuration
   * @return The configured feature
   */
  public StructureFeature<FC, ? extends Structure<FC>> withStructureFeature(String name,
      FC configuration) {
    return features.put(structure.getId() + "_" + name,
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name,
            structure.get().withConfiguration(configuration)));
  }

  //

  public RegistryObject<Structure<FC>> getStructure() {
    return structure;
  }

  public Decoration getStage() {
    return stage;
  }

  public HashMap<String, FC> getConfigurations() {
    return configurations;
  }

}
