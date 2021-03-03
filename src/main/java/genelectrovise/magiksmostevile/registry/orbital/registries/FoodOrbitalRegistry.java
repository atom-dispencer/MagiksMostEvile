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
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

public class FoodOrbitalRegistry implements IOrbitalRegistry {

  // =========FOOD========================================================================================================================
  public static final RegistryObject<Item> AMETHYST_POTATO =
      ItemOrbitalRegistry.ITEMS.register("amethyst_potato",
          () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(3).saturation(0.8F)
              .effect(() -> new EffectInstance(Effects.SPEED, 400, 1), 1.0F).fastToEat().build())
              .group(EvileItemGroup.MAGIKS_MOST_EVILE)));

  @Override
  public int priority() {
    return 8;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "foods";
  }

}
