package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<ContainerType<?>> CONTAINERS =
      DeferredRegister.create(ForgeRegistries.CONTAINERS, MagiksMostEvile.MODID);

  public static final RegistryObject<ContainerType<AltarContainer>> ALTAR_CONTAINER =
      CONTAINERS.register("altar", () -> IForgeContainerType.create(AltarContainer::new));

  @Override
  public int priority() {
    return 10;
  }

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(CONTAINERS);
  }

  @Override
  public String name() {
    return "containers";
  }

}
