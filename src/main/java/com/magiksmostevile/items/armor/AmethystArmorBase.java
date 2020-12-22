package com.magiksmostevile.items.armor;

import com.magiksmostevile.init.EvileItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class AmethystArmorBase extends EvileArmorBase {

	public AmethystArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
		if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null
				&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
			if (player.inventory.armorItemInSlot(3).getItem() == EvileItems.AMETHYST_HELMET
					&& player.inventory.armorItemInSlot(2).getItem() == EvileItems.AMETHYST_CHESTPLATE
					&& player.inventory.armorItemInSlot(1).getItem() == EvileItems.AMETHYST_LEGGINGS
					&& player.inventory.armorItemInSlot(0).getItem() == EvileItems.AMETHYST_BOOTS) {
				player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 2, 0, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, 0, false, false));
			}
		}
	}

}
