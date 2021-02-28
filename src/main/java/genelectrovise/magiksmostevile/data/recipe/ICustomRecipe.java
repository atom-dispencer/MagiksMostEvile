package genelectrovise.magiksmostevile.data.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public interface ICustomRecipe extends IRecipe<IInventory> {

  /**
   * A replacement for {@link IRecipe#matches(IInventory, World)} which uses a forge
   * {@link CombinedInvWrapper} instead.
   * 
   * @param inv
   * @param usage
   * @return
   */
  boolean craftable(CombinedInvWrapper inv, Usage usage);
}
