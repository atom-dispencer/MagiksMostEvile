package com.magiksmostevile.blocks;

import java.util.Random;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.init.EvileBlocks;
import com.magiksmostevile.tileentity.TileEntityAmethystCrystal;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AmethystCrystalBase extends BlockBase implements ITileEntityProvider {
    public static AxisAlignedBB AMETHYST_CRYSTAL_AABB = new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 0.625D,
	    0.9375D);

    private World world;

	public AmethystCrystalBase(String name, Material material) {
	super(name, material);
	setHardness(2F);
	EvileLog.logText(true, EnumLogLevel.INFO, "Constructing amethyst_crystal");
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
	return AMETHYST_CRYSTAL_AABB;
    }
    
    // ====================================================================================================================== TILE ENTITY

    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
	EvileLog.logText(true, EnumLogLevel.INFO, "creating tile entity for amethyst crystal");
	return new TileEntityAmethystCrystal();
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state) {
	return true;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(EvileBlocks.WHAT_BLOCK);
    }
    
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(EvileBlocks.WHAT_BLOCK);
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	return true;
    }
    
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    }
    
    /*
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityAmethystCrystal();
    }
    */
    
    
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
    
    //     https://www.youtube.com/watch?v=JMdDf0PCxIE&list=PLiDUvCGH5WEUEV9nc0Ll2pzUFmSFc21uR&index=19&t=451s
}
