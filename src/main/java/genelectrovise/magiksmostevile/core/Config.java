package genelectrovise.magiksmostevile.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    // Common config
    public static class Common {

        public final ForgeConfigSpec.ConfigValue<Boolean> runIntegrationTests;

        protected Common(ForgeConfigSpec.Builder builder) {
            builder.push("Developer Options");
            this.runIntegrationTests = builder
                    .comment("Runs a suite of integration tests during mod loading, allowing testing of mod systems in a runtime environment. Impacts startup time.")
                    .worldRestart()
                    .define("runIntegrationTests", false);
            builder.pop();
        }

    }

    /**
     * Server-only config
     */
    public static class Server {

        public final ForgeConfigSpec.ConfigValue<Boolean> useCourierHandshake;

        protected Server(ForgeConfigSpec.Builder builder) {
            builder.push("Courier");
            this.useCourierHandshake = builder
                    .comment("Require joining clients to complete a handshake to check the compatibility of items in their registry.")
                    .worldRestart()
                    .define("useCourierHandshake", true);
            builder.pop();

        }

    }

    // ========================================== STATIC ==========================================

    // Common
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    // Server
    public static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {

        // Common
        Pair<Common, ForgeConfigSpec> commonForgeSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonForgeSpecPair.getLeft();
        COMMON_SPEC = commonForgeSpecPair.getRight();

        // Server
        Pair<Server, ForgeConfigSpec> serverForgeSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER = serverForgeSpecPair.getLeft();
        SERVER_SPEC = serverForgeSpecPair.getRight();
    }
}
