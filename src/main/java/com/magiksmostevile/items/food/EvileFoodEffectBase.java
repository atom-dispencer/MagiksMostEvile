package com.magiksmostevile.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EvileFoodEffectBase extends EvileFoodBase{
	
	PotionEffect effect;

	public EvileFoodEffectBase(String name, int amount, float saturation, boolean isAnimalFood, PotionEffect effect) {
		super(name, amount, saturation, isAnimalFood);
		setAlwaysEdible(); //even if you aren't hungry, you can still eat the item
		
		this.effect = effect;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote) {
			player.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier(), effect.getIsAmbient(), effect.doesShowParticles()));
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
