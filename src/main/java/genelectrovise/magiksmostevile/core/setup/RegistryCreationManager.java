package genelectrovise.magiksmostevile.core.setup;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.ritual.Ritual;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.RegistryBuilder;

public class RegistryCreationManager {

  public void create() {
    new RegistryBuilder<Ritual>().setType(Ritual.class)
        .setName(new ResourceLocation(MagiksMostEvile.MODID, "ritual")).create();
  }

}
