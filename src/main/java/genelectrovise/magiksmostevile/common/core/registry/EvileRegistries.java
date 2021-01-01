package genelectrovise.magiksmostevile.common.core.registry;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class EvileRegistries {

  public static final IForgeRegistry<Ritual> RITUALS =
      RegistryManager.ACTIVE.getRegistry(Ritual.class);
  public static final RegistryKey<Registry<Ritual>> RITUALS_KEY =
      RegistryKey.getOrCreateRootKey(new ResourceLocation(MagiksMostEvile.MODID, "ritual"));

}
