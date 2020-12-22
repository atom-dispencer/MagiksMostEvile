package com.magiksmostevile.equipment.DoomStaff;

import org.lwjgl.opengl.GL11;

import com.magiksmostevile.Main;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDoom extends Render
{
    private static final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/entity/emp.png");
    private ModelBase model;
 
    public RenderDoom()
    {
        model = new ModelDoom();
    }
 
    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
 
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick)
    {
        GL11.glPushMatrix();
        bindTexture(texture);
        GL11.glTranslated(x, y - 1.25D, z);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}