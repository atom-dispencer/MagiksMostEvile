
package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticleData;
import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticleType;
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
