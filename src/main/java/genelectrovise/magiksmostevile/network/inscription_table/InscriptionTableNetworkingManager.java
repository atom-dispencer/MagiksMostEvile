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
package genelectrovise.magiksmostevile.network.inscription_table;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.cast_button.AltarCastButtonPressedMessageHandlerOnClient;
import genelectrovise.magiksmostevile.network.altar.cast_button.AltarCastButtonPressedMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.inscription_table.button_pressed.ButtonPressedMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.inscription_table.button_pressed.ButtonPressedMessageToServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author GenElectrovise 23 May 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InscriptionTableNetworkingManager {

  // Cast button
  public static SimpleChannel CButtonPressed;
  public static final ResourceLocation RLButtonPressed = new ResourceLocation(MagiksMostEvile.MODID, "inscription_table_button_pressed");
  public static final String BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int BUTTON_PRESSED_TO_SERVER = 11;

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by InscriptionTableNetworkingManager!");

    CButtonPressed = NetworkRegistry.newSimpleChannel(RLButtonPressed, () -> BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION, AltarCastButtonPressedMessageHandlerOnClient::isProtocolAccepted,
        AltarCastButtonPressedMessageHandlerOnServer::isProtocolAccepted);
    CButtonPressed.registerMessage(BUTTON_PRESSED_TO_SERVER, ButtonPressedMessageToServer.class, ButtonPressedMessageToServer::encode, ButtonPressedMessageToServer::decode,
        ButtonPressedMessageHandlerOnServer::onMessageReceived);
  }
}
