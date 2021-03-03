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
package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class TomeOrbitalRegistry implements IOrbitalRegistry {

  // =========TOMES======================================================================================================================
  public static final RegistryObject<Item> TOME_CONVERT_AMETHYST =
      ItemOrbitalRegistry.ITEMS.register("tome_convert_amethyst",
          () -> new Item(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> TOME_SUMMON_FLAPPY =
      ItemOrbitalRegistry.ITEMS.register("tome_summon_flappy",
          () -> new Item(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));

  @Override
  public int priority() {
    return 6;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "tomes";
  }

}
