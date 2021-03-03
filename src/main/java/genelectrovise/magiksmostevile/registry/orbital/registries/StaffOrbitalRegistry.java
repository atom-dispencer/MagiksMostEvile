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
import genelectrovise.magiksmostevile.item.equipment.staff.AmethystStaff;
import genelectrovise.magiksmostevile.item.equipment.staff.DebuggingStaff;
import genelectrovise.magiksmostevile.item.equipment.staff.DimensionWarpingStaff;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class StaffOrbitalRegistry implements IOrbitalRegistry {

  // =========STAFFS====================================================================================================================
  public static final RegistryObject<Item> AMETHYST_STAFF =
      ItemOrbitalRegistry.ITEMS.register("amethyst_staff", () -> new AmethystStaff(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(1).rarity(Rarity.UNCOMMON)));
  public static final RegistryObject<Item> DIMENSION_WARPING_STAFF =
      ItemOrbitalRegistry.ITEMS.register("dimension_warping_staff", () -> new DimensionWarpingStaff(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(1).rarity(Rarity.EPIC)));
  public static final RegistryObject<Item> STAFF_OF_DEGUGGING =
      ItemOrbitalRegistry.ITEMS.register("debugging_staff", () -> new DebuggingStaff(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(1).rarity(Rarity.EPIC)));

  @Override
  public int priority() {
    return 5;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "staffs";
  }

}
