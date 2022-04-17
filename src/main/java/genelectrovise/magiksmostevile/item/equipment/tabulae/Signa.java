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
package genelectrovise.magiksmostevile.item.equipment.tabulae;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.item.equipment.tabulae.Signum.EnumSignumMaterialType;
import genelectrovise.magiksmostevile.registry.orbital.ItemOrbitalRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

public class Signa {
    public static final BiMap<ResourceLocation, Signum> SIGNA = HashBiMap.create();
    public static final Signum BLANK = register(new ResourceLocation(MagiksMostEvile.MODID, "blank_signum"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.BLANK_SIGNUM);
    public static final Signum NATURES_KIN = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_natures_kin"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_NATURES_KIN);
    public static final Signum SOUL = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_soul"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_SOUL);
    public static final Signum MUDDIED_DUST = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_muddied_dust"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_MUDDIED_DUST);
    public static final Signum SHAKEN_STONE =
            register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_shaken_stone"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_SHAKEN_STONE);
    public static final Signum REACHING_GAZE =
            register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_reaching_gaze"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_REACHING_GAZE);
    public static final Signum SPRING_BREEZE =
            register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_spring_breeze"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_SPRING_BREEZE);

    public static Signum register(ResourceLocation name, EnumSignumMaterialType type, Supplier<Item> item) {
        return SIGNA.putIfAbsent(name, new Signum(name, type, item));
    }
}
