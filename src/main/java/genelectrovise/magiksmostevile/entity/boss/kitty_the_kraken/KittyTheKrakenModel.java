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
package genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.Arrays;

public class KittyTheKrakenModel<T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer[] legs = new ModelRenderer[8];
    private final ImmutableList<ModelRenderer> renderers;

    public KittyTheKrakenModel() {
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-6.0F, -8.0F, -6.0F, 12.0F, 16.0F, 12.0F);
        this.body.y += 8.0F;

        for (int j = 0; j < this.legs.length; ++j) {
            this.legs[j] = new ModelRenderer(this, 48, 0);
            double d0 = (double) j * Math.PI * 2.0D / (double) this.legs.length;
            float f = (float) Math.cos(d0) * 5.0F;
            float f1 = (float) Math.sin(d0) * 5.0F;
            this.legs[j].addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F);
            this.legs[j].x = f;
            this.legs[j].z = f1;
            this.legs[j].y = 15.0F;
            d0 = (double) j * Math.PI * -2.0D / (double) this.legs.length + (Math.PI / 2D);
            this.legs[j].yRot = (float) d0;
        }

        Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.add(this.body);
        builder.addAll(Arrays.asList(this.legs));
        this.renderers = builder.build();
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {

        try {
            for (ModelRenderer modelrenderer : this.legs) {
                modelrenderer.xRot = (float) (((Math.sin(ageInTicks / 10)) / 2) + 0.5);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Iterable<ModelRenderer> parts() {
        return this.renderers;
    }
}
