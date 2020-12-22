package com.magiksmostevile.items;

import java.util.Random;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.Main;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.init.EvileItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import util.IHasModel;

public class EvileGoingToTheBallBase extends Item implements IHasModel {
    /**
     * 
     * @param name Registry & Unlocalised name of item
     */
    public EvileGoingToTheBallBase(String name) {
	setUnlocalizedName(name);
	setRegistryName(name);

	EvileItems.EVILEITEMS.add(this);
    }

    @Override
    public void registerModels() {
	Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
	    EnumFacing facing, float hitX, float hitY, float hitZ) {

	EvileLog.logText(true, EnumLogLevel.INFO, "We're going to the ball!!");

	ItemStack itemstack = player.getHeldItem(hand);

	worldIn.setBlockState(pos, Blocks.PUMPKIN.getDefaultState());

	if (!worldIn.isRemote) {
	    spawnNobleSteeds(worldIn, pos);
	    spawnItemFountain(pos, worldIn);
	}
	itemstack.shrink(1);

	return EnumActionResult.SUCCESS;

    }

    private void spawnNobleSteeds(World worldIn, BlockPos pos) {
	EntityHorse horse1 = new EntityHorse(worldIn);
	horse1.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
	horse1.setHorseArmorStack(new ItemStack(Items.GOLDEN_HORSE_ARMOR));
	worldIn.spawnEntity(horse1);
	

	EntityHorse horse2 = new EntityHorse(worldIn);
	horse2.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
	horse2.setHorseArmorStack(new ItemStack(Items.GOLDEN_HORSE_ARMOR));
	worldIn.spawnEntity(horse2);

    }

    public void spawnItemFountain(BlockPos pos, World worldIn) {
	ItemStack pumpkins = new ItemStack(Blocks.PUMPKIN);
	ItemStack iron = new ItemStack(Items.IRON_INGOT);
	ItemStack diamond = new ItemStack(Items.DIAMOND);
	ItemStack gold = new ItemStack(Items.GOLD_INGOT);
	ItemStack amethyst = new ItemStack(EvileItems.AMETHYST);
	ItemStack emerald = new ItemStack(Items.EMERALD, 1);

	ItemStack[] itemsPossible = new ItemStack[14];
	itemsPossible[0] = pumpkins;
	itemsPossible[1] = iron;
	itemsPossible[2] = diamond;
	itemsPossible[3] = gold;
	itemsPossible[4] = amethyst;
	itemsPossible[5] = emerald;
	itemsPossible[6] = pumpkins;
	itemsPossible[7] = pumpkins;
	itemsPossible[8] = pumpkins;
	itemsPossible[9] = pumpkins;
	itemsPossible[10] = gold;
	itemsPossible[11] = gold;
	itemsPossible[12] = gold;
	itemsPossible[13] = amethyst;

	Random rand = new Random();
	int i;

	for (i = 0; i < 120; i++) {
	    ItemStack itemForStack = itemsPossible[rand.nextInt(itemsPossible.length)];
	    EntityItem itemToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), itemForStack);

	    worldIn.spawnEntity(itemToSpawn);
	    itemToSpawn.motionY = 0.25;
	    itemToSpawn.motionX = rand.nextInt(1000) / 10000;
	    itemToSpawn.motionZ = rand.nextInt(1000) / 10000;
	
	}
    }
}
