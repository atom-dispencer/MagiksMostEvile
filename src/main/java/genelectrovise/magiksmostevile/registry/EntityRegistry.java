/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.registry;

import java.util.Iterator;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.registry.orbital.registries.EntityOrbitalRegistry;
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
          if (biome.getBiomeCategory() != Biome.Category.OCEAN || biome.getBiomeCategory() != Biome.Category.NETHER || biome.getBiomeCategory() != Biome.Category.THEEND) {

            biome.getMobSettings().getEntityTypes().add(EntityOrbitalRegistry.VAMPIRE_BAT.get());
          }
        });
      });

    }

  }
}
