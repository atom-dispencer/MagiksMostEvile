package genelectrovise.magiksmostevile.common.ritual.result;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatEntity;
import genelectrovise.magiksmostevile.common.network.glyph.GlyphMessageToClient;
import genelectrovise.magiksmostevile.common.network.glyph.GlyphNetworkingManager;
import genelectrovise.magiksmostevile.common.ritual.ResultHandler;
import genelectrovise.magiksmostevile.common.ritual.SummonFlappyRitual;
import genelectrovise.magiksmostevile.common.ritual.glyph.Glyph.GlyphOrientation;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.PacketDistributor;

public class SummonFlappyResultHandler extends ResultHandler<SummonFlappyRitual> {

  private AltarTileEntity altar;
  private SummonFlappyRitual ritual;

  /**
   * @param altar
   * @param ritual
   */
  public SummonFlappyResultHandler(AltarTileEntity altar, SummonFlappyRitual ritual) {
    super(altar, ritual);
    this.altar = altar;
    this.ritual = ritual;
  }

  @Override
  public void handleSuccess() {

    ritual.setDone(true);

    VampireBatEntity bat = EvileDeferredRegistry.VAMPIRE_BAT.get().create(altar.getWorld());

    BlockPos altarPosUp = altar.getPos().up(3);
    bat.setPosition(altarPosUp.getX(), altarPosUp.getY(), altarPosUp.getZ());

    altar.getWorld().addEntity(bat);

    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID,
                "textures/items/general/vampire_bat_tooth.png"),
            GlyphOrientation.VERTICAL, altar.getPos().up(5), true, 0.5));
  }

  @Override
  public void handleCasting() {

  }

  @Override
  public void handleFailure() {
    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID, "textures/ritual/fail.png"),
            GlyphOrientation.VERTICAL, altar.getPos().up(5), true, 0.5));
  }

  @Override
  public void handleCataclysm() {
    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID, "textures/ritual/fail.png"),
            GlyphOrientation.VERTICAL, altar.getPos().up(5), true, 0.5));
  }

}
