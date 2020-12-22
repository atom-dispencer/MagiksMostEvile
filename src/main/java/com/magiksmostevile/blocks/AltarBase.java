package com.magiksmostevile.blocks;

import java.util.Random;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.Main;
import com.magiksmostevile.init.EvileBlocks;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.items.EvileItemBase;
import com.magiksmostevile.tileentity.TileEntityAltar;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import util.IHasModel;

public class AltarBase extends Block implements IHasModel {
	public static AxisAlignedBB ALTAR_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 1D);

	private World world;

	public AltarBase(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);
		EvileLog.logText(true, EnumLogLevel.INFO, "Constructing ALTAR");
		setHardness(5);
		setSoundType(SoundType.CLOTH);
		
		EvileBlocks.EVILEBLOCKS.add(this);
		EvileItems.EVILEITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return ALTAR_AABB;
	}

	// ======================================================================================================================
	// TILE ENTITY

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return super.createTileEntity(world, state);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(EvileBlocks.ALTAR);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(EvileBlocks.ALTAR);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		try {
			playerIn.sendMessage(new TextComponentString("Altar activated! +++++++++++++++++++++++++++"));
			if (!worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentString("World is not remote"));
				playerIn.openGui(Main.instance, Main.GUI_ALTAR, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		} catch (Exception e) {
			EvileLog.logText(true, EnumLogLevel.ERROR, "An error has occurred opening the GUI for Altar at : " + pos + " : This is not an intended feature! :/");
			EvileLog.logText(true, EnumLogLevel.ERROR, e.getMessage() == null ? "No message to print!" : e.getMessage());
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		EvileLog.logText(true, EnumLogLevel.WARN, "Altar placed! Evile-ness incoming!");
	}

	public static void setState() {
		// TODO Check whether method needs population
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityAltar tileentity = (TileEntityAltar) worldIn.getTileEntity(pos);
		//TODO Used to drop inventory items here
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return super.withRotation(state, rot);
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return super.withMirror(state, mirrorIn);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return super.createBlockState();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return super.getStateFromMeta(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return super.getMetaFromState(state);
	}

	// https://www.youtube.com/watch?v=JMdDf0PCxIE&list=PLiDUvCGH5WEUEV9nc0Ll2pzUFmSFc21uR&index=19&t=451s
}
