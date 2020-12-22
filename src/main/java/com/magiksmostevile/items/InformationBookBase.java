package com.magiksmostevile.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

public class InformationBookBase extends EvileItemBase {

    public InformationBookBase(String name) {
	super(name);
    }
    
    private void addInfoToBook() {
	//TODO NBT data etc.
    }
}
