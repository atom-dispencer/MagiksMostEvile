/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.Main;
import genelectrovise.magiksmostevile.common.main.reference.ContainerReference;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise
 * 14 May 2020
 */
public class AltarContainer extends Container {
	
	private final IInventory inventory = new Inventory(3);

	/**
	 * @param type
	 * @param id
	 */
	public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer extraData) {
		super(EvileDeferredRegistry.ALTAR_CONTAINER.get(), ContainerReference.ALTAR);
		Main.LOGGER.debug("Constructing AltarContainer! (Constructor 1 : id, inv, data)");
	}
	
	public AltarContainer(int windowId, PlayerInventory inv) {
		super(EvileDeferredRegistry.ALTAR_CONTAINER.get(), ContainerReference.ALTAR);
		Main.LOGGER.debug("Constructing AltarContainer! (Constructor 2 : id, inv)");
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return false;
	}

}
