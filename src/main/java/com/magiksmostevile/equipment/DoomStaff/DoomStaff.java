package com.magiksmostevile.equipment.DoomStaff;

import com.magiksmostevile.Main;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DoomStaff extends Item
{
    public DoomStaff(String itemname)
    {
    	this.setUnlocalizedName(itemname);
		this.setTextureName(Main.MODID + ":" + itemname);
		setCreativeTab(CreativeTabs.tabCombat);
        setMaxStackSize(1);
        setMaxDamage(10000);
    }
 
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(GameRegistry.findItem("magiksmostevile", "doom")))
        {
            player.swingItem();
            world.playSoundAtEntity(player, Main.MODID + ":emp_gun", 0.5F, 1.0F);
            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityDoom(world, player));
            }
        }
        return itemStack;
    }
}