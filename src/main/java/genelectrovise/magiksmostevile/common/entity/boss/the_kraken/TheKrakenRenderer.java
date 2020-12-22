package genelectrovise.magiksmostevile.common.entity.boss.the_kraken;

import com.mojang.blaze3d.matrix.MatrixStack;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * @author GenElectrovise 1 Jun 2020
 */

public class TheKrakenRenderer extends MobRenderer<TheKrakenEntity, TheKrakenModel> {
	private static final ResourceLocation THE_KRAKEN_TEXTURES = new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/the_kraken/the_kraken.png");

	public TheKrakenRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new TheKrakenModel(), 0.25F);
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	@Override
	public ResourceLocation getEntityTexture(TheKrakenEntity entity) {
		return THE_KRAKEN_TEXTURES;
	}

	@Override
	protected void preRenderCallback(TheKrakenEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		//matrixStackIn.scale(0.35F, 0.35F, 0.35F);
		super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
	}

	@Override
	protected void applyRotations(TheKrakenEntity vampireBat, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		matrixStackIn.translate(0.0D, 8, 0.0D);
		matrixStackIn.rotate(new Quaternion(90, 0, 0, true));
		matrixStackIn.translate(0.0D, 0.0D, (double) (MathHelper.cos(ageInTicks * 0.1F) * 0.5F));

		super.applyRotations(vampireBat, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
	}
}
