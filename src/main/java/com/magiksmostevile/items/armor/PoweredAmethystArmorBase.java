package com.magiksmostevile.items.armor;

import com.magiksmostevile.init.EvileItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PoweredAmethystArmorBase extends EvileArmorBase {

	public PoweredAmethystArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
		if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null
				&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
			if (player.inventory.armorItemInSlot(3).getItem() == EvileItems.POWERED_AMETHYST_HELMET
					&& player.inventory.armorItemInSlot(2).getItem() == EvileItems.POWERED_AMETHYST_CHESTPLATE
					&& player.inventory.armorItemInSlot(1).getItem() == EvileItems.POWERED_AMETHYST_LEGGINGS
					&& player.inventory.armorItemInSlot(0).getItem() == EvileItems.POWERED_AMETHYST_BOOTS) {
				player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 2, 0, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2, 2, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 2, 0, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2, 0, false, false));
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect() {
		return true;
	}

}
