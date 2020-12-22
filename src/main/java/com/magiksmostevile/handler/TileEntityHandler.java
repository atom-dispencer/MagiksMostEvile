package com.magiksmostevile.handler;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.Main;
import com.magiksmostevile.tileentity.TileEntityAltar;
import com.magiksmostevile.tileentity.TileEntityAmethystCrystal;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {

		EvileLog.logText(true, EnumLogLevel.INFO, "Registering tile entities for mod :" + Main.MODID);

		GameRegistry.registerTileEntity(TileEntityAmethystCrystal.class, new ResourceLocation(Main.MODID, "tile_entity_amethyst_crystal"));

		GameRegistry.registerTileEntity(TileEntityAltar.class, new ResourceLocation(Main.MODID, "tile_entity_altar"));
	}
}
