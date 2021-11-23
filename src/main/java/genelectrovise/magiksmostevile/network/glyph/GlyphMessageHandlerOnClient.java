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
/**
 * 
 */
package genelectrovise.magiksmostevile.network.glyph;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class GlyphMessageHandlerOnClient {

  /**
   * Called when a message is received of the appropriate type. CALLED BY THE NETWORK THREAD, NOT THE
   * CLIENT THREAD
   */
  public static void onMessageReceived(final GlyphMessageToClient message, Supplier<NetworkEvent.Context> ctxSupplier) {

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      MagiksMostEvile.LOGGER.warn("Invalid message received on client.");
      return;
    }

    if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.CLIENT) {
      MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (client) ");
      return;
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  // This message is called from the Client thread.
  private static void processMessage(GlyphMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    Glyph glyph = new Glyph(message.getResource());

    ClientWorld world = Minecraft.getInstance().player.clientLevel;
    BlockPos position = message.getPosition();
    double spacing = message.getSpacing();
    GlyphOrientation orientation = message.getOrientation();
    boolean centered = message.isCentered();

    if (centered)
      glyph.drawCentered(world, position, spacing, orientation);
    else
      glyph.draw(world, position, spacing, orientation);
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return GlyphNetworkingManager.GLYPH_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }
}
