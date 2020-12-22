package com.magiksmostevile;

import com.magiksmostevile.entity.EntityVampireBat;
import com.magiksmostevile.entity.render.RenderVampireBat;
import com.magiksmostevile.handler.RegistryHandler;
import com.magiksmostevile.init.EntityInit;
import com.magiksmostevile.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)

public class Main {
	public static final String MODID = "magiksmostevile";
	public static final String VERSION = "1.0";
	public static final String NAME = "MagiksMostEvile";
	public static final String ACCEPTED_VERSIONS = "{1.12.2}";
	public static final String CLIENT_PROXY_CLASS = "com.magiksmostevile.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.magiksmostevile.proxy.CommonProxy";

	public static final int ENTITY_VAMPIRE_BAT = 6750;

	public static final int GUI_ALTAR = 1;

	public static Item amethyst;
	public static Block amethystOre;

	@Mod.Instance(Main.MODID)
	public static Main instance;

	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			System.out.println("preInit: Client");
			RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, RenderVampireBat::new);
			RegistryHandler.otherRegistries();
		}

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
