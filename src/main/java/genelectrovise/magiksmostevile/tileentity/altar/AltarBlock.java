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
package genelectrovise.magiksmostevile.tileentity.altar;

import java.util.Random;
import genelectrovise.magiksmostevile.tileentity.ICustomContainer;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * @see AnvilBlock
 * @see FireBlock
 * @author GenElectrovise 19 Jun 2020
 */
public class AltarBlock extends Block {
  // double x1, double y1, double z1, double x2, double y2, double z2
  protected static final VoxelShape BODY_ADDON = Block.box(1.0D, 0D, 1.0D, 15.0D, 14.0D, 15.0D);
  protected static final VoxelShape BASE_ADDON = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
  protected static final VoxelShape COMBINED_SHAPE = VoxelShapes.or(BODY_ADDON, BASE_ADDON);

  public AltarBlock(Properties properties) {
    super(properties);
  }

  @Override
  public BlockRenderType getRenderShape(BlockState state) {
    return BlockRenderType.MODEL;
  }
  
  @Override
  public boolean useShapeForLightOcclusion(BlockState state) {
    return true;
  }

  @Override
  public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
    return true;
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return COMBINED_SHAPE;
  }

  // ======================================================================================================================
  // TILE ENTITY

  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new AltarTileEntity();
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
  
  @Override
  public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
    if (entityIn instanceof LivingEntity) {
      EffectInstance levitation = new EffectInstance(Effects.LEVITATION, 30);
      LivingEntity entity = (LivingEntity) entityIn;
      entity.addEffect(levitation);
    }
  }

  public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune,
      int silktouch) {
    return silktouch == 0 ? 5 * fortune : 0;
  }
  
  @Override
  public void destroy(IWorld worldIn, BlockPos pos, BlockState state) {
    // On the server
    if (!worldIn.isClientSide()) {

      // 15 times
      for (int i = 0; i < 15; i++) {

        // Definitely on the server
        if (!(worldIn instanceof ServerWorld)) {
          return;
        }
        ServerWorld serverWorld = (ServerWorld) worldIn;

        // New lightning bolt
        LightningBoltEntity lightning =
            new LightningBoltEntity(EntityType.LIGHTNING_BOLT, serverWorld);
        lightning.setPos(pos.getX() + RANDOM.nextInt(21) - 5,
            pos.getY() + RANDOM.nextInt(3) - 1, pos.getZ() + RANDOM.nextInt(21) - 5);

        // Summon
        serverWorld.addFreshEntity(lightning);

        // Get the closest player
        PlayerEntity player =
            serverWorld.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
        // If not null, make an explosion - the player is the cause
        if (player != null) {
          serverWorld.explode(player, pos.getX(), pos.getY(), pos.getZ(), 2, true,
              Explosion.Mode.BREAK);

        }
      }
    }
  }

  // Animate
  @Override
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    try {
      if (worldIn.getBlockEntity(pos) instanceof AltarTileEntity) {
        AltarTileEntity altar = (AltarTileEntity) worldIn.getBlockEntity(pos);

        // Guarentee casting
        if (!altar.isCasting()) {
          return;
        }

        // Make particles
        int mod = 3;
        for (int i = 0; i < 2; i++) {
          worldIn.addParticle(ParticleTypes.ANGRY_VILLAGER, true,
              pos.getX() + (rand.nextInt(mod)) - (mod / 2),
              pos.getY() + (rand.nextInt(mod)) - (mod / 2) + 1,
              pos.getZ() + (rand.nextInt(mod)) - (mod / 2), rand.nextDouble() - 0.5,
              rand.nextDouble(), rand.nextDouble() - 0.5);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Gui

  public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceResult) {
    if (!worldIn.isClientSide()) {
      final ICustomContainer tileEntity = (ICustomContainer) worldIn.getBlockEntity(pos);
      tileEntity.openGUI((ServerPlayerEntity) player);
    }

    return ActionResultType.SUCCESS;
  }

}
