/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.Main;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainer extends Container {

	// 159 81
	// 181 81
	// 159 104
	// 181 104

	private final IInventory inventory = new Inventory(4) {
		public void markDirty() {
			super.markDirty();
			AltarContainer.this.onCraftMatrixChanged(this);
		}
	};
	private IWorldPosCallable worldPosCallable;

	/*
	 * public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer
	 * extraData) { super(EvileDeferredRegistry.ALTAR_CONTAINER.get(),
	 * ContainerReference.ALTAR); Main.LOGGER.
	 * debug("Constructing AltarContainer! (Constructor 1 : id, inv, data)");
	 * 
	 * addSlots(); }
	 */

	public AltarContainer(int windowId, PlayerInventory inv) {
		this(windowId, inv, IWorldPosCallable.DUMMY);
	}

	/**
	 * 
	 */
	public AltarContainer(int windowId, PlayerInventory inv, IWorldPosCallable worldPosCallable) {
		super(EvileDeferredRegistry.ALTAR_CONTAINER.get(), windowId);
		Main.LOGGER.debug("Constructing AltarContainer! (Constructor 3 : id, inv, callable)");
		this.worldPosCallable = worldPosCallable;

		addSlots(inv);
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(PlayerEntity playerIn) {

	}

	private void addSlots(PlayerInventory playerInventory) {

		int x1 = 69;
		int y1 = 76;
		int xDiff = 22;
		int yDiff = 22;

		// Altar
		Slot slot_0 = new Slot(inventory, 0, x1, y1);
		Slot slot_1 = new Slot(inventory, 1, x1 + xDiff, y1);
		Slot slot_2 = new Slot(inventory, 2, x1, y1 + yDiff);
		Slot slot_3 = new Slot(inventory, 3, x1 + xDiff, y1 + yDiff);

		addSlot(slot_0);
		addSlot(slot_1);
		addSlot(slot_2);
		addSlot(slot_3);

		Main.LOGGER.debug("slot_0 : index=" + slot_0.getSlotIndex() + " x=" + slot_0.xPos + " y=" + slot_0.yPos + " No.=" + slot_0.slotNumber + " inv=" + slot_0.inventory);
		Main.LOGGER.debug("slot_1 : index=" + slot_1.getSlotIndex() + " x=" + slot_1.xPos + " y=" + slot_1.yPos + " No.=" + slot_1.slotNumber + " inv=" + slot_1.inventory);
		Main.LOGGER.debug("slot_2 : index=" + slot_2.getSlotIndex() + " x=" + slot_2.xPos + " y=" + slot_2.yPos + " No.=" + slot_2.slotNumber + " inv=" + slot_2.inventory);
		Main.LOGGER.debug("slot_3 : index=" + slot_3.getSlotIndex() + " x=" + slot_3.xPos + " y=" + slot_3.yPos + " No.=" + slot_3.slotNumber + " inv=" + slot_3.inventory);

		// First is at 8,8 or 9,9
		int startX;
		int startY;

		// Player inventory
		for (int i = 0; i < 3; ++i) { // Y
			for (int j = 0; j < 9; ++j) { // X
				Slot invSlot = new Slot(playerInventory, j + i * 9 + 9, 184 + i * 18, 8 + j * 18 - 5);
				addSlot(invSlot);
				Main.LOGGER.debug("slot : index=" + invSlot.getSlotIndex() + " x=" + invSlot.xPos + " y=" + invSlot.yPos + " No.=" + invSlot.slotNumber + " inv=" + invSlot.inventory);
			}
		}

		for (int k = 0; k < 9; ++k) {
			Slot hotbarSlot = new Slot(playerInventory, k, 242, 8 + k * 18 - 5);
			addSlot(hotbarSlot);
			Main.LOGGER.debug("slot : index=" + hotbarSlot.getSlotIndex() + " x=" + hotbarSlot.xPos + " y=" + hotbarSlot.yPos + " No.=" + hotbarSlot.slotNumber + " inv=" + hotbarSlot.inventory);
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(worldPosCallable, playerIn, EvileDeferredRegistry.ALTAR.get());
	}

}
