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
package genelectrovise.magiksmostevile.world.gen.structure.shrine;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * {@link RuinedPortalPiece.Serializer}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineStructureAestheticsSerializer {

  // Codec
  public static final Codec<OvergroundShrineStructureAestheticsSerializer> CODEC =

      // Create
      RecordCodecBuilder.create((serializerInstance) -> {

        // Return group
        return serializerInstance.group(

            // isCold
            Codec.BOOL.fieldOf("cold").forGetter((serializer) -> {
              return serializer.isCold;
            }),
            // mossiness
            Codec.FLOAT.fieldOf("mossiness").forGetter((serializer) -> {
              return serializer.mossiness;
            }),
            // isOvergrown
            Codec.BOOL.fieldOf("overgrown").forGetter((serializer) -> {
              return serializer.isOvergrown;
            }),
            // hasVines
            Codec.BOOL.fieldOf("vines").forGetter((serializer) -> {
              return serializer.hasVines;
            }),
            // shouldReplaceWithBlackstone (lava?)
            Codec.BOOL.fieldOf("replace_with_blackstone").forGetter((serializer) -> {
              return serializer.replaceWithBlackstone;
            })

        // Apply group
        ).apply(serializerInstance, OvergroundShrineStructureAestheticsSerializer::new);
      });

  public boolean isCold = false;
  public float mossiness = 0.2F;
  public boolean isOvergrown = false;
  public boolean hasVines = false;
  public boolean replaceWithBlackstone = false;

  public OvergroundShrineStructureAestheticsSerializer() {}

  public <T> OvergroundShrineStructureAestheticsSerializer(boolean isCold, float mossiness,
      boolean isOvergrown, boolean hasVines, boolean replaceWithBlackstone) {
    this.isCold = isCold;
    this.mossiness = mossiness;
    this.isOvergrown = isOvergrown;
    this.hasVines = hasVines;
    this.replaceWithBlackstone = replaceWithBlackstone;
  }
}
