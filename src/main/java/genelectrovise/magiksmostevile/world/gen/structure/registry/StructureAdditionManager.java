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
package genelectrovise.magiksmostevile.world.gen.structure.registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * {@link Biome}
 * 
 * @author GenElectrovise
 *
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.FORGE)
public class StructureAdditionManager {

  public static final Logger LOGGER = LogManager.getLogger(StructureAdditionManager.class);

  @SubscribeEvent
  public static void addStructures(BiomeLoadingEvent event) {

    LOGGER.info("Adding MME structures to biome: " + event.getName());

    for (StructureFeature<?, ?> structure : ModConfiguredStructures.map.values()) {
      event.getGeneration().addStructureStart(structure);
    }


    LOGGER.info("Done adding MME structures to biomes");
  }
}
