package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.HashMap;
import java.util.Map;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import static net.minecraft.world.biome.Biome.Category.*;

/**
 * Shrine extension of {@link StructureAspectRegistrationManager}
 * 
 * @author GenElectrovise
 *
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class StructuresAndStructureFeatures {

  public static Map<StructureFeature<?, ?>, Biome.Category[]> map =
      new HashMap<StructureFeature<?, ?>, Biome.Category[]>();

  // Overground Shrine
  public static Structure<OvergroundShrineFeatureConfig> OVERGROUND_SHRINE;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DEFAULT;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DESERT;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_END;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_JUNGLE;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_MOUNTAIN;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_NETHER;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_OCEAN;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_SWAMP;
  public static StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_TUNDRA;

  @SubscribeEvent
  public static void createStructuresAndStructureFeatures(FMLCommonSetupEvent event) {

    // Overground Shrine
    // ======================================================================================================================
    OVERGROUND_SHRINE =
        StructureAspectRegistrationManager.registerStructure("MagiksMostEvile_Overground_Shrine",
            new OvergroundShrineStructure(), GenerationStage.Decoration.SURFACE_STRUCTURES);
    // Default
    OVERGROUND_SHRINE_DEFAULT = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/default", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT)));
    // Desert
    OVERGROUND_SHRINE_DESERT = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/desert", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DESERT)));
    // End
    OVERGROUND_SHRINE_END = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/end", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.END)));
    // Jungle
    OVERGROUND_SHRINE_JUNGLE = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/jungle", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.JUNGLE)));
    // Mountain
    OVERGROUND_SHRINE_MOUNTAIN = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/mountain", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.MOUNTAIN)));
    // Nether
    OVERGROUND_SHRINE_NETHER = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/nether", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.NETHER)));
    // Ocean
    OVERGROUND_SHRINE_OCEAN = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/ocean", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.OCEAN)));
    // Swamp
    OVERGROUND_SHRINE_SWAMP = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/swamp", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.SWAMP)));
    // Tundra
    OVERGROUND_SHRINE_TUNDRA = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/tundra", OVERGROUND_SHRINE
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.TUNDRA)));

    createLists();
  }

  private static void createLists() {

    // Overground Shrine
    map.put(OVERGROUND_SHRINE_DEFAULT, new Biome.Category[] {NONE});
    map.put(OVERGROUND_SHRINE_DESERT, new Biome.Category[] {DESERT});
    map.put(OVERGROUND_SHRINE_END, new Biome.Category[] {THEEND});
    map.put(OVERGROUND_SHRINE_JUNGLE, new Biome.Category[] {JUNGLE});
    map.put(OVERGROUND_SHRINE_MOUNTAIN, new Biome.Category[] {EXTREME_HILLS});
    map.put(OVERGROUND_SHRINE_NETHER, new Biome.Category[] {NETHER});
    map.put(OVERGROUND_SHRINE_MOUNTAIN, new Biome.Category[] {OCEAN});
    map.put(OVERGROUND_SHRINE_SWAMP, new Biome.Category[] {SWAMP});
    map.put(OVERGROUND_SHRINE_TUNDRA, new Biome.Category[] {ICY});
  }
}
