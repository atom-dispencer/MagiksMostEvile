package genelectrovise.magiksmostevile.world.noisyore

import net.minecraft.resources.IResourceManager
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class NoisyOreConfigurationFactory {

    companion object {

        @JvmStatic
        private val logger: Logger = LogManager.getLogger(NoisyOreConfigurationFactory::class)

        @JvmStatic
        fun fromResources(manager: IResourceManager): NoisyOreConfiguration? {

            logger.info("Kotlin test logger")
            manager.listResources("world/gen/noisy_ore") { true }

            return null
        }
    }


}
