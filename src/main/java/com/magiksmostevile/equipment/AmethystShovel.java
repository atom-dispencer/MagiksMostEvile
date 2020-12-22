package com.magiksmostevile.equipment;

import com.magiksmostevile.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class AmethystShovel extends ItemSpade {
	public AmethystShovel(String itemname, Item.ToolMaterial mat) {
		super(mat);
		this.setUnlocalizedName(itemname);
		this.setTextureName(Main.MODID + ":" + itemname);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon("magiksmostevile:amethystShovel");
	}
}