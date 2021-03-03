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
package genelectrovise.magiksmostevile.network.glyph;

import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

/**
 * @author GenElectrovise 24 May 2020
 */
public class GlyphMessageToClient {

  private static boolean messageIsValid;

  private ResourceLocation resource;
  private GlyphOrientation orientation;
  private BlockPos position;
  private boolean centered;
  private double spacing;

  public GlyphMessageToClient(ResourceLocation resource, GlyphOrientation orientation,
      BlockPos position, boolean centered, double spacing) {
    this.resource = resource;
    this.orientation = orientation;
    this.position = position;
    this.centered = centered;
    this.spacing = spacing;
  }

  public boolean isMessageValid() {
    return messageIsValid;
  }

  /**
   * Called by the network code. Used to write the contents of your message member variables into
   * the ByteBuf, ready for transmission over the network.
   *
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    buf.writeResourceLocation(resource);
    buf.writeInt(GlyphOrientation.toInt(orientation));
    buf.writeBlockPos(position);
    buf.writeBoolean(centered);
    buf.writeDouble(spacing);
  }

  /**
   * Called by the network code once it has received the message bytes over the network. Used to
   * read the ByteBuf contents into your member variables
   *
   * @param buf
   */
  public static GlyphMessageToClient decode(PacketBuffer buf) {

    ResourceLocation resource = buf.readResourceLocation();
    GlyphOrientation orientaion = GlyphOrientation.fromInt(buf.readInt());
    BlockPos position = buf.readBlockPos();
    boolean centered = buf.readBoolean();
    double spacing = buf.readDouble();

    return new GlyphMessageToClient(resource, orientaion, position, centered, spacing);
  }

  public boolean isValid() {
    return resource != null && orientation != null && position != null && spacing != 0;
  }

  /**
   * @return the resource
   */
  public ResourceLocation getResource() {
    return resource;
  }

  /**
   * @return the orientation
   */
  public GlyphOrientation getOrientation() {
    return orientation;
  }

  /**
   * @return the position
   */
  public BlockPos getPosition() {
    return position;
  }

  /**
   * @return the spacing
   */
  public double getSpacing() {
    return spacing;
  }

  /**
   * @return the centered
   */
  public boolean isCentered() {
    return centered;
  }
}
