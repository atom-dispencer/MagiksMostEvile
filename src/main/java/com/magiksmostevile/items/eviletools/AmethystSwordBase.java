package com.magiksmostevile.items.eviletools;

import com.magiksmostevile.init.EvileItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class AmethystSwordBase extends EvileToolSword {

    public AmethystSwordBase(String name, ToolMaterial material) {
	super(name, material);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
	if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null
		&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
	    if (player.inventory.armorItemInSlot(3).getItem() == EvileItems.POWERED_AMETHYST_HELMET
		    && player.inventory.armorItemInSlot(2).getItem() == EvileItems.POWERED_AMETHYST_CHESTPLATE
		    && player.inventory.armorItemInSlot(1).getItem() == EvileItems.POWERED_AMETHYST_LEGGINGS
		    && player.inventory.armorItemInSlot(0).getItem() == EvileItems.POWERED_AMETHYST_BOOTS) {
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 6, 0, false, false));
	    }
	}
	return false;
    }
    
    @Override
    public boolean hitEntity(ItemStack item, EntityLivingBase entity, EntityLivingBase player)
    {
        item.damageItem(0, player);
        return true;
    }

}
