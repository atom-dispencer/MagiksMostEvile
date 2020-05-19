/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.tileentity.CommonContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainer extends CommonContainer {

	public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
		this(windowId, inv, new ItemStackHandler(4), (AltarTileEntity) Minecraft.getInstance().world.getTileEntity(data.readBlockPos()));
	}

	public AltarContainer(int windowId, PlayerInventory inv, IItemHandler handler, AltarTileEntity altar) {
		super(EvileDeferredRegistry.ALTAR_CONTAINER.get(), windowId, 4);
		MagiksMostEvile.LOGGER.debug("Constructing AltarContainer! (Constructor 3 : id, inv, callable)");

		addSlots(inv, handler);
	}

	private void addSlots(PlayerInventory playerInventory, IItemHandler handler) {
		int x1 = 69;
		int y1 = 76;
		int xDiff = 22;
		int yDiff = 22;

		// Altar
		addSlot(new SlotAltar(handler, 0, x1, y1));
		addSlot(new SlotAltar(handler, 1, x1 + xDiff, y1));
		addSlot(new SlotAltar(handler, 2, x1, y1 + yDiff));
		addSlot(new SlotAltar(handler, 3, x1 + xDiff, y1 + yDiff));

		// Player inventory
		for (int i = 0; i < 3; ++i) { // Y
			for (int j = 0; j < 9; ++j) { // X
				Slot invSlot = new Slot(playerInventory, j + i * 9 + 9, 184 + i * 18, 8 + j * 18 - 5);
				addSlot(invSlot);
				//MagiksMostEvile.LOGGER.debug("slot : index=" + invSlot.getSlotIndex() + " x=" + invSlot.xPos + " y=" + invSlot.yPos + " No.=" + invSlot.slotNumber + " inv=" + invSlot.inventory);
			}
		}

		for (int k = 0; k < 9; ++k) {
			Slot hotbarSlot = new Slot(playerInventory, k, 242, 8 + k * 18 - 5);
			addSlot(hotbarSlot);
			//MagiksMostEvile.LOGGER.debug("slot : index=" + hotbarSlot.getSlotIndex() + " x=" + hotbarSlot.xPos + " y=" + hotbarSlot.yPos + " No.=" + hotbarSlot.slotNumber + " inv=" + hotbarSlot.inventory);
		}
	}

}
