package genelectrovise.magiksmostevile.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    /**
     * Doesn't need to be protected, just encapsulating :)
     */
    protected static class Common {

        public final ForgeConfigSpec.ConfigValue<Boolean> runIntegrationTests;

        protected Common(ForgeConfigSpec.Builder builder) {
            builder.push("Developer Options");
            this.runIntegrationTests = builder
                    .comment("Runs a suite of integration tests during mod loading, allowing testing of mod systems in a runtime environment. Impacts startup time.")
                    .worldRestart()
                    .define("Run Integration Tests During Loading", false);
            builder.pop();
        }

    }

    // Common
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {

        // Common
        Pair<Common, ForgeConfigSpec> commonForgeSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonForgeSpecPair.getLeft();
        COMMON_SPEC = commonForgeSpecPair.getRight();
    }
}
