package genelectrovise.magiksmostevile.item.equipment.tabulae;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.World;

public class TabulaSpringBreeze extends Tabula {

  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);

    // Only operate on the server
    if (world.isRemote) {
      return ActionResult.resultPass(stack);
    }

    // Make sure the player is actually holding the item!
    if (!(stack.getItem() instanceof TabulaMuddiedDust)) {
      return ActionResult.resultFail(stack);
    }

    // Logic
    BlockPos position = player.getPosition();
    int lowerLimit = -1;
    int upperLimit = 1;
    for (int x = position.getX() + lowerLimit; x < position.getX() + upperLimit + 1; x++) {
      for (int z = position.getZ() + lowerLimit; z < position.getZ() + upperLimit + 1; z++) {

        BlockPos movingPosition = new BlockPos(x, position.getY() - 1, z);
        BlockState state = world.getBlockState(movingPosition);
        // If dirt and 1/4 chance
        if (state.getBlock() == Blocks.DIRT && (world.getRandom().nextBoolean()) && world.getRandom().nextBoolean()) {
          world.setBlockState(movingPosition, Blocks.GRASS_BLOCK.getDefaultState(), 2);
        }
      }

    }

    return ActionResult.resultSuccess(stack);
  }

  @Override
  public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    tooltip.add(TextComponentUtils.toTextComponent(() -> "The wind brushes your cheek and you feel invigorated..."));
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
