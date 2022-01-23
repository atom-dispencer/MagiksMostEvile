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

import genelectrovise.magiksmostevile.core.support.EnumEvileArmorMaterial;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.item.equipment.armor.EvileArmorBases.AmethystArmorBase;
import genelectrovise.magiksmostevile.item.equipment.armor.EvileArmorBases.OverPoweredAmethystArmorBase;
import genelectrovise.magiksmostevile.item.equipment.armor.EvileArmorBases.PoweredAmethystArmorBase;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ArmorOrbitalRegistry implements IOrbitalRegistry {

    // =========ARMOR======================================================================================================================
    public static final RegistryObject<Item> AMETHYST_HELMET = ItemOrbitalRegistry.ITEMS
            .register("amethyst_helmet", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
                    EquipmentSlotType.HEAD, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ItemOrbitalRegistry.ITEMS
            .register("amethyst_chestplate", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
                    EquipmentSlotType.CHEST, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ItemOrbitalRegistry.ITEMS
            .register("amethyst_leggings", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
                    EquipmentSlotType.LEGS, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> AMETHYST_BOOTS = ItemOrbitalRegistry.ITEMS
            .register("amethyst_boots", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
                    EquipmentSlotType.FEET, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    //
    public static final RegistryObject<Item> POWERED_AMETHYST_HELMET =
            ItemOrbitalRegistry.ITEMS.register("powered_amethyst_helmet",
                    () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
                            EquipmentSlotType.HEAD, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> POWERED_AMETHYST_CHESTPLATE =
            ItemOrbitalRegistry.ITEMS.register("powered_amethyst_chestplate",
                    () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> POWERED_AMETHYST_LEGGINGS =
            ItemOrbitalRegistry.ITEMS.register("powered_amethyst_leggings",
                    () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
                            EquipmentSlotType.LEGS, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> POWERED_AMETHYST_BOOTS =
            ItemOrbitalRegistry.ITEMS.register("powered_amethyst_boots",
                    () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
                            EquipmentSlotType.FEET, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    //
    public static final RegistryObject<Item> OVER_POWERED_AMETHYST_HELMET =
            ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_helmet",
                    () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
                            EquipmentSlotType.HEAD, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> OVER_POWERED_AMETHYST_CHESTPLATE =
            ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_chestplate",
                    () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> OVER_POWERED_AMETHYST_LEGGINGS =
            ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_leggings",
                    () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
                            EquipmentSlotType.LEGS, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> OVER_POWERED_AMETHYST_BOOTS =
            ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_boots",
                    () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
                            EquipmentSlotType.FEET, new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    @Override
    public int priority() {
        return 7;
    }

    @Override
    public void initialise() {

    }

    @Override
    public String name() {
        return "armor";
    }

}
