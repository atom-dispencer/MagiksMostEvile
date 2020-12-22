package com.magiksmostevile.handler;

import com.magiksmostevile.guis.GuiAltar;
import com.magiksmostevile.tileentity.TileEntityAltar;
import com.magiksmostevile.tileentity.container.ContainerAltar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	/*
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Main.GUI_ALTAR)
			return new ContainerAltar(player.inventory, (TileEntityAltar) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Main.GUI_ALTAR)
			return new GuiAltar(player.inventory, (TileEntityAltar) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
	*/

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if(tileEntity instanceof TileEntityAltar) {
			TileEntityAltar tileEntityAltar = (TileEntityAltar)tileEntity;
			return new ContainerAltar(player.inventory, tileEntityAltar);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if(tileEntity instanceof TileEntityAltar) {
			TileEntityAltar tileEntityAltar = (TileEntityAltar)tileEntity;
			return new GuiAltar(player.inventory, tileEntityAltar);
		}
		return null;
	}
	
}
