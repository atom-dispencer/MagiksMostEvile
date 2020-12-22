package com.magiksmostevile.init;

import com.magiksmostevile.Main;
import com.magiksmostevile.entity.EntityVampireBat;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	public static void registerEntities() {
		// registerEntity("vampireBat", EntityVampireBat.class, Main.ENTITY_VAMPIRE_BAT, 96, 9830655, 2431278);

	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1,
			int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID + ":" + name), entity, name, id, Main.instance,
				range, 1, true, color1, color2);// 0x9600FF, 0x#25192E

	}

}
