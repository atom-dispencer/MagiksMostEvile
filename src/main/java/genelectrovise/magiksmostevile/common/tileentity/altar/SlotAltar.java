/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * @author GenElectrovise 18 May 2020
 */
public class SlotAltar extends SlotItemHandler {

  /**
   * @param itemHandler
   * @param index
   * @param xPosition
   * @param yPosition
   */
  public SlotAltar(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
    super(itemHandler, index, xPosition, yPosition);
  }

  @Override
  public boolean isItemValid(ItemStack stack) {
    return true;
  }

}
