package genelectrovise.magiksmostevile.common.world.gen.structure.shrine2;

import java.util.Random;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.reference.StructureReference;
import genelectrovise.magiksmostevile.common.world.gen.structure.EvileStructureType;
import genelectrovise.magiksmostevile.common.world.gen.structure.StructureDecorator;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class ShrinePiece {

  public static class ShrineCentrePiece extends TemplateStructurePiece {

    public ShrineCentrePiece(TemplateManager templateManager, CompoundNBT nbt) {
      super(Shrine2.CENTRE_PIECE, nbt);
      MagiksMostEvile.LOGGER.debug("==6== New ShrinePiece.Piece (Constructor 1)");
      this.setupTemplate(templateManager);
    }

    public ShrineCentrePiece(TemplateManager templateMgr, ResourceLocation resLoc,
        BlockPos blockPos, Rotation rot, int offsetY) {
      super(Shrine2.CENTRE_PIECE, 0);
      MagiksMostEvile.LOGGER.debug("==6== New ShrinePiece.Piece (Constructor 2)");
      this.templatePosition =
          new BlockPos(blockPos.getX(), blockPos.getY() + offsetY, blockPos.getZ());
      this.setupTemplate(templateMgr);
    }

    private void setupTemplate(TemplateManager templateManager) {
      MagiksMostEvile.LOGGER.debug("==7== Setting up template");
      Template template = templateManager.getTemplateDefaulted(StructureReference.SHRINE_LOC);
      PlacementSettings settings = (new PlacementSettings()).setRotation(Rotation.NONE)
          .setMirror(Mirror.NONE).setCenterOffset(BlockPos.ZERO)
          .addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
      this.setup(template, this.templatePosition, settings);
    }

    @Override
    public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> generator, Random rand,
        MutableBoundingBox mutableBB, ChunkPos pos) {
      MagiksMostEvile.LOGGER.debug("==4== Generating shrine at ChunkPos : " + pos);

      PlacementSettings placementSettings = new PlacementSettings().setRotation(Rotation.NONE)
          .setMirror(Mirror.NONE).setCenterOffset(BlockPos.ZERO)
          .addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);

      BlockPos blockpos1 = this.templatePosition
          .add(Template.transformedBlockPos(placementSettings, new BlockPos(3, 0, 0)));
      int groundHeight =
          worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());

      this.templatePosition = this.templatePosition.add(0, groundHeight, 0);
      boolean superReturn = super.func_225577_a_(worldIn, generator, rand, mutableBB, pos);

      int lowerY = templatePosition.getY();
      int upperY = templatePosition.getY() + 12;
      decorateShrineWithBiome(worldIn,
          template.getMutableBoundingBox(placementSettings, pos.asBlockPos().add(0, worldIn
              .getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ()), 0)),
          pos, rand, lowerY, upperY);

      return superReturn;
    }

    private void decorateShrineWithBiome(IWorld worldIn, MutableBoundingBox mutableBB, ChunkPos pos,
        Random rand, int lowerY, int upperY) {
      StructureDecorator decorator = new StructureDecorator(mutableBB, worldIn,
          new ShrineDecorationDictionary(rand, worldIn.getBiome(pos.asBlockPos()), worldIn), lowerY,
          upperY);
      decorator.decorateThroughoutBoundingBox(mutableBB, EvileStructureType.SHRINE);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
        MutableBoundingBox sbb) {
      MagiksMostEvile.LOGGER
          .debug("handleDataMarker is an unnecessary method, but must implement!");
    }

  }

}
