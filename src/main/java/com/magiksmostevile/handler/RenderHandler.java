package com.magiksmostevile.handler;

import com.magiksmostevile.entity.EntityVampireBat;
import com.magiksmostevile.entity.render.RenderVampireBat;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class RenderHandler {
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, new IRenderFactory<EntityVampireBat>() {

			@Override
			public Render<? super EntityVampireBat> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderVampireBat(manager);
			}
			
		});
	}
}
