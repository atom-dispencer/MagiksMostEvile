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

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.item.equipment.tabulae.*;
import genelectrovise.magiksmostevile.item.glowing.GlowingItem;
import genelectrovise.magiksmostevile.tileentity.mortar.EnumPestleLevel;
import genelectrovise.magiksmostevile.tileentity.mortar.Pestle;
import net.minecraft.item.Item;
import net.minecraft.item.WrittenBookItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemOrbitalRegistry implements IOrbitalRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagiksMostEvile.MODID);

    // =========ITEMS=======================================================================================================================

    // Material types
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> POWERED_AMETHYST = ITEMS.register("powered_amethyst", () -> new GlowingItem(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> OVER_POWERED_AMETHYST = ITEMS.register("over_powered_amethyst", () -> new GlowingItem(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    // Mobs
    public static final RegistryObject<Item> VAMPIRE_BAT_TOOTH = ITEMS.register("vampire_bat_tooth", () -> new WrittenBookItem(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    // General
    public static final RegistryObject<Item> GOING_TO_THE_BALL = ITEMS.register("going_to_the_ball", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INTRO_BOOK = ITEMS.register("intro_book", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> LESSER_POWER_STONE = ITEMS.register("lesser_power_stone", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> GREATER_POWER_STONE = ITEMS.register("greater_power_stone", () -> new GlowingItem(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> CERAMIC_PESTLE = ITEMS.register("ceramic_pestle", () -> new Pestle(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE), EnumPestleLevel.CERAMIC));

    // Tabulae
    public static final RegistryObject<Item> BEESWAX = ITEMS.register("beeswax", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SANDPAPER = ITEMS.register("sandpaper", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    public static final RegistryObject<Item> BLANK_SIGNUM = ITEMS.register("blank_signum", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SIGNUM_OF_MUDDIED_DUST = ITEMS.register("signum_of_muddied_dust", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SIGNUM_OF_THE_SOUL = ITEMS.register("signum_of_the_soul", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SIGNUM_OF_THE_REACHING_GAZE = ITEMS.register("signum_of_the_reaching_gaze", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SIGNUM_OF_THE_SHAKEN_STONE = ITEMS.register("signum_of_the_shaken_stone", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SIGNUM_OF_THE_SPRING_BREEZE = ITEMS.register("signum_of_the_spring_breeze", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));
    public static final RegistryObject<Item> SIGNUM_OF_NATURES_KIN = ITEMS.register("signum_of_natures_kin", () -> new Item(new Item.Properties().tab(EvileItemGroup.MAGIKS_MOST_EVILE)));

    public static final RegistryObject<Item> BLANK_TABLULA = ITEMS.register("blank_tabula", () -> new BlankTabula());
    public static final RegistryObject<Item> TABULA_OF_THE_SIGNUM_OF_MUDDIED_DUST = ITEMS.register("tabula_of_the_signum_of_muddied_dust", () -> new TabulaMuddiedDust());
    public static final RegistryObject<Item> TABULA_OF_THE_SIGNUM_OF_THE_SOUL = ITEMS.register("tabula_of_the_signum_of_the_soul", () -> new TabulaSoul());
    public static final RegistryObject<Item> TABULA_OF_THE_SIGNUM_OF_THE_REACHING_GAZE = ITEMS.register("tabula_of_the_signum_of_the_reaching_gaze", () -> new TabulaReachingGaze());
    public static final RegistryObject<Item> TABULA_OF_THE_SIGNUM_OF_THE_SHAKEN_STONE = ITEMS.register("tabula_of_the_signum_of_the_shaken_stone", () -> new TabulaShakenStone());
    public static final RegistryObject<Item> TABULA_OF_THE_SIGNUM_OF_THE_SPRING_BREEZE = ITEMS.register("tabula_of_the_signum_of_the_spring_breeze", () -> new TabulaSpringBreeze());
    public static final RegistryObject<Item> TABULA_OF_THE_SIGNUM_OF_NATURES_KIN = ITEMS.register("tabula_of_the_signum_of_natures_kin", () -> new TabulaNaturesKin());

    @Override
    public void initialise() {
        OrbitalRegistryHandler.registerDeferredRegister(ITEMS);
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public String name() {
        return "items";
    }

}
