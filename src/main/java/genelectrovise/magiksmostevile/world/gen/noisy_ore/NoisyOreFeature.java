package genelectrovise.magiksmostevile.world.gen.noisy_ore;

import com.google.common.primitives.Ints;
import com.mojang.serialization.Codec;
import genelectrovise.magiksmostevile.core.reference.WorldReference;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Generates ores customly.
 * <p>
 * Configurable through datapacks/resourcepacks (see {@link NoisyOreFeature#CUSTOM_ORE_GENERATION_SETTINGS_LOCATION}).
 * <p>
 * Uses {@link net.minecraft.world.gen.SimplexNoiseGenerator} to generate a chunk-by-chunk map of probabilities of placement for each type of ore, in order to allow ores to generate in regional clusters.
 * <p>
 * Then uses a binomial distribution, mutated with an eccentricity, to generate a Y-coordinate for an ore vein.
 *
 * @see net.minecraft.world.gen.feature.Features
 */
@Getter
@Setter
public class NoisyOreFeature extends Feature<NoFeatureConfig> {

    public static final Logger LOGGER = LogManager.getLogger(NoisyOreFeature.class);

    public static final ConfiguredFeature<NoFeatureConfig, NoisyOreFeature> CONFIGURED_ORE = Registry.register( //
            WorldGenRegistries.CONFIGURED_FEATURE, //
            "magiksmostevile_custom_ore_feature", //
            new ConfiguredFeature<>(new NoisyOreFeature(NoFeatureConfig.CODEC), new NoFeatureConfig()));

    private NoisyOreConfiguration settings;

    public NoisyOreFeature(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec);
        NoisyOreConfiguration settings = NoisyOreConfigurationFactory.fromResources(Minecraft.getInstance().getResourceManager());
    }

    /**
     * @param blockPos The north-west (-X, -Z) coordinate of the chunk the feature is being placed in, at Y=0.
     */
    @Override
    public boolean place(@Nonnull ISeedReader iSeedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos blockPos, @Nonnull NoFeatureConfig noFeatureConfig) {

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
    protected int generateSpawnHeight(int desiredSpawnLevel, float eccentricity, RandomGenerator randomGenerator) {

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