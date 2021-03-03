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
package genelectrovise.magiksmostevile.core.setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class RecipeSetup {

  private static final Logger LOGGER = LogManager.getLogger();

  public static void editPreExistingItems(FMLCommonSetupEvent event) {
    LOGGER.info("Editing pre-existing items... Going incognito...");

    // Make an iron sword return an iron sword when placed into a crafting grid.
    ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.IRON_SWORD, Items.IRON_SWORD, "containerItem");
    LOGGER.debug("New containerItem injected for Items.IRON_SWORD");
  }
}
