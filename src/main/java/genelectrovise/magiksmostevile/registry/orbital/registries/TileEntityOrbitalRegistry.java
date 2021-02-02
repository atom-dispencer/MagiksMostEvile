package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.tileentity.altar.AltarTileEntity;
import genelectrovise.magiksmostevile.tileentity.amethyst_crystal.AmethystCrystalTileEntity;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableTileEntity;
import genelectrovise.magiksmostevile.tileentity.mortar.MortarTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MagiksMostEvile.MODID);

  public static final RegistryObject<TileEntityType<AmethystCrystalTileEntity>> TILE_ENTITY_AMETHYST_CRYSTAL =
      TILE_ENTITIES.register("tile_entity_amethyst_crystal", () -> TileEntityType.Builder.create(AmethystCrystalTileEntity::new, BlockOrbitalRegistry.AMETHYST_CRYSTAL.get()).build(null));
  public static final RegistryObject<TileEntityType<AltarTileEntity>> TILE_ENTITY_ALTAR =
      TILE_ENTITIES.register("tile_entity_altar", () -> TileEntityType.Builder.create(AltarTileEntity::new, BlockOrbitalRegistry.ALTAR.get()).build(null));
  public static final RegistryObject<TileEntityType<InscriptionTableTileEntity>> TILE_ENTITY_INSCRIPTION_TABLE =
      TILE_ENTITIES.register("tile_entity_inscription_table", () -> TileEntityType.Builder.create(InscriptionTableTileEntity::new, BlockOrbitalRegistry.INSCRIPTION_TABLE.get()).build(null));
  public static final RegistryObject<TileEntityType<MortarTileEntity>> TILE_ENTITY_MORTAR =
      TILE_ENTITIES.register("tile_entity_mortar", () -> TileEntityType.Builder.create(MortarTileEntity::new, BlockOrbitalRegistry.MORTAR.get()).build(null));

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
