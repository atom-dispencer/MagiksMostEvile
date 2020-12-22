package com.magiksmostevile;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class EvileMaterials {
	public static final ToolMaterial MATERIAL_AMETHYST = EnumHelper.addToolMaterial("AMETHYST", 2, 700, 10.0F, 2.5F, 50); // name, harvestLevel, maxUses, efficiency, damage, enchantability
	
	public static final ArmorMaterial ARMOR_MATERIAL_AMETHYST = EnumHelper.addArmorMaterial("armor_material_amethyst", Main.MODID + ":amethyst", 12, 
			new int[] {2, 6, 5, 3}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

	public static final ArmorMaterial ARMOR_MATERIAL_POWERED_AMETHYST = EnumHelper.addArmorMaterial("armor_material_powered_amethyst", Main.MODID + ":amethyst", 200,
			new int[] {3, 9, 8, 4}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F); 
	
	public static final ArmorMaterial ARMOR_MATERIAL_OVER_POWERED_AMETHYST = EnumHelper.addArmorMaterial("armor_material_over_powered_amethyst", Main.MODID + ":amethyst", 999999999, 
			new int[] {5, 15, 13, 5}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

}
