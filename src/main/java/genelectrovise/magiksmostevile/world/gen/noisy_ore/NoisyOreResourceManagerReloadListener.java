package genelectrovise.magiksmostevile.world.gen.noisy_ore;

import com.google.gson.Gson;
import genelectrovise.magiksmostevile.gson.GsonConfigurator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class NoisyOreResourceManagerReloadListener implements ISelectiveResourceReloadListener {

    public static final Logger LOGGER = LogManager.getLogger(NoisyOreResourceManagerReloadListener.class);

    public static final String ROOT_PATH = "path";
    public static final String MIDDLE_PATH = "worldgen/noisy_ore/generations";

    protected NoisyOreConfiguration config;

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        Collection<ResourceLocation> resources = resourceManager.listResources(ROOT_PATH, path -> (path.contains(MIDDLE_PATH) && path.endsWith(".json")));
        Gson gson = GsonConfigurator.newConfiguredInstance();
        Map<String, NoisyOreConfiguration.Generation> map = resources.stream().collect(Collectors.toMap(
                NoisyOreResourceManagerReloadListener::mapKey,
                rl -> mapValue(rl, gson, resourceManager)
        ));
        NoisyOreConfiguration.INSTANCE = new NoisyOreConfiguration(map);
    }

    protected static String mapKey(ResourceLocation rl) {
        return rl.toString();
    }

    protected static NoisyOreConfiguration.Generation mapValue(ResourceLocation resourceLocation, Gson gson, IResourceManager resourceManager) {
        try {
            InputStream is = resourceManager.getResource(resourceLocation).getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            return gson.fromJson(reader, NoisyOreConfiguration.Generation.class);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return NoisyOreConfiguration.Generation.DUMMY;
    }
}