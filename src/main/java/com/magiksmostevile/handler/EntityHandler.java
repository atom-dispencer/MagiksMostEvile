package com.magiksmostevile.handler;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.Main;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.entity.EntityVampireBat;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityHandler {
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
	EvileLog.logText(true, EnumLogLevel.INFO, "Registering entities for mod: " + Main.MODID );
	
	//Vampire Bat
	event.getRegistry().register(EntityEntryBuilder.create().entity(EntityVampireBat.class)
		.id(new ResourceLocation(Main.MODID, "vampire_bat"), 33).name("vampire_bat").tracker(160, 2, false)
		.egg(9830655, 2431278)
		.spawn(EnumCreatureType.MONSTER, 10, 4, 24, ForgeRegistries.BIOMES.getValuesCollection()).build());
    }
}
