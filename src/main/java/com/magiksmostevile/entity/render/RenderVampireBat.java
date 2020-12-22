package com.magiksmostevile.entity.render;

import com.magiksmostevile.Main;
import com.magiksmostevile.entity.EntityVampireBat;
import com.magiksmostevile.entity.model.ModelVampireBat;

import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@SideOnly(Side.CLIENT)
public class RenderVampireBat extends RenderLiving<EntityVampireBat>
{
    private static final ResourceLocation VAMPIRE_BAT_TEXTURES = new ResourceLocation(Main.MODID+":textures/entity/vampire_bat/vampire_bat.png");  //Main.MODID + ":textures/entity/vampire_bat"
    private static final boolean debugMode = false;
    private static final boolean doTextureNullDebug = true;
    
    public RenderVampireBat(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelVampireBat(), 0.25F);
    }
    
    /*
    protected void setEntityTexture()
    {
    	VAMPIRE_BAT_TEXTURES = new ResourceLocation(Main.MODID+":textures/entity/serpents/python.png");
    }
    */


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVampireBat entity)
    {
    	textureNullDebug();
        return VAMPIRE_BAT_TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityVampireBat entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    protected void applyRotations(EntityVampireBat entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        if (entityLiving.getIsBatHanging())
        {
            GlStateManager.translate(0.0F, -0.1F, 0.0F);
        }
        else
        {
            GlStateManager.translate(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
        }

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
    
    @SuppressWarnings("unused")
	private void textureNullDebug() {
    	if(debugMode && doTextureNullDebug) {
    		if(VAMPIRE_BAT_TEXTURES.getResourcePath().equals(null)) {
        		System.out.println("Oops! VAMPIRE_BAT_TEXTURES is null! No texture for vampire_bat found? Or is something weird happening?");
        	}
        	else {
            	System.out.println("VAMPIRE_BAT_TEXTURES location: " + VAMPIRE_BAT_TEXTURES.getResourcePath());
        	}
    		if(VAMPIRE_BAT_TEXTURES.equals(null)) {
    			System.out.println("VAMPIRE_BAT_TEXTURES null!");
    		}
    	}
    }
}