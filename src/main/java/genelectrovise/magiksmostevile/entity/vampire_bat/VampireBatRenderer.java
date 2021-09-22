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
/**
 * 
 */
package genelectrovise.magiksmostevile.entity.vampire_bat;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * @see BatRenderer
 * @author GenElectrovise 1 Jun 2020
 * 
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
  public ResourceLocation getTextureLocation(VampireBatEntity entity) {
    return VAMPIRE_BAT_TEXTURES;
  }

  @Override
  protected void scale(VampireBatEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
    matrixStackIn.scale(0.35F, 0.35F, 0.35F);
    super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
  }

  @Override
  protected void setupRotations(VampireBatEntity vampireBat, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
    if (vampireBat.getIsBatHanging()) {
      matrixStackIn.translate(0.0D, (double) -0.45F, 0.0D);
    } else {
      matrixStackIn.translate(0.0D, (double) (MathHelper.cos(ageInTicks * 0.3F) * 0.1F), 0.0D);
    }

    super.setupRotations(vampireBat, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
  }

}
