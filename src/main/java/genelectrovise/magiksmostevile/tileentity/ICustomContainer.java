/**
 * 
 */
package genelectrovise.magiksmostevile.tileentity;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;

/**
 * @author GenElectrovise 18 May 2020
 */
public interface ICustomContainer extends INamedContainerProvider {
  void openGUI(ServerPlayerEntity player);
}
