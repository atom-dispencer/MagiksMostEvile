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

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.registry.orbital.registries.ContainerOrbitalRegistry;
import genelectrovise.magiksmostevile.tileentity.altar.AltarScreenManager;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableScreenManager;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

/**
 * @author GenElectrovise 12 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegistry {

  @SubscribeEvent
  public static void registerFactories(FMLLoadCompleteEvent event) {
    ScreenManager.register(ContainerOrbitalRegistry.ALTAR_CONTAINER.get(), new AltarScreenManager());
    ScreenManager.register(ContainerOrbitalRegistry.INSCRIPTION_TABLE_CONTAINER.get(), new InscriptionTableScreenManager());
  }
}
