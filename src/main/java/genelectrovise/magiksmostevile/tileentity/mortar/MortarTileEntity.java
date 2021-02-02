package genelectrovise.magiksmostevile.tileentity.mortar;

import genelectrovise.magiksmostevile.registry.orbital.registries.TileEntityOrbitalRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class MortarTileEntity extends TileEntity {

  private ItemStackHandler slot = new ItemStackHandler() {
    protected void onContentsChanged(int slot) {
      markDirty();
    };
  };
  protected final LazyOptional<IItemHandler> slotHandler = LazyOptional.of(() -> slot);
  protected final LazyOptional<IItemHandler> allSlots = LazyOptional.of(() -> new CombinedInvWrapper(slot));

  public MortarTileEntity() {
    super(TileEntityOrbitalRegistry.TILE_ENTITY_MORTAR.get());
  }

  @Override
  public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {

    // IItemHandler
    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      this.markDirty();

      // if the block at myself isn't myself, allow full access (Block Broken)
      if (world != null && world.getBlockState(pos).getBlock() != this.getBlockState().getBlock()) {
        return allSlots.cast();
      }

      // If accessing from the top or bottom
      if (side == Direction.UP || side == Direction.DOWN) {
        return allSlots.cast();
      }
    }

    return super.getCapability(capability, side);
  }

  public void recieveItemStack(ItemStack playerHeldStack) {

    if (this.world.isRemote) {
      slotHandler.ifPresent((handler) -> {

        // Pop current item out
        ItemStack out = handler.extractItem(0, 1, false);
        ItemEntity entity = EntityType.ITEM.create(world);
        entity.setPosition(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, out);

        // Place new item in
        handler.insertItem(0, new ItemStack(playerHeldStack.getItem(), 1), false);
        playerHeldStack.shrink(1);
      });
    }
  }

  public void recipe() {
    
  }

}
