package genelectrovise.magiksmostevile.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TestJunk {

  static DeferredRegister<Structure<?>> STRUCTURES =
      DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MagiksMostEvile.MODID);

  static List<Structure<?>> STRUCTURE_LIST = new ArrayList<>();
  static Map<ResourceLocation, StructureSeparationSettings> STRUCTURE_SETTINGS = new HashMap<>();

  static IStructurePieceType register(IStructurePieceType type, String name) {
    net.minecraft.util.registry.Registry.register(
        net.minecraft.util.registry.Registry.STRUCTURE_PIECE,
        new ResourceLocation(MagiksMostEvile.MODID, name), type);
    return type;
  }

  static <C extends IFeatureConfig, S extends Structure<C>> StructureFeature<C, S> register(
      StructureFeature<C, S> feature, String name) {
    WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE,
        new ResourceLocation(MagiksMostEvile.MODID, name), feature);
    return feature;
  }

  static <C extends IFeatureConfig> RegistryObject<Structure<C>> addStructure(String name,
      Structure<C> structure, GenerationStage.Decoration stage,
      StructureSeparationSettings settings) {
    Structure.NAME_STRUCTURE_BIMAP.put(MagiksMostEvile.MODID + ":" + name, structure);
    Structure.STRUCTURE_DECORATION_STAGE_MAP.put(structure, stage);
    STRUCTURE_LIST.add(structure);
    STRUCTURE_SETTINGS.put(new ResourceLocation(MagiksMostEvile.MODID, name), settings);
    if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
      Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder()
          .addAll(Structure.field_236384_t_).add(structure).build();
    }
    return STRUCTURES.register(name, () -> structure);
  }

  public static IStructurePieceType SKY_DUNGEON_PIECE;
  public static IStructurePieceType SPACE_DUNGEON_PIECE;
  public static IStructurePieceType NETHER_DUNGEON_PIECE;
  public static IStructurePieceType UNDERGROUND_DUNGEON_PIECE;
  public static IStructurePieceType TREE_TOP_PIECE;
  public static IStructurePieceType SEA_CAVE_PIECE;

  public static RegistryObject<Structure<NoFeatureConfig>> SKY_DUNGEON_STRUCTURE =
      addStructure("sky_dungeon", new SkyDungeonStructure(NoFeatureConfig.field_236558_a_),
          GenerationStage.Decoration.RAW_GENERATION, new StructureSeparationSettings(7, 6, 1236));

  public static RegistryObject<Structure<NoFeatureConfig>> SPACE_DUNGEON_STRUCTURE =
      addStructure("space_dungeon", new SpaceDungeonStructure(NoFeatureConfig.field_236558_a_),
          GenerationStage.Decoration.RAW_GENERATION, new StructureSeparationSettings(9, 5, 1337));

  public static RegistryObject<Structure<NoFeatureConfig>> NETHER_DUNGEON_STRUCTURE =
      addStructure("nether_dungeon", new NetherDungeonStructure(NoFeatureConfig.field_236558_a_),
          GenerationStage.Decoration.RAW_GENERATION, new StructureSeparationSettings(6, 3, 1438));

  public static RegistryObject<Structure<NoFeatureConfig>> UNDERGROUND_DUNGEON_STRUCTURE =
      addStructure("underground_dungeon",
          new UndergroundDungeonStructure(NoFeatureConfig.field_236558_a_),
          GenerationStage.Decoration.RAW_GENERATION, new StructureSeparationSettings(8, 6, 1539));

  public static RegistryObject<Structure<NoFeatureConfig>> TREE_TOP_STRUCTURE = addStructure(
      "tree_top", new TreeTopStructure(NoFeatureConfig.field_236558_a_),
      GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(4, 3, 1640));

  public static RegistryObject<Structure<NoFeatureConfig>> SEA_CAVE_STRUCTURE =
      addStructure("sea_cave", new SeaCaveStructure(NoFeatureConfig.field_236558_a_),
          GenerationStage.Decoration.RAW_GENERATION, new StructureSeparationSettings(8, 6, 1741));

  public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> SKY_DUNGEON_FEATURE;
  public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> SPACE_DUNGEON_FEATURE;
  public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_DUNGEON_FEATURE;
  public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> UNDERGROUND_DUNGEON_FEATURE;
  public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> TREE_TOP_FEATURE;
  public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> SEA_CAVE_FEATURE;

  public static void preInit() {
    STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public static void init() {
    SKY_DUNGEON_PIECE = register(SkyDungeonStructure.Piece::new, "sky_dungeon");
    SKY_DUNGEON_FEATURE =
        register(SKY_DUNGEON_STRUCTURE.get().withConfiguration(NoFeatureConfig.field_236559_b_),
            "sky_dungeon");
    SPACE_DUNGEON_PIECE = register(SpaceDungeonStructure.Piece::new, "space_dungeon");
    SPACE_DUNGEON_FEATURE =
        register(SPACE_DUNGEON_STRUCTURE.get().withConfiguration(NoFeatureConfig.field_236559_b_),
            "space_dungeon");
    UNDERGROUND_DUNGEON_PIECE =
        register(UndergroundDungeonStructure.Piece::new, "underground_dungeon");
    UNDERGROUND_DUNGEON_FEATURE = register(
        UNDERGROUND_DUNGEON_STRUCTURE.get().withConfiguration(NoFeatureConfig.field_236559_b_),
        "underground_dungeon");
    TREE_TOP_PIECE = register(TreeTopStructure.Piece::new, "tree_top");
    TREE_TOP_FEATURE = register(
        TREE_TOP_STRUCTURE.get().withConfiguration(NoFeatureConfig.field_236559_b_), "tree_top");
    SEA_CAVE_PIECE = register(SeaCaveStructure.Piece::new, "sea_cave");
    SEA_CAVE_FEATURE = register(
        SEA_CAVE_STRUCTURE.get().withConfiguration(NoFeatureConfig.field_236559_b_), "sea_cave");
    NETHER_DUNGEON_PIECE = register(NetherDungeonStructure.Piece::new, "nether_dungeon");
    NETHER_DUNGEON_FEATURE =
        register(NETHER_DUNGEON_STRUCTURE.get().withConfiguration(NoFeatureConfig.field_236559_b_),
            "nether_dungeon");

    for (Structure<?> s : STRUCTURE_LIST) {
      ImmutableSet.of(DimensionSettings.field_242734_c, DimensionSettings.field_242736_e);
      DimensionStructuresSettings.field_236191_b_ =
          ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
              .putAll(DimensionStructuresSettings.field_236191_b_)
              .put(s, STRUCTURE_SETTINGS.get(s.getRegistryName())).build();
      DimensionSettings.field_242740_q.getStructures().field_236193_d_.put(s,
          STRUCTURE_SETTINGS.get(s.getRegistryName()));
    }
  }

  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onBiomeLoad(BiomeLoadingEvent event) {
    event.getGeneration().withStructure(SKY_DUNGEON_FEATURE);
    event.getGeneration().withStructure(SPACE_DUNGEON_FEATURE);
    event.getGeneration().withStructure(UNDERGROUND_DUNGEON_FEATURE);

    if (event.getCategory() == Biome.Category.FOREST || event.getCategory() == Biome.Category.PLAINS
        || event.getCategory() == Biome.Category.SAVANNA
        || event.getCategory() == Biome.Category.TAIGA
        || event.getCategory() == Biome.Category.EXTREME_HILLS) {
      event.getGeneration().withStructure(TREE_TOP_FEATURE);
    } else if (event.getCategory() == Biome.Category.OCEAN) {
      event.getGeneration().withStructure(SEA_CAVE_FEATURE);
    } else if (event.getCategory() == Biome.Category.SWAMP
        || event.getCategory() == Biome.Category.NETHER) {
      event.getGeneration().withStructure(NETHER_DUNGEON_FEATURE);
    }

  }
}
