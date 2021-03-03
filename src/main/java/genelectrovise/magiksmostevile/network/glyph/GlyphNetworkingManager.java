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

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author GenElectrovise 19 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GlyphNetworkingManager {

  // Glyph
  public static SimpleChannel CGlyph;
  public static final ResourceLocation RLGlyph =
      new ResourceLocation(MagiksMostEvile.MODID, "glyph");
  public static final String GLYPH_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int GLYPH_TO_SERVER = 11;
  public static final int GLYPH_TO_CLIENT = 12;

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by GlyphNetworkManager!");
    CGlyph = NetworkRegistry.newSimpleChannel(RLGlyph, () -> GLYPH_MESSAGE_PROTOCOL_VERSION, GlyphMessageHandlerOnClient::isProtocolAccepted, GlyphMessageHandlerOnServer::isProtocolAccepted);
    CGlyph.registerMessage(GLYPH_TO_SERVER, GlyphMessageToServer.class, GlyphMessageToServer::encode, GlyphMessageToServer::decode, GlyphMessageHandlerOnServer::onMessageReceived);
    CGlyph.registerMessage(GLYPH_TO_CLIENT, GlyphMessageToClient.class, GlyphMessageToClient::encode, GlyphMessageToClient::decode, GlyphMessageHandlerOnClient::onMessageReceived);
  }

}
