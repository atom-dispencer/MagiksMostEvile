/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.vampire_bat;

import com.mojang.blaze3d.matrix.MatrixStack;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author GenElectrovise 1 Jun 2020
 */
public class VampireBatRenderer extends MobRenderer<VampireBatEntity, VampireBatModel> {
	private static final ResourceLocation BAT_TEXTURES = new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/vampire_bat/vampire_bat.png");

	public VampireBatRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new VampireBatModel(), 0.25F);
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getEntityTexture(VampireBatEntity entity) {
		return BAT_TEXTURES;
	}

	protected void preRenderCallback(BatEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(0.35F, 0.35F, 0.35F);
	}

	protected void applyRotations(VampireBatEntity vampireBat, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		if (vampireBat.getIsBatHanging()) {
			matrixStackIn.translate(0.0D, (double) -0.1F, 0.0D);
		} else {
			matrixStackIn.translate(0.0D, (double) (MathHelper.cos(ageInTicks * 0.3F) * 0.1F), 0.0D);
		}

		super.applyRotations(vampireBat, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
	}
}
