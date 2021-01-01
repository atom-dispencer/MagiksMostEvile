package genelectrovise.magiksmostevile.common.entity.boss.the_kraken.squid_missile;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SquidMissileModel<T extends Entity> extends SegmentedModel<T> {

  @Override
  public Iterable<ModelRenderer> getParts() {
    return null;
  }

  @Override
  public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    
  }

}
