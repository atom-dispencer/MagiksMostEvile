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

import java.util.Arrays;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * {@link SquidModel}
 * 
 * @author GenElectrovise
 *
 * @param <T>
 */
public class SquidMissileModel<T extends Entity> extends SegmentedModel<T> {

  private ModelRenderer body;
  private ModelRenderer[] legs = new ModelRenderer[8];
  private ImmutableList<ModelRenderer> renderers;

  public SquidMissileModel() {
    reload();
  }

  private void reload() {
    float yOffset = -0.5f;

    this.body = new ModelRenderer(this, 0, 0);
    this.body.addBox(-6.0F, -8.0F + yOffset, -6.0F, 12.0F, 16.0F, 12.0F);

    this.body.rotationPointY += 8.0F;

    // Legs
    for (int j = 0; j < this.legs.length; ++j) {

      // Create leg
      ModelRenderer legRenderer = new ModelRenderer(this, 48, 0);
      legRenderer.addBox(-1.0F, 0.0F + yOffset, -1.0F, 2.0F, 18.0F, 2.0F);

      double d0 = (double) j * Math.PI * 2.0D / (double) this.legs.length;
      float f = (float) Math.cos(d0) * 5.0F;
      float f1 = (float) Math.sin(d0) * 5.0F;

      // Rotation points
      legRenderer.rotationPointX = f;
      legRenderer.rotationPointZ = f1;
      legRenderer.rotationPointY = 15.0F;

      // Angles
      d0 = (double) j * Math.PI * -2.0D / (double) this.legs.length + (Math.PI / 2D);
      legRenderer.rotateAngleY = (float) d0;

      body.addChild(legRenderer);
      this.legs[j] = legRenderer;
    }

    Builder<ModelRenderer> builder = ImmutableList.builder();
    builder.add(this.body);
    builder.addAll(Arrays.asList(this.legs));
    this.renderers = builder.build();
  }

  /**
   * Sets this entity's model rotation angles
   */
  public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    for (ModelRenderer modelrenderer : this.legs) {
      modelrenderer.rotateAngleX = (float) (((Math.sin(ageInTicks / 5)) / 2) + 0.5);
    }

  }

  public Iterable<ModelRenderer> getParts() {
    reload();
    return renderers;
  }

}
