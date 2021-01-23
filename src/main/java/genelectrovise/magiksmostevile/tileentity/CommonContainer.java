/**
 * 
 */
package genelectrovise.magiksmostevile.tileentity;

import javax.annotation.Nonnull;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * Thanks to Draco18s for this lovely class!<br>
 * No-nonsense container class that does all the thigns a container needs to do.<br>
 * Every "container" TE can create a subclass of this to use for a GUI.
 * 
 * @author Draco18s
 *
 */
public class CommonContainer extends Container {
  protected final int invenSize;

  protected CommonContainer(ContainerType<?> type, int id, int size) {
    super(type, id);
    invenSize = size;
  }

  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {
    return true;
  }

  protected void bindPlayerInventory(PlayerInventory playerInventory) {
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    for (int k = 0; k < 9; ++k) {
      this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
    }
  }

  @Override
  @Nonnull
  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = (Slot) this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack()) {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();

      if (index < invenSize) {
        if (!this.mergeItemStack(itemstack1, invenSize, this.inventorySlots.size(), true)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.mergeItemStack(itemstack1, 0, invenSize, false)) {
        return ItemStack.EMPTY;
      }

      if (itemstack1.getCount() == 0) {
        slot.putStack(ItemStack.EMPTY);
      } else {
        slot.onSlotChanged();
      }
    }

    return itemstack;
  }
}
