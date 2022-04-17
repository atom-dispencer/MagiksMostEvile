package genelectrovise.magiksmostevile.world.noisy_ore;

import com.google.common.primitives.Ints;
import genelectrovise.magiksmostevile.core.reference.WorldReference;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class NoisyOrePlacementHandler {

    public static final Logger LOGGER = LogManager.getLogger(NoisyOrePlacementHandler.class);

    /**
     * Places a {@link NoisyOreFeature} in the world. Abstracted from {@link NoisyOreFeature#place(ISeedReader, ChunkGenerator, Random, BlockPos, NoFeatureConfig)}
     * <br><br> Steps:
     * <ol>
     *     <li>Get placement settings
     *     <li>  Get biome probability
     *     <li>Get simplex probability modifier
     *     <li>Collect overall probability modifier
     *     <li>Check spawn attempt
     *     <li>IF TRUE
     *     <li>Generate binomial height
     *     <li>Place ore
     * </ol>
     *
     * @param iSeedReader     Superclass of World.
     * @param chunkGenerator  The instance generating the terrain we are in.
     * @param random          The global instance of random. Should be wrapped in a {@link RandomGenerator}, for example using {@link RandomGeneratorFactory}.
     * @param blockPos        The N/W (-X, -Z) corner of the chunk in we are working in.
     * @param noFeatureConfig Configuration for {@link NoisyOreFeature}. Currently, {@link NoFeatureConfig}.
     * @return A boolean for something - no idea what. Just returning false seems to work fine I guess.
     */
    @ParametersAreNonnullByDefault
    public static boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, NoFeatureConfig noFeatureConfig) {

        RandomGenerator randomGenerator = RandomGeneratorFactory.createRandomGenerator(random);

        // new SimplexNoiseGenerator(random).getValue();

        final int desiredSpawnLevel = 50;
        final float eccentricity = 1f;

        int y = generateSpawnHeight(desiredSpawnLevel, eccentricity, randomGenerator);

        BlockPos realPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());

        LOGGER.info("100321 Placing gold block at " + realPos.toShortString());
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
    @ParametersAreNonnullByDefault
    public static int generateSpawnHeight(int desiredSpawnLevel, float eccentricity, RandomGenerator randomGenerator) {

        // World height params
        final int maxY = WorldReference.WORLD_MAX_Y;
        final int minY = WorldReference.WORLD_MIN_Y;
        final int worldHeight = maxY - minY; // Distance from min to max

        // Spawning level
        final int howFarAboveMinY = desiredSpawnLevel - minY; // Dist from minY to desiredLevel
        final double percentageOfMaxHeight = (double) howFarAboveMinY / (double) worldHeight;

        // Distribution
        BinomialDistribution binomialDistribution = new BinomialDistribution(randomGenerator, (int) Math.ceil(worldHeight / eccentricity), percentageOfMaxHeight);

        // Scale
        int sample = binomialDistribution.sample();
        int scaled = (int) (sample * eccentricity);
        return Ints.constrainToRange(scaled, minY, maxY);
    }
}