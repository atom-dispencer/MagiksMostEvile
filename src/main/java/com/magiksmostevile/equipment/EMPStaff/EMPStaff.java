package com.magiksmostevile.equipment.EMPStaff;

import com.magiksmostevile.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EMPStaff extends Item
{
    public EMPStaff(String itemname)
    {
    	this.setUnlocalizedName(itemname);
		this.setTextureName(Main.MODID + ":" + itemname);
		setCreativeTab(CreativeTabs.tabCombat);
        setMaxStackSize(1);
        setMaxDamage(0);
    }
 
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(GameRegistry.findItem("magiksmostevile", "emp")))
        {
            player.swingItem();
            world.playSoundAtEntity(player, Main.MODID + ":emp_gun", 0.5F, 1.0F);
            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityEMP(world, player));
            }
        }
        return itemStack;
    }
}