/**
 * 
 */
package genelectrovise.magiksmostevile.common.main.registry;

import java.util.Iterator;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author GenElectrovise 12 Jun 2020
 */
public class EntityRegistry {

  /**
   * For adding vampire bats to the world.
   * 
   * @author GenElectrovise 12 Jun 2020
   */
  @Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class VampireBat {

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void addVampireBatSpawns(FMLCommonSetupEvent event) {

      // Run later
      DeferredWorkQueue.runLater(() -> {

        Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
        biomes.forEachRemaining((biome) -> {

          // If biome is not the ocean, nether, or end, add a spawn
          if (biome.getCategory() != Biome.Category.OCEAN
              || biome.getCategory() != Biome.Category.NETHER
              || biome.getCategory() != Biome.Category.THEEND) {

            biome.getMobSpawnInfo().getEntityTypes().add(EvileDeferredRegistry.VAMPIRE_BAT.get());
          }
        });
      });

    }

  }
}
