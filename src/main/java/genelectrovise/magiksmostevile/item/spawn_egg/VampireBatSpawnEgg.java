/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.item.spawn_egg;

import genelectrovise.magiksmostevile.registry.orbital.EntityOrbitalRegistry;
import net.minecraft.entity.EntityType;

/**
 * @author GenElectrovise 5 Jun 2020
 */
public class VampireBatSpawnEgg extends EvileSpawnEgg {

    /**
     * @param properties
     */
    public VampireBatSpawnEgg(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<?> getEntityType() {
        return EntityOrbitalRegistry.VAMPIRE_BAT.get();
    }

}
