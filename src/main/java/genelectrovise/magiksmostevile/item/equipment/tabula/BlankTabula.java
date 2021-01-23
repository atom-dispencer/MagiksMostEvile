package genelectrovise.magiksmostevile.item.equipment.tabula;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BlankTabula extends Tabula {

  public BlankTabula() {
    super();
  }
  
  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
  }

}
