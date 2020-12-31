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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// https://forums.minecraftforge.net/topic/93506-1163-generate-structure-in-custom-biome/

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class StructureOrbitalRegistry implements IOrbitalRegistry {

  // Data containers

  // Auto put structures in the main map
  public static final HashMap<StructureHolder<?, ?>, StructureData> EVILE_STRUCTURES =
      new HashMap<StructureHolder<?, ?>, StructureData>();

  // Registers
  public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES =
      DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MagiksMostEvile.MODID);

  // Declarations
  public static final StructureHolder<OvergroundShrineStructure, OvergroundShrineFeatureConfig> OVERGROUND_SHRINE =
      registerStructure("overground_shrine", new OvergroundShrineStructure(),
          GenerationStage.Decoration.SURFACE_STRUCTURES);

  public static StructureFeatureHolder<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DEFAULT;

  // Methods

  public static <T extends Structure<FC>, FC extends IFeatureConfig> StructureHolder<T, FC> registerStructure(
      String name, Structure<FC> structure, GenerationStage.Decoration stage) {

    StructureHolder<T, FC> structureHolder =
        new StructureHolder<T, FC>(STRUCTURE_FEATURES.register(name, () -> structure), stage);

    StructureOrbitalRegistry.EVILE_STRUCTURES.put(structureHolder, new StructureData(name, stage));

    return structureHolder;
  }

  /**
   * Adds evile structures to the main Structures name bimap
   * 
   * @param event
   */
  @SubscribeEvent
  public static void completeStructureRegistration(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {

      // Add to BIMAP
      EVILE_STRUCTURES.forEach((holder, data) -> {
        Structure.NAME_STRUCTURE_BIMAP.put(holder.getStructure().getId().toString(),
            holder.getStructure().get());
      });

      // Populate features
      OVERGROUND_SHRINE_DEFAULT = OVERGROUND_SHRINE.withStructureFeature("default",
          new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT));

    });
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
