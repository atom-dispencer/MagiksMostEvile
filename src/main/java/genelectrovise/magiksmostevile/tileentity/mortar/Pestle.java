package genelectrovise.magiksmostevile.tileentity.mortar;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class Pestle extends Item {

  private EnumPestleLevel level;

  public Pestle(Properties properties, EnumPestleLevel level) {
    super(properties);
    this.level = level;
  }

  public EnumPestleLevel getLevel() {
    return level;
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {

    // On client, check for ActionResultType
    if (context.getWorld().isRemote) {
      return context.getWorld().getBlockState(context.getPos()).getBlock() instanceof MortarBlock ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    // On server, handle recipe
    ServerWorld world = (ServerWorld) context.getWorld();
    BlockPos position = context.getPos();
    Block block = world.getBlockState(position).getBlock();

    if (block instanceof MortarBlock) {
      TileEntity tileEntity = world.getTileEntity(position);
      if (tileEntity instanceof MortarTileEntity) {
        ((MortarTileEntity)tileEntity).recipe();
      }
    }

    return ActionResultType.SUCCESS;
  }

}
