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
