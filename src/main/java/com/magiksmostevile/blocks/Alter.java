package com.magiksmostevile.blocks;


import com.magiksmostevile.AlterGui;
import com.magiksmostevile.EvileLog;
import com.magiksmostevile.Main;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class Alter extends BlockContainer {
		private static final String name = "alter";
		
		private IIcon[] icons = new IIcon[6];
	
		public Alter(Material material, String blockName)
		{
			super(material);
			this.setBlockName(blockName);
			this.setBlockTextureName(Main.MODID + ":" + blockName);
			this.setCreativeTab(CreativeTabs.tabDecorations);
			this.setHardness(4.0F);
			this.setHarvestLevel("pickaxe", 1);
		}
		
		@Override
		public void registerBlockIcons(IIconRegister reg)
		{
			for (int i = 0; i < 6; i++)
			{
				icons[i] = reg.registerIcon(Main.MODID + ":" + name + "_" + i);
				//this means that textures need to be called 'alter_1', 'alter_2', etc.
				//side 0 is bottom, 1 top, 2 North, 3 South, 4 West, 5 East
			}
		}
		
		@Override
		public IIcon getIcon(int side, int meta)
		{
			return icons[side];
		}
		
		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer entityPlayer, int blockMetadata, float playerX, float playerY, float playerZ) {
			if (!world.isRemote) {
				Minecraft.getMinecraft().displayGuiScreen(new AlterGui());
				EvileLog.LogText(true, 1, "This is a logging test");
			}
			return true;
		}
		//    http://jabelarminecraft.blogspot.com/p/minecraft-modding-containers.html

		
		
		
    }