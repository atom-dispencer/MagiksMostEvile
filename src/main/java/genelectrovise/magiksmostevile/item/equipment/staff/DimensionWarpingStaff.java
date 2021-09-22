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
/**
 * 
 */
package genelectrovise.magiksmostevile.item.equipment.staff;

import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * @author GenElectrovise 18 Jun 2020
 * 
 * @see EnderPearlEntity
 */
public class DimensionWarpingStaff extends Item {

  /**
   * @param properties
   */
  public DimensionWarpingStaff(Properties properties) {
    super(properties);
  }

  @Override
  public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn,      Hand handIn) {

    // If the player is in creative
    if (!playerIn.isCreative()) {

      // Server send message to player
      if (!worldIn.isClientSide) {
        playerIn.sendMessage(TextComponentUtils.fromMessage(() -> "You can only use this item in creative mode!"), Util.NIL_UUID);
        // Util.DUMMY_UUID = CommandSource#189
        return ActionResult.pass(playerIn.getItemInHand(handIn));
      }

    }

    // On client
    // Add particles in the old world
    if (worldIn.isClientSide) {
      for (int i = 0; i < 32; ++i) {
        playerIn.level.addParticle(ParticleTypes.PORTAL, true, playerIn.getX(), playerIn.getY() + random.nextDouble() * 2.0D, playerIn.getZ(), random.nextGaussian(), 0.0D,
            random.nextGaussian());
      }
    }

    // On the server
    if (!worldIn.isClientSide) {
      try {

        // Destination
        RegistryKey<World> worldKey;

        // Current
        RegistryKey<World> currentWorld = playerIn.level.dimension();

        // In Over-world
        if (currentWorld.compareTo(World.OVERWORLD) == 0) {
          worldKey = World.NETHER;
        }
        // In Nether
        else if (currentWorld.compareTo(World.NETHER) == 0) {
          worldKey = World.END;
        }
        // In End
        else if (currentWorld.compareTo(World.END) == 0) {
          worldKey = World.OVERWORLD;
        }
        // Default
        else {
          worldKey = World.OVERWORLD;
        }

        changeDimension(playerIn, worldIn, worldKey);

      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      return ActionResult.fail(playerIn.getItemInHand(handIn));
    }

    return super.use(worldIn, playerIn, handIn);
  }

  /**
   * Convenience method to change the dimension of the given entity.
   * 
   * @param entityIn
   * @param worldIn
   */
  private void changeDimension(Entity entityIn, World worldIn, RegistryKey<World> registryKey) {

    // Get the world of that key
    ServerWorld serverworld = ((ServerWorld) worldIn).getServer().getLevel(registryKey);

    // Change
    if (serverworld == null) {
      return;
    }
    entityIn.changeDimension(serverworld, new NoPortalTeleporter(serverworld));
  }

  @Override
  public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    tooltip.add(TextComponentUtils.fromMessage(() -> "Ever wanted to just right click to change dimension? Well now you can!"));

    super.appendHoverText(stack, worldIn, tooltip, flagIn);
  }

}
