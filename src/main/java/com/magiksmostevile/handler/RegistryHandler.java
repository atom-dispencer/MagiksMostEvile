package com.magiksmostevile.handler;

import com.magiksmostevile.Main;
import com.magiksmostevile.init.EvileBlocks;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.tileentity.TileEntityAltar;
import com.magiksmostevile.world.gen.WorldGenCustomOres;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
//   https://www.youtube.com/watch?v=rQLhheYcnrY&list=PLDhiRTZ_vnoX4bx_BJccGV7MjpXUfVJSn&index=3
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import util.IHasModel;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(EvileItems.EVILEITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(EvileBlocks.EVILEBLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}

	/*
	@SubscribeEvent
	public static void registerGuiRelations(AttachCapabilitiesEvent<TileEntity> event) {
		event.addCapability(new ResourceLocation(Main.MODID + ":capabilites_altar"), new TileEntityAltar());
	}
	*/

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : EvileItems.EVILEITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}

		for (Block block : EvileBlocks.EVILEBLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
	}

	@SubscribeEvent
	public static void entityRegistration(RegistryEvent.Register<EntityEntry> event) {
		EntityHandler.registerEntities(event);
	}

	public static void preInitRegistries() {
		RenderHandler.registerEntityRenders();
	}

	public static void initRegistries() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}

	public static void otherRegistries() {
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
	}
}
