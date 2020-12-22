package genelectrovise.magiksmostevile.common.world.gen.structure.shrine2;

import java.util.function.Function;
import com.mojang.datafixers.Dynamic;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.reference.StructureReference;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class Shrine2 extends ScatteredStructure<NoFeatureConfig> {

  public static IStructurePieceType CENTRE_PIECE = null;

  public Shrine2(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
    super(configFactoryIn);
  }

  @Override
  protected int getSeedModifier() {
    return 3457537;
  }

  @Override
  public IStartFactory getStartFactory() {
    return Shrine2.Start::new;
  }

  @Override
  public String getStructureName() {
    return StructureReference.SHRINE_LOC.toString();
  }

  @Override
  public int getSize() {
    return 1;
  }

  public class Start extends StructureStart {

    public Start(Structure<?> structureIn, int int_1, int int_2, MutableBoundingBox mutableBB,
        int int_3, long long_1) {
      super(structureIn, int_1, int_2, mutableBB, int_3, long_1);
      MagiksMostEvile.LOGGER.debug("==5a== Starting shrine structure!");
    }

    @Override
    public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX,
        int chunkZ, Biome biomeIn) {
      MagiksMostEvile.LOGGER.debug("==5b== Init shrine structure!");
      int worldX = chunkX * 16;
      int worldZ = chunkZ * 16;
      BlockPos blockpos = new BlockPos(worldX, 0, worldZ);
      this.components.add(new ShrinePiece.ShrineCentrePiece(templateManagerIn,
          StructureReference.SHRINE_LOC, blockpos, Rotation.NONE, -5));
      this.recalculateStructureSize();
    }

  }

}
