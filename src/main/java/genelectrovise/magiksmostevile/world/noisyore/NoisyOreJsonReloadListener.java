package genelectrovise.magiksmostevile.world.noisyore;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@ParametersAreNonnullByDefault
public class NoisyOreJsonReloadListener extends JsonReloadListener {

    public static final Logger LOGGER = LogManager.getLogger(NoisyOreJsonReloadListener.class);
    public static final String ROOT_DIRECTORY = MagiksMostEvile.MODID + "_noisyore";

    public NoisyOreJsonReloadListener(Gson gson, String directory) {
        super(gson, directory);
    }

    /**
     *
     * @param mapIn A pre-filtered map containing {@link JsonElement}s from the configured ROOT_DIRECTORY
     * @param resourceManager The {@link IResourceManager} which is currently being reloaded
     * @param profiler Tracks the progress of reloading.
     */
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> mapIn, IResourceManager resourceManager, IProfiler profiler) {
        Map<String, NoisyOreConfiguration.Generation> map = NoisyOreConfigurationFactory.createGenerationsFromResources(mapIn);
        NoisyOreConfiguration.INSTANCE.setGenerations(map);
    }
}