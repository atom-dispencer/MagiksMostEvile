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
package genelectrovise.magiksmostevile.tileentity.altar;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.registry.orbital.RitualOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.Ritual;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Handles ritual selection in an {@link AltarContainer}
 *
 * @author GenElectrovise 10 Jun 2020
 */
public class AltarRitualSelector {

    private ResourceLocation location =
            new ResourceLocation(MagiksMostEvile.MODID, "convert_amethyst_ritual");

    /**
     * @return the location
     */
    public ResourceLocation getLocation() {
        return location != null ? location
                : new ResourceLocation(MagiksMostEvile.MODID, "convert_amethyst_ritual");
    }

    /**
     * @param location
     */
    public void setLocation(ResourceLocation location) {
        this.location = location;
    }

    public Supplier<Ritual> getRitualSupplier() {
        return getRitualSupplier(location);
    }

    public Supplier<Ritual> getRitualSupplier(ResourceLocation location) {

        ArrayList<Supplier<Ritual>> ritualSuppliers = new ArrayList<Supplier<Ritual>>();

        RitualOrbitalRegistry.RITUALS_DEFERRED.getEntries().forEach((ritualSupplier) -> {
            ritualSuppliers.add(ritualSupplier);
        });

        for (Supplier<Ritual> supplier : ritualSuppliers) {
            if (supplier.get().getRegistryName().equals(location)) {
                return supplier;
            }
        }

        MagiksMostEvile.LOGGER.error("No valid ritual of ID " + location + " in ritual registry!");
        MagiksMostEvile.LOGGER.debug("Registry: \n " + RitualOrbitalRegistry.RITUALS_DEFERRED);
        return null;
    }
}
