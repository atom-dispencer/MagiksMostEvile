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
package genelectrovise.magiksmostevile.core.support;

import genelectrovise.magiksmostevile.registry.orbital.ItemOrbitalRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EvileItemGroup {
    public static final ItemGroup MAGIKS_MOST_EVILE = new ItemGroup("itemgroup_evile") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemOrbitalRegistry.AMETHYST.get());
        }
    };
}
