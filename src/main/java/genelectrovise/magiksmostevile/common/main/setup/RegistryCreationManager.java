package genelectrovise.magiksmostevile.common.main.setup;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.RegistryBuilder;

public class RegistryCreationManager {

  public void create() {
    new RegistryBuilder<Ritual>().setType(Ritual.class)
        .setName(new ResourceLocation(MagiksMostEvile.MODID, "ritual")).create();
  }

}
