package com.magiksmostevile.init;

import java.util.ArrayList;
import java.util.List;

import com.magiksmostevile.EvileMaterials;
import com.magiksmostevile.items.EvileGlowingItemBase;
import com.magiksmostevile.items.EvileGoingToTheBallBase;
import com.magiksmostevile.items.EvileItemBase;
import com.magiksmostevile.items.EvileTomeBase;
import com.magiksmostevile.items.InformationBookBase;
import com.magiksmostevile.items.armor.AmethystArmorBase;
import com.magiksmostevile.items.armor.OverPoweredAmethystArmorBase;
import com.magiksmostevile.items.armor.PoweredAmethystArmorBase;
import com.magiksmostevile.items.eviletools.EvileToolAxe;
import com.magiksmostevile.items.eviletools.EvileToolHoe;
import com.magiksmostevile.items.eviletools.EvileToolPickaxe;
import com.magiksmostevile.items.eviletools.EvileToolSpade;
import com.magiksmostevile.items.eviletools.EvileToolSword;
import com.magiksmostevile.items.food.EvileFoodEffectBase;

import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EvileItems {
	public static final List<Item> EVILEITEMS = new ArrayList<Item>();
	
	//Information Book
	public static final Item INFORMATION_BOOK = new InformationBookBase("information_book");
	
	//Items EvileItemBase
	public static final Item AMETHYST = new EvileItemBase("amethyst");
	public static final Item LESSER_POWER_STONE = new EvileItemBase("lesser_power_stone");
	public static final Item VAMPIRE_BAT_TOOTH = new EvileItemBase("vampire_bat_tooth");
	public static final Item VAMPIRE_BAT_GUANO = new EvileItemBase("vampire_bat_guano");
	public static final Item GOING_TO_THE_BALL = new EvileGoingToTheBallBase("going_to_the_ball");
	
	//TOMES
	public static final Item TOME_CONVERT_AMETHYST = new EvileTomeBase("tome_convert_amethyst", 1);
	
	//Glowing Items EvileGlowingItemBase
	public static final Item POWERED_AMETHYST = new EvileGlowingItemBase("powered_amethyst");
	public static final Item GREATER_POWER_STONE = new EvileGlowingItemBase("greater_power_stone");
	public static final Item DOOM = new EvileGlowingItemBase("doom");
	public static final Item EMP = new EvileGlowingItemBase("emp");
	
	//Tools EvileToolBase
	public static final ItemSword AMETHYST_SWORD = new EvileToolSword("amethyst_sword", EvileMaterials.MATERIAL_AMETHYST);
	public static final ItemSpade AMETHYST_SPADE = new EvileToolSpade("amethyst_shovel", EvileMaterials.MATERIAL_AMETHYST);
	public static final ItemAxe AMETHYST_AXE = new EvileToolAxe("amethyst_axe", EvileMaterials.MATERIAL_AMETHYST);
	public static final ItemPickaxe AMETHYST_PICKAXE = new EvileToolPickaxe("amethyst_pickaxe", EvileMaterials.MATERIAL_AMETHYST);
	public static final ItemHoe AMETHYST_HOE = new EvileToolHoe("amethyst_hoe", EvileMaterials.MATERIAL_AMETHYST);
	
	//Armour EvileArmorBase
	public static final Item AMETHYST_HELMET = new AmethystArmorBase("amethyst_helmet", EvileMaterials.ARMOR_MATERIAL_AMETHYST, 1, EntityEquipmentSlot.HEAD);
	public static final Item AMETHYST_CHESTPLATE = new AmethystArmorBase("amethyst_chestplate", EvileMaterials.ARMOR_MATERIAL_AMETHYST, 1, EntityEquipmentSlot.CHEST);
	public static final Item AMETHYST_LEGGINGS = new AmethystArmorBase("amethyst_leggings", EvileMaterials.ARMOR_MATERIAL_AMETHYST, 2, EntityEquipmentSlot.LEGS);
	public static final Item AMETHYST_BOOTS = new AmethystArmorBase("amethyst_boots", EvileMaterials.ARMOR_MATERIAL_AMETHYST, 1, EntityEquipmentSlot.FEET);
	
	public static final Item POWERED_AMETHYST_HELMET = new PoweredAmethystArmorBase("powered_amethyst_helmet", EvileMaterials.ARMOR_MATERIAL_POWERED_AMETHYST, 1, EntityEquipmentSlot.HEAD);
	public static final Item POWERED_AMETHYST_CHESTPLATE = new PoweredAmethystArmorBase("powered_amethyst_chestplate", EvileMaterials.ARMOR_MATERIAL_POWERED_AMETHYST, 1, EntityEquipmentSlot.CHEST);
	public static final Item POWERED_AMETHYST_LEGGINGS = new PoweredAmethystArmorBase("powered_amethyst_leggings", EvileMaterials.ARMOR_MATERIAL_POWERED_AMETHYST, 2, EntityEquipmentSlot.LEGS);
	public static final Item POWERED_AMETHYST_BOOTS = new PoweredAmethystArmorBase("powered_amethyst_boots", EvileMaterials.ARMOR_MATERIAL_POWERED_AMETHYST, 1, EntityEquipmentSlot.FEET);
	
	public static final Item OVER_POWERED_AMETHYST_HELMET = new OverPoweredAmethystArmorBase("over_powered_amethyst_helmet", EvileMaterials.ARMOR_MATERIAL_OVER_POWERED_AMETHYST, 1, EntityEquipmentSlot.HEAD);
	public static final Item OVER_POWERED_AMETHYST_CHESTPLATE = new OverPoweredAmethystArmorBase("over_powered_amethyst_chestplate", EvileMaterials.ARMOR_MATERIAL_OVER_POWERED_AMETHYST, 1, EntityEquipmentSlot.CHEST);
	public static final Item OVER_POWERED_AMETHYST_LEGGINGS = new OverPoweredAmethystArmorBase("over_powered_amethyst_leggings", EvileMaterials.ARMOR_MATERIAL_OVER_POWERED_AMETHYST, 2, EntityEquipmentSlot.LEGS);
	public static final Item OVER_POWERED_AMETHYST_BOOTS = new OverPoweredAmethystArmorBase("over_powered_amethyst_boots", EvileMaterials.ARMOR_MATERIAL_OVER_POWERED_AMETHYST, 1, EntityEquipmentSlot.FEET);
	
	//Food EvileFoodBase
	
	//Potion food EvileFoodEffectBase
	public static final Item AMETHYST_POTATO = new EvileFoodEffectBase("amethyst_potato", 5, 6.5F, false, new PotionEffect(MobEffects.SPEED, (120*20), 1, false, true));





	
	/**
	 * @param stack (the stack holding the item which should/not glow)
	 * @return whether the item should glow
	 **/
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;	
	}
	
}



