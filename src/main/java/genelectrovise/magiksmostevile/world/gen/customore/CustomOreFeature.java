package genelectrovise.magiksmostevile.world.gen.customore;

import com.google.common.primitives.Ints;
import com.mojang.serialization.Codec;
import genelectrovise.magiksmostevile.core.reference.WorldReference;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * {@link net.minecraft.world.gen.feature.Features}
 */
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
    public boolean place(ISeedReader iSeedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, BlockPos blockPos, @Nonnull NoFeatureConfig noFeatureConfig) {
        LOGGER.info("100321 Placing gold block at " + blockPos.toShortString());

        final int TRIALS = 10;
        final float PROBABILITY = 0.1f;

        RandomGenerator randomGenerator = new JDKRandomGenerator();
        randomGenerator.setSeed(iSeedReader.getSeed());

        final int desiredSpawnLevel = 50;
        final float eccentricity = 1f;
        int y = generateSpawnHeight(desiredSpawnLevel, eccentricity, randomGenerator);

        BlockPos realPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
        iSeedReader.setBlock(realPos, Blocks.GOLD_BLOCK.defaultBlockState(), 4);
        return false;
    }

    /**
     * Uses a binomial distribution to generate a height near to the desiredSpawnLevel, with some leniency around it.
     *
     * @param desiredSpawnLevel The desired height for an ore to spawn at.
     * @param eccentricity      Increases the irregularity of height by reducing accuracy.
     * @return A value for spawning height
     */
    protected int generateSpawnHeight(int desiredSpawnLevel, float eccentricity, RandomGenerator randomGenerator) {

        // World height params
        final int maxY = WorldReference.WORLD_MAX_Y;
        final int minY = WorldReference.WORLD_MIN_Y;
        final int worldHeight = maxY - minY; // Distance from min to max

        // Spawning level
        final int howFarAboveMinY = desiredSpawnLevel - minY; // Dist from minY to desiredLevel
        final double percentageOfMaxHeight = (double) howFarAboveMinY / (double) worldHeight;

        // Distribution
        BinomialDistribution binomialDistribution = new BinomialDistribution(randomGenerator, (int) (worldHeight / eccentricity), percentageOfMaxHeight);

        // Scale
        int sample = binomialDistribution.sample();
        int scaled = (int) (sample * eccentricity);
        return Ints.constrainToRange(scaled, minY, maxY);
    }
}