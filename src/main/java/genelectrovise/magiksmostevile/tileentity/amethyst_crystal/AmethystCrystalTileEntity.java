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
package genelectrovise.magiksmostevile.tileentity.amethyst_crystal;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.particle.ParticleNetworkingManager;
import genelectrovise.magiksmostevile.network.particle.ender.EnderParticleMessageToClient;
import genelectrovise.magiksmostevile.registry.orbital.registries.FoodOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.registries.TileEntityOrbitalRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PotatoBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class AmethystCrystalTileEntity extends TileEntity implements ITickableTileEntity {

  private World world;
  private int iteration = 1;

  public AmethystCrystalTileEntity() {
    super(TileEntityOrbitalRegistry.TILE_ENTITY_AMETHYST_CRYSTAL.get());
    MagiksMostEvile.LOGGER.debug("Constructing class : TileEntityAmethystCrystal");
  }

  public void read(BlockState state, CompoundNBT compound) {
    super.read(state, compound);
  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
    return super.write(compound);
  }

  @Override
  public void onLoad() {
    this.world = this.getWorld();
  }

  @Override
  public void tick() {
    int posX = this.getPos().getX();
    int posY = this.getPos().getY();
    int posZ = this.getPos().getZ();
    int i = posX;
    int j = posY;
    int k = posZ;

    if (iteration % 10 == 0) {
      try {
        for (i = posX - 2; i < posX + 3; i++) {
          for (j = posY - 1; j < posY + 1; j++) {
            for (k = posZ - 2; k < posZ + 3; k++) {
              BlockPos blockPos = new BlockPos(i, j, k);
              Block block = world.getBlockState(blockPos).getBlock();
              checkAndStartBlockReplacement(block, blockPos);
            }

          }
        }
        iteration = 1;
      } catch (NullPointerException e) {
        MagiksMostEvile.LOGGER.error("NullPointerException! world.getBlockState(blockPos).getBlock() returned null? ");
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }

      ParticleNetworkingManager.CEnderParticle.send(PacketDistributor.ALL.noArg(), new EnderParticleMessageToClient(this.getPos(), 1));
    }

    iteration++;
  }

  private void checkAndStartBlockReplacement(Block block, BlockPos blockPos) {
    if (block instanceof PotatoBlock) {

      if ((int) world.getBlockState(blockPos).get(PotatoBlock.AGE) == 7) {
        replacePotatoAtPosition(blockPos);
      }

    }
  }

  private void replacePotatoAtPosition(BlockPos blockPos) {
    BlockState air = Blocks.AIR.getDefaultState();
    world.setBlockState(blockPos, air);
    spawnEntityItemAmethystPotato(blockPos);
  }

  private void spawnEntityItemAmethystPotato(BlockPos blockPos) {
    if (!world.isRemote) {
      Entity entity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(FoodOrbitalRegistry.AMETHYST_POTATO.get(), 1));
      world.addEntity(entity);
    }
  }
}
