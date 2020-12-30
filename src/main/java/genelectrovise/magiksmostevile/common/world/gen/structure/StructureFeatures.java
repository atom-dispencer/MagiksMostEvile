package genelectrovise.magiksmostevile.common.world.gen.structure;

import static net.minecraft.world.biome.Biome.Category.DESERT;
import static net.minecraft.world.biome.Biome.Category.EXTREME_HILLS;
import static net.minecraft.world.biome.Biome.Category.ICY;
import static net.minecraft.world.biome.Biome.Category.JUNGLE;
import static net.minecraft.world.biome.Biome.Category.NETHER;
import static net.minecraft.world.biome.Biome.Category.NONE;
import static net.minecraft.world.biome.Biome.Category.OCEAN;
import static net.minecraft.world.biome.Biome.Category.SWAMP;
import static net.minecraft.world.biome.Biome.Category.THEEND;
import java.util.HashMap;
import java.util.Map;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.StructureOrbitalRegistry;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Shrine extension of {@link StructureAspectRegistrationManager}
 * 
 * @author GenElectrovise
 *
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class StructureFeatures {

  public static Map<StructureFeature<?, ?>, Biome.Category[]> map =
      new HashMap<StructureFeature<?, ?>, Biome.Category[]>();

  // Overground Shrine
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
    // Default
    OVERGROUND_SHRINE_DEFAULT = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/default",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT)));

    // Desert
    OVERGROUND_SHRINE_DESERT = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/desert",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DESERT)));
    // End
    OVERGROUND_SHRINE_END = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/end", StructureOrbitalRegistry.OVERGROUND_SHRINE
            .get().withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.END)));
    // Jungle
    OVERGROUND_SHRINE_JUNGLE = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/jungle",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.JUNGLE)));
    // Mountain
    OVERGROUND_SHRINE_MOUNTAIN = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/mountain",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.MOUNTAIN)));
    // Nether
    OVERGROUND_SHRINE_NETHER = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/nether",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.NETHER)));
    // Ocean
    OVERGROUND_SHRINE_OCEAN = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/ocean",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.OCEAN)));
    // Swamp
    OVERGROUND_SHRINE_SWAMP = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/swamp",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
            .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.SWAMP)));
    // Tundra
    OVERGROUND_SHRINE_TUNDRA = StructureAspectRegistrationManager.registerStructureFeature(
        MagiksMostEvile.MODID + ":overground_shrine/tundra",
        StructureOrbitalRegistry.OVERGROUND_SHRINE.get()
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
