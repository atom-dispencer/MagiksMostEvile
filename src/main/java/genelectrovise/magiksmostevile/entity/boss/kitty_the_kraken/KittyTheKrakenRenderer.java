package genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

/**
 * @author GenElectrovise 1 Jun 2020
 */

public class KittyTheKrakenRenderer extends MobRenderer<KittyTheKrakenEntity, KittyTheKrakenModel<KittyTheKrakenEntity>> {
  private static final ResourceLocation THE_KRAKEN_TEXTURES =
      new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/the_kraken/the_kraken.png");

  public static final float SCALE = 8;

  public KittyTheKrakenRenderer(EntityRendererManager renderManagerIn) {
    super(renderManagerIn, new KittyTheKrakenModel<>(), 0.7F);
  }

  /**
   * Returns the location of an entity's texture.
   */
  public ResourceLocation getEntityTexture(KittyTheKrakenEntity entity) {
    return THE_KRAKEN_TEXTURES;
  }

  protected void applyRotations(SquidEntity entityLiving, MatrixStack matrixStackIn,
      float ageInTicks, float rotationYaw, float partialTicks) {

    float f = MathHelper.lerp(partialTicks, entityLiving.prevSquidPitch, entityLiving.squidPitch);
    float f1 = MathHelper.lerp(partialTicks, entityLiving.prevSquidYaw, entityLiving.squidYaw);

    matrixStackIn.translate(0.0D, 0.5D, 0.0D);
    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
    matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
    matrixStackIn.translate(0.0D, (double) -1.2F, 0.0D);
  }

  @Override
  protected void preRenderCallback(KittyTheKrakenEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
    matrixStackIn.scale(SCALE, SCALE, SCALE);
    
    matrixStackIn.translate(0, 0.5, 0);
  }
}
