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

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.particle.glyph.GlyphParticleData;
import genelectrovise.magiksmostevile.particle.glyph.GlyphParticleType;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<ParticleType<?>> PARTICLES =
      DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MagiksMostEvile.MODID);

  // =========PARTICLES===================================================================================================================
  public static final RegistryObject<ParticleType<GlyphParticleData>> GLYPH_PARTICLE =
      PARTICLES.register("glyph_particle", () -> new GlyphParticleType());

  @Override
  public int priority() {
    return 13;
  }

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(PARTICLES);
  }

  @Override
  public String name() {
    return "particles";
  }

}
