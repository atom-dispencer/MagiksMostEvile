package com.magiksmostevile.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Based on mojang's ModelBat   
 * Created using Tabula 7.0.1
 */
public class ModelVampireBatHanging extends ModelBase {
    public ModelRenderer vampireBat_head;
    public ModelRenderer vampireBat_body;
    public ModelRenderer vampireBat_feet;
    public ModelRenderer vampireBat_ear_right;
    public ModelRenderer vampireBat_ear_left;
    public ModelRenderer vampireBat_wing_right;
    public ModelRenderer vampireBat_wing_left;
    public ModelRenderer vampireBat_forewing_right;
    public ModelRenderer vampireBat_forewing_left;

    public ModelVampireBatHanging() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.vampireBat_ear_left = new ModelRenderer(this, 24, 0);
        this.vampireBat_ear_left.mirror = true;
        this.vampireBat_ear_left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vampireBat_ear_left.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1, 0.0F);
        this.vampireBat_forewing_left = new ModelRenderer(this, 24, 16);
        this.vampireBat_forewing_left.mirror = true;
        this.vampireBat_forewing_left.setRotationPoint(12.0F, 1.0F, 1.5F);
        this.vampireBat_forewing_left.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1, 0.0F);
        this.setRotateAngle(vampireBat_forewing_left, 0.0F, 1.7278759594743864F, 0.0F);
        this.vampireBat_forewing_right = new ModelRenderer(this, 24, 16);
        this.vampireBat_forewing_right.setRotationPoint(-12.0F, 1.0F, 1.5F);
        this.vampireBat_forewing_right.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1, 0.0F);
        this.setRotateAngle(vampireBat_forewing_right, 0.0F, -1.7278759594743864F, 0.0F);
        this.vampireBat_body = new ModelRenderer(this, 0, 16);
        this.vampireBat_body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vampireBat_body.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6, 0.0F);
        this.setRotateAngle(vampireBat_body, 3.141592653589793F, 0.0F, 0.0F);
        this.vampireBat_wing_right = new ModelRenderer(this, 42, 0);
        this.vampireBat_wing_right.setRotationPoint(-3.0F, 0.0F, 3.0F);
        this.vampireBat_wing_right.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1, 0.0F);
        this.setRotateAngle(vampireBat_wing_right, -0.15707963267948966F, -1.2566370614359172F, 0.0F);
        this.vampireBat_head = new ModelRenderer(this, 0, 0);
        this.vampireBat_head.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.vampireBat_head.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(vampireBat_head, 0.0F, 3.141592653589793F, 3.141592653589793F);
        this.vampireBat_wing_left = new ModelRenderer(this, 42, 0);
        this.vampireBat_wing_left.mirror = true;
        this.vampireBat_wing_left.setRotationPoint(3.0F, 0.0F, 3.0F);
        this.vampireBat_wing_left.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1, 0.0F);
        this.setRotateAngle(vampireBat_wing_left, -0.15707963267948966F, 1.2566370614359172F, 0.0F);
        this.vampireBat_ear_right = new ModelRenderer(this, 24, 0);
        this.vampireBat_ear_right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vampireBat_ear_right.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1, 0.0F);
        this.vampireBat_feet = new ModelRenderer(this, 0, 34);
        this.vampireBat_feet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vampireBat_feet.addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1, 0.0F);
        this.setRotateAngle(vampireBat_feet, 3.141592653589793F, 0.0F, 0.0F);
        this.vampireBat_head.addChild(this.vampireBat_ear_left);
        this.vampireBat_wing_left.addChild(this.vampireBat_forewing_left);
        this.vampireBat_wing_right.addChild(this.vampireBat_forewing_right);
        this.vampireBat_body.addChild(this.vampireBat_wing_right);
        this.vampireBat_body.addChild(this.vampireBat_wing_left);
        this.vampireBat_head.addChild(this.vampireBat_ear_right);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.vampireBat_body.render(f5);
        this.vampireBat_head.render(f5);
        this.vampireBat_feet.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
