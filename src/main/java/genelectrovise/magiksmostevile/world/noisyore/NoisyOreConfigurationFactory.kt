package genelectrovise.magiksmostevile.world.noisyore

import com.google.common.collect.Maps
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import genelectrovise.magiksmostevile.gson.GsonConfigurator
import net.minecraft.util.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class NoisyOreConfigurationFactory {

    companion object {

        @JvmStatic
        private val logger: Logger = LogManager.getLogger(NoisyOreConfigurationFactory::class)

        /**
         * @param resources Resources under the NoisyOreJsonReloadListener#ROOT_DIRECTORY
         */
        @JvmStatic
        fun createGenerationsFromResources(resources: Map<ResourceLocation, JsonElement>): Map<String, NoisyOreConfiguration.Generation> {

            logger.info("Reloading Noisy Ore configuration from datapacks...")

            val generations = Maps.newHashMap<String, NoisyOreConfiguration.Generation>()
            val gson = GsonConfigurator.newConfiguredInstance()

            for ((rl, elem) in resources) {
                try {
                    val gen = gson.fromJson(elem, NoisyOreConfiguration.Generation::class.java)
                    generations[rl.toString()] = gen
                } catch (jse: JsonSyntaxException) {
                    logger.error(jse)
                }
            }

            return generations
        }
    }


}
