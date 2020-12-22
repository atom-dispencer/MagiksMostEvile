package com.magiksmostevile.tileentity.container;

import com.magiksmostevile.tileentity.TileEntityAltar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAltar extends Container { // https://www.youtube.com/watch?v=rARVLYm9TBs&list=PLiDUvCGH5WEUEV9nc0Ll2pzUFmSFc21uR&index=22&t=366s

	private final TileEntityAltar tileentity;

	public ContainerAltar(InventoryPlayer player, TileEntityAltar tileentity) {

		this.tileentity = tileentity;

		System.out.println("Constructing ContainerAltar");

		//this.addSlotToContainer(new SlotItemHandler(tileentity, 0, 305, 185));
		//this.addSlotToContainer(new SlotItemHandler(tileentity, 1, 327, 185));
		//this.addSlotToContainer(new SlotItemHandler(tileentity, 2, 305, 208));
		//this.addSlotToContainer(new SlotItemHandler(tileentity, 3, 327, 208));

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.getDistanceSq(playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ()) < 3;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		return super.transferStackInSlot(playerIn, index);
	}

}
