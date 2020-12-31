package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Lists;
import com.mojang.serialization.Dynamic;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.StructureUtil;
import genelectrovise.magiksmostevile.common.world.gen.structure.registry.StructurePieces;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.RuinedPortalPiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlackStoneReplacementProcessor;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.BlockMosinessProcessor;
import net.minecraft.world.gen.feature.template.LavaSubmergingProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.RuleEntry;
import net.minecraft.world.gen.feature.template.RuleStructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

/**
 * {@link RuinedPortalPiece}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineStructurePiece extends TemplateStructurePiece {
  private static final Logger LOGGER = LogManager.getLogger();
  private final ResourceLocation templateName;
  private final Rotation rotation;
  private final Mirror mirror;
  private final EnumFeatureLocation location;
  private final OvergroundShrineStructureAestheticsSerializer serializer;


  /**
   * Called from the {@link OvergroundShrineStructure} class.
   * 
   * @param blockPos
   * @param location
   * @param serializer
   * @param templateName
   * @param template
   * @param rotation
   * @param mirror
   * @param truePosition
   */
  public OvergroundShrineStructurePiece(BlockPos blockPos, EnumFeatureLocation location,
      OvergroundShrineStructureAestheticsSerializer serializer, ResourceLocation templateName,
      Template template, Rotation rotation, Mirror mirror, BlockPos truePosition) {
    super(StructurePieces.OVERGROUND_SHRINE_PIECE, 0);
    this.templatePosition = blockPos;
    this.templateName = templateName;
    this.rotation = rotation;
    this.mirror = mirror;
    this.location = location;
    this.serializer = serializer;
    this.setupPlacement(template, truePosition);
  }

  /**
   * Used to read from nbt
   * 
   * @param templateManager
   * @param nbt
   */
  public OvergroundShrineStructurePiece(TemplateManager templateManager, CompoundNBT nbt) {
    super(IStructurePieceType.RUINED_PORTAL, nbt);

    // Basic
    this.templateName = new ResourceLocation(nbt.getString("Template"));
    this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
    this.mirror = Mirror.valueOf(nbt.getString("Mirror"));
    this.location = EnumFeatureLocation.get(nbt.getString("Location"));

    // Properties
    this.serializer = OvergroundShrineStructureAestheticsSerializer.CODEC
        .parse(new Dynamic<>(NBTDynamicOps.INSTANCE, nbt.get("Properties")))
        .getOrThrow(true, LOGGER::error);

    // Fetch Template
    Template template = templateManager.getTemplateDefaulted(this.templateName);

    // Setup
    this.setupPlacement(template,
        new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2));
  }

  private void setupPlacement(Template template, BlockPos centerOffset) {

    // Add processor to read the NBT ignoring structure blocks and air
    BlockIgnoreStructureProcessor blockignorestructureprocessor =
        BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK;

    // Create a list of rules for replacing blocks
    List<RuleEntry> list = Lists.newArrayList();

    // Replace
    list.add(StructureUtil.randomBlockReplacementRule(Blocks.STONE_BRICKS, 0.2f, Blocks.AIR));
    if (!this.serializer.isCold) {
      list.add(
          StructureUtil.randomBlockReplacementRule(Blocks.NETHERRACK, 0.07F, Blocks.MAGMA_BLOCK));
    }

    PlacementSettings placementsettings = new PlacementSettings() // New
        .setRotation(this.rotation) // Rotation
        .setMirror(this.mirror) // Mirror
        .setCenterOffset(centerOffset) // Center offset
        .addProcessor(blockignorestructureprocessor) // Processor (ignore some)
        .addProcessor( // Add rules
            new RuleStructureProcessor(list)) // > from list
        .addProcessor(// Mossiness
            new BlockMosinessProcessor( // > Processor
                this.serializer.mossiness)) // > serializer
        .addProcessor( // Lava
            new LavaSubmergingProcessor()); // > +water

    // Replace stone variants with blackstone
    if (this.serializer.replaceWithBlackstone) {
      placementsettings.addProcessor(BlackStoneReplacementProcessor.field_237058_b_);
    }

    this.setup(template, this.templatePosition, placementsettings);
  }

  /**
   * (abstract) Called on loading the structure to read data from the java to the NBT tag!
   */
  protected void readAdditional(CompoundNBT tagCompound) {
    super.readAdditional(tagCompound);
    // Basic
    tagCompound.putString("Template", this.templateName.toString());
    tagCompound.putString("Rotation", this.rotation.name());
    tagCompound.putString("Mirror", this.mirror.name());
    tagCompound.putString("Location", this.location.getName());

    // Properties
    OvergroundShrineStructureAestheticsSerializer.CODEC
        .encodeStart(NBTDynamicOps.INSTANCE, this.serializer).resultOrPartial(LOGGER::error)
        .ifPresent((inbt) -> {
          tagCompound.put("Properties", inbt);
        });
  }

  public static Heightmap.Type getHeightMapType(EnumFeatureLocation location) {
    return location == EnumFeatureLocation.OCEAN ? Heightmap.Type.OCEAN_FLOOR_WG
        : Heightmap.Type.WORLD_SURFACE_WG;
  }

  @Override
  protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand,
      MutableBoundingBox sbb) {
    // Nothing in RuinedPortalPiece, used by the igloo to give its chest a loot table.
  }

}
