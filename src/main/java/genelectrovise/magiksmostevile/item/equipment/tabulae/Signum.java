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

import net.minecraft.item.Item;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class Signum {

    private final ResourceLocation name;
    private final EnumSignumMaterialType type;
    private final Supplier<Item> item;

    protected Signum(ResourceLocation name, EnumSignumMaterialType type, Supplier<Item> item) {
        this.name = name;
        this.type = type;
        this.item = item;
    }

    public ResourceLocation getName() {
        return name;
    }

    public EnumSignumMaterialType getType() {
        return type;
    }

    public Supplier<Item> getItem() {
        return item;
    }

    public static enum EnumSignumMaterialType {
        WOOD, GOLD;
    }
}
