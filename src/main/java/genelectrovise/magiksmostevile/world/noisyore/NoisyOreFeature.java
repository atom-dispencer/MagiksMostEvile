package genelectrovise.magiksmostevile.world.noisyore;

import com.mojang.serialization.Codec;
import lombok.Getter;
import lombok.Setter;
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

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Generates ores customly.
 * <p>
 * Configurable through datapacks/resourcepacks.
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
    }

    /**
     * @param blockPos The north-west (-X, -Z) coordinate of the chunk the feature is being placed in, at Y=0.
     */
    @Override
    public boolean place(@Nonnull ISeedReader iSeedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos blockPos, @Nonnull NoFeatureConfig noFeatureConfig) {
        return NoisyOrePlacer.place(iSeedReader, chunkGenerator, random, blockPos, noFeatureConfig);
    }
}