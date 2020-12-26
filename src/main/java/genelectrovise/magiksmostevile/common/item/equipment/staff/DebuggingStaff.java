package genelectrovise.magiksmostevile.common.item.equipment.staff;

import genelectrovise.magiksmostevile.common.data.ModdedTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextComponentUtils;

public class DebuggingStaff extends Item {

  public DebuggingStaff(Properties properties) {
    super(properties);
  }
  
  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    
    context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(() -> "ItemUsed"), Util.DUMMY_UUID);
    context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(() -> context.getWorld().getBlockState(context.getPos()).getBlock().getTags().toString()), Util.DUMMY_UUID);
    
    if(ModdedTags.AMETHYST_ORE_SPAWNABLE == null) {
      context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(() -> "#A_O_S nulled"), Util.DUMMY_UUID);
      return super.onItemUse(context);
    }
    

    Block block = context.getWorld().getBlockState(context.getPos()).getBlock();
    context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(() -> "Block is in A_O_S: " + new Boolean(ModdedTags.AMETHYST_ORE_SPAWNABLE.contains(block)).toString()), Util.DUMMY_UUID);
    
    return super.onItemUse(context);
  }

}
