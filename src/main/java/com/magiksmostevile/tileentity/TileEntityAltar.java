package com.magiksmostevile.tileentity;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityAltar extends TileEntity implements ITickable /* , IItemHandler */ {

	public static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

	private final IItemHandler inventory = new ItemStackHandler(4) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
		}
	};

	private String customName;

	public TileEntityAltar() {
		super();
		System.out.println("Capability ITEM_HANDLER for Altar : " + this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
		EvileLog.logText(true, EnumLogLevel.INFO, "Constructing altar tile entity");
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		System.out.println("Checking ITEM_HANDLER_CAPABILIITY");
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		System.out.println("Does not have ITEM_HANDLER_CAPABILIITY");
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		System.out.println("Getting ITEM_HANDLER_CAPABILIITY");
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) inventory;
		}
		System.out.println("Cannot get ITEM_HANDLER_CAPABILIITY");
		return super.getCapability(capability, facing);
	}

	public String getName() {
		return this.hasCustomName() ? this.customName : "container.altar";
	}

	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

	private void setCustomName(String customNameIn) {
		this.customName = customNameIn;
	}

	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return compound;
	}

	@Override
	public void onLoad() {

	}

	@Override
	public void update() {

	}

	public String getGuiID() {
		return "magiksmostevile:gui_altar";
	}

	/*
	 * @Override public ItemStack getStackInSlot(int slot) { return (ItemStack) this.inventory.getStackInSlot(slot); }
	 * @Override public int getSlots() { return 4; }
	 * @Override public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) { return null; }
	 * @Override public ItemStack extractItem(int slot, int amount, boolean simulate) { return null; }
	 * @Override public int getSlotLimit(int slot) { return 64; }
	 */

}
