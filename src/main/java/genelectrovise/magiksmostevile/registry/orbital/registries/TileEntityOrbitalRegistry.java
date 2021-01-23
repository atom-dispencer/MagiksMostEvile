package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.tileentity.altar.AltarTileEntity;
import genelectrovise.magiksmostevile.tileentity.amethyst_crystal.AmethystCrystalTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
      DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MagiksMostEvile.MODID);

  public static final RegistryObject<TileEntityType<AmethystCrystalTileEntity>> TILE_ENTITY_AMETHYST_CRYSTAL =
      TILE_ENTITIES.register("tile_entity_amethyst_crystal",
          () -> TileEntityType.Builder
              .create(AmethystCrystalTileEntity::new, BlockOrbitalRegistry.AMETHYST_CRYSTAL.get())
              .build(null));
  public static final RegistryObject<TileEntityType<AltarTileEntity>> TILE_ENTITY_ALTAR =
      TILE_ENTITIES.register("tile_entity_altar", () -> TileEntityType.Builder
          .create(AltarTileEntity::new, BlockOrbitalRegistry.ALTAR.get()).build(null));

  @Override
  public int priority() {
    return 9;
  }

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(TILE_ENTITIES);
  }

  @Override
  public String name() {
    return "tileentities";
  }

}
