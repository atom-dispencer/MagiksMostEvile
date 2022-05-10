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
import genelectrovise.magiksmostevile.ritual.ConvertAmethystRitual;
import genelectrovise.magiksmostevile.ritual.Ritual;
import genelectrovise.magiksmostevile.ritual.SummonFlappyRitual;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@OrbitalRegistry(name = "rituals", priority = 12, registryField = "RITUALS_DEFERRED")
public class RitualOrbitalRegistry implements IOrbitalRegistry {

    public static final DeferredRegister<Ritual> RITUALS_DEFERRED = DeferredRegister.create(new ResourceLocation(MagiksMostEvile.MODID, "rituals"), MagiksMostEvile.MODID);
    public static final Supplier<IForgeRegistry<Ritual>> RITUALS_REGISTER = RITUALS_DEFERRED.makeRegistry(Ritual.class, RegistryBuilder::new);

    // =========RITUALS=====================================================================================================================
    public static final RegistryObject<ConvertAmethystRitual> CONVERT_AMETHYST_RITUAL = RITUALS_DEFERRED.register("convert_amethyst_ritual", ConvertAmethystRitual::new);
    public static final RegistryObject<SummonFlappyRitual> SUMMON_FLAPPY_RITUAL = RITUALS_DEFERRED.register("summon_flappy_ritual", SummonFlappyRitual::new);

    @Override
    public int priority() {
        return 12;
    }

    @Override
    public void initialise() {
        OrbitalRegistryGenerator.registerDeferredRegister(RITUALS_DEFERRED);
    }

    @Override
    public String name() {
        return "rituals";
    }

}
