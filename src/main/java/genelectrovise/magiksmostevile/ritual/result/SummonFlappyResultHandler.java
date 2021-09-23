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
package genelectrovise.magiksmostevile.ritual.result;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatEntity;
import genelectrovise.magiksmostevile.network.glyph.GlyphMessageToClient;
import genelectrovise.magiksmostevile.network.glyph.GlyphNetworkingManager;
import genelectrovise.magiksmostevile.registry.orbital.registries.EntityOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.ResultHandler;
import genelectrovise.magiksmostevile.ritual.SummonFlappyRitual;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
import genelectrovise.magiksmostevile.tileentity.altar.AltarTileEntity;
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

    VampireBatEntity bat = EntityOrbitalRegistry.VAMPIRE_BAT.get().create(altar.getLevel());

    BlockPos altarPosUp = altar.getBlockPos().above(3);
    bat.setPos(altarPosUp.getX(), altarPosUp.getY(), altarPosUp.getZ());

    altar.getLevel().addFreshEntity(bat);

    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID,
                "textures/items/general/vampire_bat_tooth.png"),
            GlyphOrientation.VERTICAL, altar.getBlockPos().above(5), true, 0.5));
  }

  @Override
  public void handleCasting() {

  }

  @Override
  public void handleFailure() {
    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID, "textures/ritual/fail.png"),
            GlyphOrientation.VERTICAL, altar.getBlockPos().above(5), true, 0.5));
  }

  @Override
  public void handleCataclysm() {
    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID, "textures/ritual/fail.png"),
            GlyphOrientation.VERTICAL, altar.getBlockPos().above(5), true, 0.5));
  }

}
