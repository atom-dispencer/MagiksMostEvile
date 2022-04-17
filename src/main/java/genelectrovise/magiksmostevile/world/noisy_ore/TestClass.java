package genelectrovise.magiksmostevile.world.noisy_ore;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.StatUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TestClass {

    public static void main(String[] args) {
        testBinomial();
    }

    public static void testBinomial() {

        RandomGenerator randomGenerator = new JDKRandomGenerator();

        // Configuration
        final int desiredSpawningLevel = 50; // The desired height for an ore to spawn at.
        final float eccentricity = 1f; // Increases the irregularity of height by reducing accuracy.

        // World height params
        final int maxY = 256;
        final int minY = 0;
        final float worldHeight = maxY - minY; // Distance from min to max

        // Spawning level
        final int howFarAboveMinY = desiredSpawningLevel - minY; // Dist from minY to desiredLevel
        final double percentageOfMaxHeight = (float) howFarAboveMinY / worldHeight;

        // Distribution
        BinomialDistribution binomialDistribution = new BinomialDistribution(randomGenerator, (int) (worldHeight / eccentricity), percentageOfMaxHeight);

        for (int i = 0; i < 10; i++) {
            int[] sample = binomialDistribution.sample(100);
            IntStream stream = Arrays.stream(sample);
            double[] primArr = stream.asDoubleStream().toArray();
            Double[] sampleArr = ArrayUtils.toObject(primArr);
            List<Double> sampleList = Arrays.asList(sampleArr);
            double mean = StatUtils.mean(primArr);

            Double min = Collections.min(sampleList);
            Double max = Collections.max(sampleList);

            System.out.println(i + " :MEAN," + mean + " :LOW," + min + " :HIGH," + max + " :RANGE," + (max - min)+ " :RNGE%," + ((max - min) / mean) + ": " + Arrays.toString(binomialDistribution.sample(100)));
        }
    }
}
