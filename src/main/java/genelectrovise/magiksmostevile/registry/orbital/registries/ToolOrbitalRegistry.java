/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.support.EnumEvileItemTier;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.item.glowing.GlowingAxe;
import genelectrovise.magiksmostevile.item.glowing.GlowingHoe;
import genelectrovise.magiksmostevile.item.glowing.GlowingPickaxe;
import genelectrovise.magiksmostevile.item.glowing.GlowingShovel;
import genelectrovise.magiksmostevile.item.glowing.GlowingSword;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;

public class ToolOrbitalRegistry implements IOrbitalRegistry {

  // =========TOOLS=======================================================================================================================
  public static final RegistryObject<Item> AMETHYST_SWORD = ItemOrbitalRegistry.ITEMS
      .register("amethyst_sword", () -> new SwordItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> AMETHYST_AXE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_axe", () -> new AxeItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> AMETHYST_PICKAXE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_pickaxe", () -> new PickaxeItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> AMETHYST_SHOVEL = ItemOrbitalRegistry.ITEMS
      .register("amethyst_shovel", () -> new ShovelItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> AMETHYST_HOE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_hoe", () -> new HoeItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_SWORD = ItemOrbitalRegistry.ITEMS
      .register("powered_amethyst_sword", () -> new GlowingSword(EnumEvileItemTier.POWERED_AMETHYST,
          2, -0.25F, new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_AXE = ItemOrbitalRegistry.ITEMS
      .register("powered_amethyst_axe", () -> new GlowingAxe(EnumEvileItemTier.POWERED_AMETHYST, 2,
          -0.25F, new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_PICKAXE =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_pickaxe",
          () -> new GlowingPickaxe(EnumEvileItemTier.POWERED_AMETHYST, 2, -0.25F,
              new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_SHOVEL =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_shovel",
          () -> new GlowingShovel(EnumEvileItemTier.POWERED_AMETHYST, 2, -0.25F,
              new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_HOE = ItemOrbitalRegistry.ITEMS
      .register("powered_amethyst_hoe", () -> new GlowingHoe(EnumEvileItemTier.POWERED_AMETHYST, 2,
          -0.25F, new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE)));

  @Override
  public int priority() {
    return 4;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "tools";
  }

}
