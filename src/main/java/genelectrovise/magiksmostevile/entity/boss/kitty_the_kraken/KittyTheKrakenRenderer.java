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

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

/**
 * @author GenElectrovise 1 Jun 2020
 */

public class KittyTheKrakenRenderer extends MobRenderer<KittyTheKrakenEntity, KittyTheKrakenModel<KittyTheKrakenEntity>> {
    public static final float SCALE = 8;
    private static final ResourceLocation THE_KRAKEN_TEXTURES =
            new ResourceLocation(MagiksMostEvile.MODID, "textures/entity/the_kraken/the_kraken.png");

    public KittyTheKrakenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KittyTheKrakenModel<>(), 0.7F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getTextureLocation(KittyTheKrakenEntity entity) {
        return THE_KRAKEN_TEXTURES;
    }

    protected void applyRotations(SquidEntity entityLiving, MatrixStack matrixStackIn,
                                  float ageInTicks, float rotationYaw, float partialTicks) {

        float f = MathHelper.lerp(partialTicks, entityLiving.xBodyRotO, entityLiving.xBodyRot);
        float f1 = MathHelper.lerp(partialTicks, entityLiving.zBodyRotO, entityLiving.zBodyRotO);

        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(f));
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f1));
        matrixStackIn.translate(0.0D, (double) -1.2F, 0.0D);
    }

    @Override
    protected void setupRotations(KittyTheKrakenEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
    }

    @Override
    protected void scale(KittyTheKrakenEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(SCALE, SCALE, SCALE);

        matrixStackIn.translate(0, 0.5, 0);
    }

}
