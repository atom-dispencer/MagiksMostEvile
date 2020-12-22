package com.magiksmostevile.blocks.ores;

import java.util.Random;

import com.magiksmostevile.Main;
import com.magiksmostevile.blocks.item.ItemBlockVarients;
import com.magiksmostevile.handler.EnumHandler;
import com.magiksmostevile.init.EvileBlocks;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.items.EvileItemBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import util.IHasModel;
import util.IMetaName;

public class BlockOres extends Block implements IHasModel, IMetaName {

	public static final PropertyEnum<EnumHandler.EnumType> VARIENT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);
	private String name, dimension;
	private Random rand = new Random();

	public BlockOres(String name, String dimension) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setHardness(3F);
		setHarvestLevel("pickaxe", 1);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIENT, EnumHandler.EnumType.AMETHYST));

		EvileBlocks.EVILEBLOCKS.add(this);
		EvileItems.EVILEITEMS.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));

		this.name = name;
		this.dimension = dimension;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.EnumType) state.getValue(VARIENT)).getMeta();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumHandler.EnumType) state.getValue(VARIENT)).getMeta();

	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIENT, EnumHandler.EnumType.byMetadata(meta));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		if (state.getValue(VARIENT).getMeta() == 0) {
			return new ItemStack(EvileItems.AMETHYST, rand.nextInt(3) + 1);
		}
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumType varient : EnumHandler.EnumType.values()) {
			items.add(new ItemStack(this, 1, varient.getMeta()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIENT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumHandler.EnumType.values().length; i++) {
			Main.proxy.registerVarientRenderer(Item.getItemFromBlock(this), i, "ore_" + this.dimension + "_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
	}
}
