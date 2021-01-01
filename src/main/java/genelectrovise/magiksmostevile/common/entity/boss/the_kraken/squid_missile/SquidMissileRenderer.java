package genelectrovise.magiksmostevile.common.entity.boss.the_kraken.squid_missile;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.entity.boss.the_kraken.KittyTheKrakenModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

/**
 * {@link SquidRenderer} {@link ArrowRenderer} {@link SquidModel}
 * 
 * @author GenElectrovise
 *
 * @param <T>
 */
public class SquidMissileRenderer extends MobRenderer<SquidMissileEntity, SquidMissileModel<SquidMissileEntity>> {

  public SquidMissileRenderer(EntityRendererManager renderManagerIn) {
    super(renderManagerIn, new SquidMissileModel<>(), 1);
  }

  @Override
  public ResourceLocation getEntityTexture(SquidMissileEntity entity) {
    return new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/squid_missile/squid_missile");
  }

  @Override
  public void render(SquidMissileEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
    float f = MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch);
    float f1 = MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw);
    matrixStackIn.translate(0.0D, 0.5D, 0.0D);
    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityIn.rotationYaw));
    matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
    matrixStackIn.translate(0.0D, (double) -1.2F, 0.0D);
  }

}
