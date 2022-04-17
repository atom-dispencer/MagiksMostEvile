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
package genelectrovise.magiksmostevile.registry.orbital;

import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class BlockItemOrbitalRegistry implements IOrbitalRegistry {

    public static final RegistryObject<Item> AMETHYST_BLOCK_ITEM =
            ItemOrbitalRegistry.ITEMS.register("amethyst_block", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_BLOCK.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> LEAD_BLOCK_ITEM =
            ItemOrbitalRegistry.ITEMS.register("lead_block", () -> new BlockItem(BlockOrbitalRegistry.LEAD_BLOCK.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    // Amethyst Ore
    public static final RegistryObject<Item> AMETHYST_ORE_STONE_ITEM = ItemOrbitalRegistry.ITEMS.register("amethyst_ore_stone",
            () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_ORE_STONE.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> AMETHYST_ORE_NETHERRACK_ITEM =
            ItemOrbitalRegistry.ITEMS.register("amethyst_ore_netherrack", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_ORE_NETHERRACK.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> AMETHYST_ORE_END_STONE_ITEM =
            ItemOrbitalRegistry.ITEMS.register("amethyst_ore_end_stone", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_ORE_END_STONE.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    // Lead Ore
    public static final RegistryObject<Item> LEAD_ORE_STONE_ITEM =
            ItemOrbitalRegistry.ITEMS.register("lead_ore_stone", () -> new BlockItem(BlockOrbitalRegistry.LEAD_ORE_STONE.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> LEAD_ORE_NETHERRACK_ITEM =
            ItemOrbitalRegistry.ITEMS.register("lead_ore_netherrack", () -> new BlockItem(BlockOrbitalRegistry.LEAD_ORE_NETHERRACK.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> LEAD_ORE_END_STONE_ITEM =
            ItemOrbitalRegistry.ITEMS.register("lead_ore_end_stone", () -> new BlockItem(BlockOrbitalRegistry.LEAD_ORE_END_STONE.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    // Tile entity
    public static final RegistryObject<Item> AMETHYST_CRYSTAL_ITEM =
            ItemOrbitalRegistry.ITEMS.register("amethyst_crystal", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_CRYSTAL.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> ALTAR_ITEM =
            ItemOrbitalRegistry.ITEMS.register("altar", () -> new BlockItem(BlockOrbitalRegistry.ALTAR.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> INSCRIPTION_TABLE_ITEM =
            ItemOrbitalRegistry.ITEMS.register("inscription_table", () -> new BlockItem(BlockOrbitalRegistry.INSCRIPTION_TABLE.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> CERAMIC_MORTAR_ITEM =
            ItemOrbitalRegistry.ITEMS.register("ceramic_mortar", () -> new BlockItem(BlockOrbitalRegistry.MORTAR.get(), new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public void initialise() {

    }

    @Override
    public String name() {
        return "blockitems";
    }

}
