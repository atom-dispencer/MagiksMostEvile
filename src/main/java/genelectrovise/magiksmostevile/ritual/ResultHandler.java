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
package genelectrovise.magiksmostevile.ritual;

import genelectrovise.magiksmostevile.ritual.Ritual.RitualResult;
import genelectrovise.magiksmostevile.tileentity.altar.AltarTileEntity;

public abstract class ResultHandler<T extends Ritual> {

  private AltarTileEntity altar;
  private T ritual;

  public ResultHandler(AltarTileEntity altar, T ritual) {
    this.altar = altar;
    this.ritual = ritual;
  }

  public final AltarTileEntity getAltar() { return altar; }

  public final T getRitual() { return ritual; }

  public void handle(RitualResult type) {
    switch (type) {
      case SUCCESS:
        ritual.setDone(false);
        handleSuccess();
        break;
      case CASTING:
        ritual.setDone(false);
        handleCasting();
        break;
      case FAILURE:
        ritual.setDone(true);
        handleFailure();
        break;
      case CATACLYSM:
        ritual.setDone(true);
        handleCataclysm();
        break;
      default:
        break;
    }
  }

  /**
   * Handles the {@link RitualResult#SUCCESS} result of the {@link Ritual#tick()} method, i.e. What
   * should happen when the {@link Ritual} ends with success?
   */
  public abstract void handleSuccess();

  /**
   * Handles the {@link RitualResult#CASTING} result of the {@link Ritual#tick()} method. This is only
   * really here as a cursory method, as during casting, logic should be handled in the
   * {@link Ritual#tick()} method.
   */
  public abstract void handleCasting();

  /**
   * Handles the {@link RitualResult#FAILURE} result of the {@link Ritual#tick()} method. This is a
   * <b>graceful</b> failure. You're looking for the explosions? Go to {@link #handleCataclysm()}...
   */
  public abstract void handleFailure();

  /**
   * Handles the {@link RitualResult#CATACLYSM} result of the {@link Ritual#tick()} method. This is
   * what happens if the {@link Ritual} is interrupted, e.g. by running out of amethyst flux.
   */
  public abstract void handleCataclysm();
}
