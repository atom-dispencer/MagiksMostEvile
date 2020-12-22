package com.magiksmostevile.items.food;

import com.magiksmostevile.Main;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.items.EvileItemBase;

import net.minecraft.item.ItemFood;
import util.IHasModel;

public class EvileFoodBase extends ItemFood implements IHasModel{
	/**
	 * 
	 * @param name the name
	 * @param amount amount of food bars it replenishes
	 * @param saturation how long you stay not-hungry 
	 * @param isAnimalFood is it animal food (eg bone eaten by wolf but not humans)
	 */
	public EvileFoodBase(String name, int amount, float saturation, boolean isAnimalFood) {     
		super(amount, saturation, isAnimalFood);
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
