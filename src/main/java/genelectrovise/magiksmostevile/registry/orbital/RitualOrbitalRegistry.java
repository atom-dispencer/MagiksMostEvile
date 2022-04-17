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
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.ritual.ConvertAmethystRitual;
import genelectrovise.magiksmostevile.ritual.Ritual;
import genelectrovise.magiksmostevile.ritual.SummonFlappyRitual;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class RitualOrbitalRegistry implements IOrbitalRegistry {

    public static final DeferredRegister<Ritual> RITUALS =
            DeferredRegister.create(Ritual.class, MagiksMostEvile.MODID);

    // =========RITUALS=====================================================================================================================
    public static final RegistryObject<ConvertAmethystRitual> CONVERT_AMETHYST_RITUAL =
            RITUALS.register("convert_amethyst_ritual", () -> new ConvertAmethystRitual());
    public static final RegistryObject<SummonFlappyRitual> SUMMON_FLAPPY_RITUAL =
            RITUALS.register("summon_flappy_ritual", () -> new SummonFlappyRitual());

    @Override
    public int priority() {
        return 12;
    }

    @Override
    public void initialise() {
        OrbitalRegistryGenerator.registerDeferredRegister(RITUALS);
    }

    @Override
    public String name() {
        return "rituals";
    }

}
