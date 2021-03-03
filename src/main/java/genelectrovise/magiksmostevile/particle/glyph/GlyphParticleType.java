/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.particle.glyph;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

/**
 * @author GenElectrovise 15 Jun 2020
 */
public class GlyphParticleType extends ParticleType<GlyphParticleData> {
  private static boolean ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER = false;

  // TODO does this error?
  private final Codec<GlyphParticleData> codec = Codec.unit(() -> null);

  public GlyphParticleType() {
    super(ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER, GlyphParticleData.DESERIALIZER);
  }

  public GlyphParticleType getThis() {
    return this;
  }

  /**
   * Get the {@link Codec} (Encoder/Decoder) of this particle.
   */
  @Override
  public Codec<GlyphParticleData> func_230522_e_() {
    return codec;
  }

}
