/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.entity.model.GiantModel;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;

/**
 * {@link SquidRenderer} {@link ArrowRenderer} {@link SquidModel} {@link GiantModel}
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
    return new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/squid_missile/squid_missile.png");
  }

  @Override
  protected void applyRotations(SquidMissileEntity missile, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {

    // + pitch squid backwards, - pitch squid forwards
    // + yaw ??
    // + rotate squid eyes to its left hand-side (do squid have hand-sides??)

    matrixStackIn.translate(0, 0, 0);
    matrixStackIn.rotate(new Quaternion(0, 0, 0, true));
  }

  /**
   * Defines what float the third param in setRotationAngles of ModelBase is
   */
  protected float handleRotationFloat(SquidEntity livingBase, float partialTicks) {
    return MathHelper.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
  }

}
