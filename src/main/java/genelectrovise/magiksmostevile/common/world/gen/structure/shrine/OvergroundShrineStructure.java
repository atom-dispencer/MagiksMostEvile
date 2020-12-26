package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.RuinedPortalStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

/**
 * See also {@link RuinedPortalStructure}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineStructure extends Structure<OvergroundShrineFeatureConfig> {

  public OvergroundShrineStructure() {
    super(OvergroundShrineFeatureConfig.CODEC);
  }

  @Override
  public IStartFactory<OvergroundShrineFeatureConfig> getStartFactory() {
    return OvergroundShrineStructure.Start::new;
  }

  public static class Start extends StructureStart<OvergroundShrineFeatureConfig> {

    public Start(Structure<OvergroundShrineFeatureConfig> structure, int chunkPosX, int chunkPosZ,
        MutableBoundingBox bounds, int references, long seed) {
      super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
    }

    /**
     * Create list of {@link StructurePiece}s. I suppose these will then be built automatically.
     */
    @Override
    public void func_230364_a_(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator,
        TemplateManager templateManager, int p_230364_4_, int p_230364_5_, Biome biome,
        OvergroundShrineFeatureConfig configuration) {

    }

  }

}
