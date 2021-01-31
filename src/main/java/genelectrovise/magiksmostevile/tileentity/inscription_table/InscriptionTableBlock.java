package genelectrovise.magiksmostevile.tileentity.inscription_table;

import genelectrovise.magiksmostevile.tileentity.ICustomContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LoomBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * {@link LoomBlock}
 * 
 * @author GenElectrovise
 *
 */
public class InscriptionTableBlock extends HorizontalBlock {

  public InscriptionTableBlock(Properties properties) {
    super(properties);
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new InscriptionTableTileEntity();
  }

  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
  }

  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(HORIZONTAL_FACING);
  }

  public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceResult) {
    if (!worldIn.isRemote) {
      final ICustomContainer tileEntity = (ICustomContainer) worldIn.getTileEntity(pos);
      tileEntity.openGUI((ServerPlayerEntity) player);
    }

    return ActionResultType.SUCCESS;
  }

}
