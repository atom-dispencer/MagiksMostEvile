package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.common.ritual.ConvertAmethystRitual;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import genelectrovise.magiksmostevile.common.ritual.SummonFlappyRitual;
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
