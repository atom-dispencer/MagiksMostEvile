package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.RuinedPortalStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

/**
 * See also {@link RuinedPortalStructure}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineStructure extends Structure<OvergroundShrineFeatureConfig> {

  public static final int bigVariants = 1;
  public static final int smallVariants = 2;
  public static final int mediumVariants = 3;

  public OvergroundShrineStructure() {
    super(OvergroundShrineFeatureConfig.CODEC);
  }

  @Override
  public IStartFactory<OvergroundShrineFeatureConfig> getStartFactory() {
    return OvergroundShrineStructure.Start::new;
  }

  public net.minecraft.world.gen.GenerationStage.Decoration getDecorationStage() {
    return Decoration.SURFACE_STRUCTURES;
  };

  public static class Start extends StructureStart<OvergroundShrineFeatureConfig> {

    public Start(Structure<OvergroundShrineFeatureConfig> structure, int chunkPosX, int chunkPosZ,
        MutableBoundingBox bounds, int references, long seed) {
      super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
    }

    /**
     * <ul>
     * <li>Sets serializer variables to customise the structure aesthetics to the location.
     * <li>Select a random variant of the structure
     * <li>Piece together data for the structure
     * <li>Create {@value components} list of {@link StructurePiece}s. I suppose these will then be
     * built automatically.
     * </ul>
     */
    @Override
    public void func_230364_a_(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator,
        TemplateManager templateManager, int chunkPosX, int chunkPosZ, Biome biome,
        OvergroundShrineFeatureConfig configuration) {

      OvergroundShrineStructureAestheticsSerializer serializer =
          new OvergroundShrineStructureAestheticsSerializer();
      EnumFeatureLocation location = configuration.location;

      // Configure aesthetics (block replacement)
      switch (location) {
        case DEFAULT:
          break;
        case DESERT:
          break;
        case END:
          break;
        case JUNGLE:
          break;
        case MOUNTAIN:
          break;
        case NETHER:
          break;
        case OCEAN:
          break;
        case SWAMP:
          break;
        case TUNDRA:
          break;
        default:
          break;
      }

      // Get the location of the template of the structure, i.e. chosen randomly to determine size
      ResourceLocation templateName;

      float randomisedFloat = this.rand.nextFloat();
      // Make it big (5%)
      if (randomisedFloat < 0.05F) {
        templateName = formatTemplateName(Size.LARGE);
      }
      // Make it medium (25%)
      else if (randomisedFloat < 0.35) {
        templateName = formatTemplateName(Size.MEDIUM);
      }
      // Make it small (65%)
      else {
        templateName = formatTemplateName(Size.SMALL);
      }

      // Create basic final data
      Template template = templateManager.getTemplateDefaulted(templateName);
      Rotation rotation = Util.getRandomObject(Rotation.values(), rand);
      Mirror mirror = Util.getRandomObject(Mirror.values(), rand);
      BlockPos truePosition =
          new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
      BlockPos chunkBlockPos = new ChunkPos(chunkPosX, chunkPosZ).asBlockPos();
      MutableBoundingBox mutableboundingbox =
          template.func_237150_a_(chunkBlockPos, rotation, truePosition, mirror);

      // Create bounding boxes
      Vector3i vector3i = mutableboundingbox.func_215126_f();
      int x = vector3i.getX();
      int z = vector3i.getZ();
      int y =
          chunkGenerator.getHeight(x, z, OvergroundShrineStructurePiece.getHeightMapType(location))
              - 1;
      BlockPos finalPlacementPosition = new BlockPos(x, y, z);

      // Is the position cold?
      if (configuration.location == EnumFeatureLocation.MOUNTAIN
          || configuration.location == EnumFeatureLocation.OCEAN
          || configuration.location == EnumFeatureLocation.TUNDRA
          || configuration.location == EnumFeatureLocation.END) {

        serializer.isCold = isPlacementPositionCold(finalPlacementPosition, biome);
      }

      this.components.add(new OvergroundShrineStructurePiece(finalPlacementPosition, location,
          serializer, templateName, template, rotation, mirror, truePosition));
      this.recalculateStructureSize();
    }

    public static boolean isPlacementPositionCold(BlockPos pos, Biome biome) {
      return biome.getTemperature(pos) < 0.15F;
    }

    private ResourceLocation formatTemplateName(Size size) {

      int variantCount;

      switch (size) {
        case LARGE:
          variantCount = bigVariants;
          break;
        case MEDIUM:
          variantCount = mediumVariants;
          break;
        case SMALL:
          variantCount = smallVariants;
          break;
        default:
          variantCount = mediumVariants;
          break;
      }

      StringBuilder builder = new StringBuilder("overground_shrine/");
      // overground_shrine/

      if (size == Size.SMALL) {
        builder.append("small");
      } else if (size == Size.MEDIUM) {
        builder.append("large");
      } else if (size == Size.LARGE) {
        builder.append("large");
      }
      // overground_shrine/large

      builder.append("_");
      // overground_shrine/large_

      int choice = this.rand.nextInt(variantCount);
      builder.append(choice);
      // overground_shrine/large_0

      return new ResourceLocation(MagiksMostEvile.MODID, builder.toString());
    }

  }

  private static enum Size {
    SMALL, MEDIUM, LARGE;
  }

}
