package com.magiksmostevile.tileentity;

import java.util.Iterator;

import com.google.common.collect.ImmutableMap;
import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.init.EvileItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPotato;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAmethystCrystal extends TileEntity implements ITickable {

    private String customName;
    private World world;

    public TileEntityAmethystCrystal() {
	super();
	EvileLog.logText(true, EnumLogLevel.INFO,
		"Constructing crystal tile entity (Not called previously - added by me)");
    }

    private String getName() {
	return this.hasCustomName() ? this.customName : "te.tile_entity_amethyst_crystal";
    }

    private boolean hasCustomName() {
	return this.customName != null && !this.customName.isEmpty();
    }

    private void setCustomName(String customNameIn) {
	this.customName = customNameIn;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
	super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	return super.writeToNBT(compound);
    }

    @Override
    public void onLoad() { // HELPFUL POST FOR GETTING WORLD>>>
			   // https://www.minecraftforge.net/forum/topic/41640-world-and-position-empty-in-tile-entity/
	this.world = this.getWorld();
    }

    @Override
    public void update() {
	int posX = this.getPos().getX();
	int posY = this.getPos().getY();
	int posZ = this.getPos().getZ();
	int i = posX;
	int j = posY;
	int k = posZ;

	EvileLog.logText(false, EnumLogLevel.INFO, "tile entity coords are: " + posX + ":" + posY + ":" + posZ);

	try {
	    for (i = posX - 2; i < posX + 3; i++) {
		for (j = posY - 1; j < posY + 1; j++) {
		    for (k = posZ - 2; k < posZ + 3; k++) {
			BlockPos blockPos = new BlockPos(i, j, k);
			Block block = world.getBlockState(blockPos).getBlock();
			EvileLog.logText(false, EnumLogLevel.INFO, "for loop 3; world is: " + world + "; blockPos is: "
				+ blockPos + "; block is: " + block);
			doBlockReplacement(block, blockPos);
		    }

		}
	    }
	} catch (NullPointerException e) {
	    System.out.println("NullPointerException! world.getBlockState(blockPos).getBlock() returned null? ");
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void doBlockReplacement(Block block, BlockPos blockPos) {
	if (block instanceof BlockPotato) {
	    //int spudAge = (int) world.getBlockState(pos).getValue(BlockPotato.AGE);
	    int spudAge = (int) world.getBlockState(blockPos).getValue(BlockPotato.AGE);
	    if (spudAge == 7) {
		doReplacement(blockPos);
	    }

	}
    }

    private void doReplacement(BlockPos blockPos) {
	IBlockState air = Blocks.AIR.getDefaultState();
	world.setBlockState(blockPos, air);
	spawnEntityItemAmethystPotato(blockPos);
    }

    private void spawnEntityItemAmethystPotato(BlockPos blockPos) {
	if (!world.isRemote) {
	    Entity entity = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
		    new ItemStack(EvileItems.AMETHYST_POTATO, 1));
	    world.spawnEntity(entity);
	}
    }
}
