/**
 * 
 */
package genelectrovise.magiksmostevile.common.item.equipment.staff;

import java.util.List;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.impl.GiveCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.server.command.CommandSetDimension;

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
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
      Hand handIn) {

    // If the player is in creative
    if (!playerIn.isCreative()) {

      // Server send message to player
      if (!worldIn.isRemote) {
        playerIn.sendMessage(TextComponentUtils.toTextComponent(
            () -> "You can only use this item in creative mode!"), Util.DUMMY_UUID);
        // Util.DUMMY_UUID = CommandSource#189
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
      }

    }

    // On client
    // Add particles in the old world
    if (worldIn.isRemote) {
      for (int i = 0; i < 32; ++i) {
        playerIn.world.addParticle(ParticleTypes.PORTAL, true, playerIn.getPosX(),
            playerIn.getPosY() + random.nextDouble() * 2.0D, playerIn.getPosZ(),
            random.nextGaussian(), 0.0D, random.nextGaussian());
      }
    }

    // On the server
    if (!worldIn.isRemote) {
      try {

        // Destination
        RegistryKey<World> worldKey;

        // In Over-world
        if (playerIn.world.getDimensionKey().compareTo(DimensionType.OVERWORLD) == 0) {
          worldKey = World.THE_NETHER;
        }
        // In Nether
        else if (playerIn.world.getDimensionKey().compareTo(DimensionType.THE_NETHER) == 0) {
          worldKey = World.THE_END;
        }
        // In End
        else if (playerIn.world.getDimensionKey().compareTo(DimensionType.THE_END) == 0) {
          worldKey = World.OVERWORLD;
        }
        // Default
        else {
          worldKey = World.OVERWORLD;
        }


      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      return ActionResult.resultFail(playerIn.getHeldItem(handIn));
    }

    return super.onItemRightClick(worldIn, playerIn, handIn);
  }

  /**
   * Convenience method to change the dimension of the given entity.
   * 
   * @param entityIn
   * @param worldIn
   */
  private void changeDimension(Entity entityIn, World worldIn, RegistryKey<World> registryKey) {

    // Get the world of that key
    ServerWorld serverworld = ((ServerWorld) worldIn).getServer().getWorld(registryKey);

    // Change
    if (serverworld == null) {
      return;
    }
    entityIn.changeDimension(serverworld);
  }

  @Override
  public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip,
      ITooltipFlag flagIn) {

    tooltip.add(TextComponentUtils.toTextComponent(
        () -> "Ever wanted to just right click to change dimension? Well now you can!"));

    super.addInformation(stack, worldIn, tooltip, flagIn);
  }

}
