/**
 * 
 */
package genelectrovise.magiksmostevile.common.main.registry;

import java.util.Iterator;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
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
      DeferredWorkQueue.runLater(() -> {
        MagiksMostEvile.LOGGER.debug(
            "Adding vampire bats to Biomes! (will skip Biome.Category == OCEAN, NETHER, THEEND)");
        Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
        biomes.forEachRemaining((biome) -> {
          if (biome.getCategory() != Biome.Category.OCEAN
              || biome.getCategory() != Biome.Category.NETHER
              || biome.getCategory() != Biome.Category.THEEND) {
            MagiksMostEvile.LOGGER
                .debug(" > Adding vampire bat spawn list entry to Biome : " + biome);
            biome.getSpawns(EntityClassification.MONSTER)
                .add(new SpawnListEntry(EvileDeferredRegistry.VAMPIRE_BAT.get(), 5, 2, 10));
          }
        });
      });
    }

  }
}
