/**
 * 
 */
package genelectrovise.magiksmostevile.entity.vampire_bat;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * @author GenElectrovise 1 Jun 2020
 */

public class VampireBatRenderer extends MobRenderer<VampireBatEntity, VampireBatModel> {
  private static final ResourceLocation VAMPIRE_BAT_TEXTURES =
      new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/vampire_bat/vampire_bat.png");

  public VampireBatRenderer(EntityRendererManager renderManagerIn) {
    super(renderManagerIn, new VampireBatModel(), 0.25F);
  }

  /**
   * Returns the location of an entity's texture.
   */
  @Override
  public ResourceLocation getEntityTexture(VampireBatEntity entity) {
    return VAMPIRE_BAT_TEXTURES;
  }

  @Override
  protected void preRenderCallback(VampireBatEntity entitylivingbaseIn, MatrixStack matrixStackIn,
      float partialTickTime) {
    matrixStackIn.scale(0.35F, 0.35F, 0.35F);
    super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
  }

  @Override
  protected void applyRotations(VampireBatEntity vampireBat, MatrixStack matrixStackIn,
      float ageInTicks, float rotationYaw, float partialTicks) {
    if (vampireBat.getIsBatHanging()) {
      matrixStackIn.translate(0.0D, (double) -0.45F, 0.0D);
    } else {
      matrixStackIn.translate(0.0D, (double) (MathHelper.cos(ageInTicks * 0.3F) * 0.1F), 0.0D);
    }

    super.applyRotations(vampireBat, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
  }
}
