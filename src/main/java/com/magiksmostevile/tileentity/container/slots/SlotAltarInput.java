package com.magiksmostevile.tileentity.container.slots;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Deprecated
/**
 * Use standard slots instead. This is a backup class.
 * @author adam_
 *
 */
public class SlotAltarInput extends Slot {
    
    private int index;
    private Item[] validSlot0 = {};
    private Item[] validSlot1 = {};
    private Item[] validSlot3 = {};
    private Item[] validSlot4 = {};
    //TODO better way to do this. Implement?


    public SlotAltarInput(IInventory inventory, int index, int x, int y) {
	super(inventory, index, x, y);
	this.index = index;
    }
    
    @Override
    public boolean isItemValid(ItemStack stack) {
        return super.isItemValid(stack);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        return super.getItemStackLimit(stack);
    }
    

}
