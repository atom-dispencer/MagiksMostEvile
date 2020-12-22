package com.magiksmostevile.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MobDropsHandler 
{
	//Item amethyst;
	
	public MobDropsHandler() {
	}

	@SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
    {
        if (event.entity instanceof EntityWitch)
        {
        	Item amethyst = (Item)Item.itemRegistry.getObject("magiksmostevile:amethyst");
        	if (amethyst == null) {
        		System.out.println ("Amethyst not found");
        	} 
        	else {
        		System.out.println ("Amethyst loaded from registry");
         	}
            ItemStack stack = new ItemStack(amethyst);
            EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack);
 
            event.drops.add(drop);
        }
    }

}
