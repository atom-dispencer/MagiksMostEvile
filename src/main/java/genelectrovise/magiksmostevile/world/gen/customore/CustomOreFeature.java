package genelectrovise.magiksmostevile.world.gen.customore;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class CustomOreFeature extends Feature<NoFeatureConfig> {

   public static final Logger LOGGER = LogManager.getLogger(CustomOreFeature.class);

    public static final ConfiguredFeature<NoFeatureConfig, CustomOreFeature> CONFIGURED_ORE = Registry.register( //
            WorldGenRegistries.CONFIGURED_FEATURE, //
            "magiksmostevile_custom_ore_feature", //
            new ConfiguredFeature<>(new CustomOreFeature(NoFeatureConfig.CODEC), new NoFeatureConfig()));

    public CustomOreFeature(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec);
    }

    /**
     * @param blockPos The north-west (-X, -Z) coordinate of the chunk the feature is being placed in, at Y=0.
     */
    @Override
    public boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, NoFeatureConfig noFeatureConfig) {
        LOGGER.info("100321 Placing gold block at " + blockPos.toShortString());

        iSeedReader.setBlock(blockPos, Blocks.GOLD_BLOCK.defaultBlockState(), 4);
        return false;
    }
}
