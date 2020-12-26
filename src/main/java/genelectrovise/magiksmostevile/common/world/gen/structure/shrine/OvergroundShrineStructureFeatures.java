package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.StructureAspectRegistrationManager;
import genelectrovise.magiksmostevile.common.world.gen.structure.Structures;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

/**
 * Shrine extension of {@link StructureAspectRegistrationManager}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineStructureFeatures {

  // Default
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DEFAULT =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/default",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT)));
  // Desert
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_DESERT =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/desert",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DESERT)));
  // End
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_END =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/end",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.END)));
  // Jungle
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_JUNGLE =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/jungle",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.JUNGLE)));
  // Mountain
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_MOUNTAIN =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/mountain",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.MOUNTAIN)));
  // Nether
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_NETHER =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/nether",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.NETHER)));
  // Ocean
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_OCEAN =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/ocean",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.OCEAN)));
  // Swamp
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_SWAMP =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/swamp",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.SWAMP)));
  // Tundra
  public static final StructureFeature<OvergroundShrineFeatureConfig, ? extends Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE_TUNDRA =
      StructureAspectRegistrationManager.registerStructureFeature(MagiksMostEvile.MODID + ":overground_shrine/tundra",
          Structures.OVERGROUND_SHRINE
              .withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.TUNDRA)));

}
