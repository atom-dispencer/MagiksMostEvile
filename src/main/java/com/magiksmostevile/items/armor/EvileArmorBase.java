package com.magiksmostevile.items.armor;

import com.magiksmostevile.Main;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.items.EvileItemBase;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import util.IHasModel;

public class EvileArmorBase extends ItemArmor implements IHasModel{

	public EvileArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);
			
		EvileItems.EVILEITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory"); 
	}
}
