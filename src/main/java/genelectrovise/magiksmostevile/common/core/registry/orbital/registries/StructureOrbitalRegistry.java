package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import java.util.HashMap;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.StructureData;
import genelectrovise.magiksmostevile.common.world.gen.structure.StructureHolder;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// https://forums.minecraftforge.net/topic/93506-1163-generate-structure-in-custom-biome/

public class StructureOrbitalRegistry implements IOrbitalRegistry {

  // Data containers

  // Auto put structures in the main map
  @SuppressWarnings("serial")
  public static final HashMap<StructureHolder<?, ?>, StructureData> EVILE_STRUCTURES =

      new HashMap<StructureHolder<?, ?>, StructureData>() {

        @Override
        public StructureData put(StructureHolder<?, ?> structure, StructureData data) {
          Structure.NAME_STRUCTURE_BIMAP.put(structure.getStructure().getId().toString(),
              structure.getStructure().get());
          return super.put(structure, data);
        };
      };

  // Registers
  public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES =
      DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MagiksMostEvile.MODID);

  // Declarations
  public static final StructureHolder<OvergroundShrineStructure, OvergroundShrineFeatureConfig> OVERGROUND_SHRINE =
      registerStructure("overground_shrine", new OvergroundShrineStructure(),
          GenerationStage.Decoration.SURFACE_STRUCTURES);

  public static final StructureFeatureHolder<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DEFAULT =
      OVERGROUND_SHRINE.withStructureFeature("default",
          new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT));

  // Methods

  public static <T extends Structure<FC>, FC extends IFeatureConfig> StructureHolder<T, FC> registerStructure(
      String name, Structure<FC> structure, GenerationStage.Decoration stage) {

    StructureHolder<T, FC> structureHolder =
        new StructureHolder<T, FC>(STRUCTURE_FEATURES.register(name, () -> structure), stage);

    StructureOrbitalRegistry.EVILE_STRUCTURES.put(structureHolder, new StructureData(name, stage));

    return structureHolder;
  }

  // Orbital boilerplate

  @Override
  public String name() {
    return "structures";
  }


  @Override
  public int priority() {
    return 14;
  }


  @Override
  public void initialise() {
    STRUCTURE_FEATURES.register(MagiksMostEvile.MOD_EVENT_BUS);
  }
}
