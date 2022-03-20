package genelectrovise.magiksmostevile.world.gen.noisy_ore;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.stream.Collectors;

public class NoisyOreConfigurationFactory {

    public static final Logger LOGGER = LogManager.getLogger(NoisyOreConfigurationFactory.class);

    public static NoisyOreConfiguration fromResources(IResourceManager manager) {
        Collection<ResourceLocation> resources = manager.listResources("worldgen/noisy_ore", path -> true);

        LOGGER.info("RESOURCES!!! :: " + resources.stream().map(String::valueOf).collect(Collectors.joining("-", "{", "}")));

        return null;
    }
}
