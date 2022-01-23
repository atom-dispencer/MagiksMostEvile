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
package genelectrovise.magiksmostevile.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.structure.RuinedPortalPiece;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link RuinedPortalPiece.Location}
 *
 * @author GenElectrovise
 */
public enum EnumFeatureLocation implements IStringSerializable {
    DEFAULT("default", Category.NONE), //
    DESERT("desert", Category.DESERT), //
    JUNGLE("jungle", Category.JUNGLE), //
    SWAMP("swamp", Category.SWAMP), //
    ICY("mountain", Category.ICY), //
    OCEAN("ocean", Category.OCEAN), //
    NETHER("nether", Category.NETHER), //
    END("end", Category.THEEND), //
    ;

    // Map
    private static final Map<String, EnumFeatureLocation> MAP =
            Arrays.stream(values()).collect(Collectors.toMap(EnumFeatureLocation::getName, (location) -> {
                return location;
            }));
    // Codec
    public static final Codec<EnumFeatureLocation> CODEC =
            IStringSerializable.fromEnum(EnumFeatureLocation::values, EnumFeatureLocation::get);
    // Enum
    private final String name;
    private final Biome.Category biomeCategory;

    private EnumFeatureLocation(String name, Biome.Category biomeCategory) {
        this.name = name;
        this.biomeCategory = biomeCategory;
    }

    public static EnumFeatureLocation get(String name) {
        return MAP.get(name);
    }

    public String getName() {
        return this.name;
    }

    // IStringSerializable

    public Biome.Category getBiomeCategory() {
        return biomeCategory;
    }

    @Override
    public String getSerializedName() {
        return this.getName();
    }
}
