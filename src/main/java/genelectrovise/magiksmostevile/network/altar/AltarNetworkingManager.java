/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.network.altar;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.arrow_toggles.AltarToggleButtonMessageHandlerOnClient;
import genelectrovise.magiksmostevile.network.altar.arrow_toggles.AltarToggleButtonMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.altar.arrow_toggles.AltarToggleButtonMessageToClient;
import genelectrovise.magiksmostevile.network.altar.arrow_toggles.AltarToggleButtonMessageToServer;
import genelectrovise.magiksmostevile.network.altar.cast_button.AltarCastButtonPressedMessageHandlerOnClient;
import genelectrovise.magiksmostevile.network.altar.cast_button.AltarCastButtonPressedMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.altar.cast_button.AltarCastButtonPressedMessageToClient;
import genelectrovise.magiksmostevile.network.altar.cast_button.AltarCastButtonPressedMessageToServer;
import genelectrovise.magiksmostevile.network.altar.energy_update.AltarIchorUpdateMessageHandlerOnClient;
import genelectrovise.magiksmostevile.network.altar.energy_update.AltarIchorUpdateMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.altar.energy_update.AltarIchorUpdateMessageToClient;
import genelectrovise.magiksmostevile.network.altar.energy_update.AltarIchorUpdateMessageToServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author GenElectrovise 23 May 2020
 */
@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AltarNetworkingManager {

    public static final ResourceLocation RLEnergyUpdate = new ResourceLocation(MagiksMostEvile.MODID, "altar_energy_update");
    public static final String ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION = "1.0";
    public static final int ALTAR_ENERGY_UPDATE_TO_SERVER = 11;
    public static final int ALTAR_ENERGY_UPDATE_TO_CLIENT = 12;
    public static final ResourceLocation RLCastButtonPressed = new ResourceLocation(MagiksMostEvile.MODID, "altar_cast_button_pressed");
    public static final String CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION = "1.0";
    public static final int ALTAR_CAST_BUTTON_PRESSED_TO_SERVER = 21;
    public static final int ALTAR_CAST_BUTTON_PRESSED_TO_CLIENT = 22;
    public static final ResourceLocation RLToggleButton = new ResourceLocation(MagiksMostEvile.MODID, "altar_toggle_button");
    public static final String TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION = "1.0";
    public static final int ALTAR_TOGGLE_BUTTON_TO_SERVER = 31;
    public static final int ALTAR_TOGGLE_BUTTON_TO_CLIENT = 32;
    // Energy update
    public static SimpleChannel CAltarEnergyUpdate;
    // Cast button
    public static SimpleChannel CAltarCastButton;
    // Arrow toggles
    public static SimpleChannel CAltarToggleButton;

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by AltarNetworkingManager!");
        CAltarEnergyUpdate = NetworkRegistry.newSimpleChannel(RLEnergyUpdate,
                () -> ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION,
                AltarIchorUpdateMessageHandlerOnClient::isProtocolAccepted,
                AltarIchorUpdateMessageHandlerOnServer::isProtocolAccepted);
        CAltarEnergyUpdate.registerMessage(ALTAR_ENERGY_UPDATE_TO_SERVER,
                AltarIchorUpdateMessageToServer.class, AltarIchorUpdateMessageToServer::encode,
                AltarIchorUpdateMessageToServer::decode,
                AltarIchorUpdateMessageHandlerOnServer::onMessageReceived);
        CAltarEnergyUpdate.registerMessage(ALTAR_ENERGY_UPDATE_TO_CLIENT,
                AltarIchorUpdateMessageToClient.class, AltarIchorUpdateMessageToClient::encode,
                AltarIchorUpdateMessageToClient::decode,
                AltarIchorUpdateMessageHandlerOnClient::onMessageReceived);

        CAltarCastButton = NetworkRegistry.newSimpleChannel(RLCastButtonPressed,
                () -> CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION,
                AltarCastButtonPressedMessageHandlerOnClient::isProtocolAccepted,
                AltarCastButtonPressedMessageHandlerOnServer::isProtocolAccepted);
        CAltarCastButton.registerMessage(ALTAR_CAST_BUTTON_PRESSED_TO_SERVER,
                AltarCastButtonPressedMessageToServer.class, AltarCastButtonPressedMessageToServer::encode,
                AltarCastButtonPressedMessageToServer::decode,
                AltarCastButtonPressedMessageHandlerOnServer::onMessageReceived);
        CAltarCastButton.registerMessage(ALTAR_CAST_BUTTON_PRESSED_TO_CLIENT,
                AltarCastButtonPressedMessageToClient.class, AltarCastButtonPressedMessageToClient::encode,
                AltarCastButtonPressedMessageToClient::decode,
                AltarCastButtonPressedMessageHandlerOnClient::onMessageReceived);

        CAltarToggleButton = NetworkRegistry.newSimpleChannel(RLToggleButton,
                () -> TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION,
                AltarToggleButtonMessageHandlerOnClient::isProtocolAccepted,
                AltarToggleButtonMessageHandlerOnServer::isProtocolAccepted);
        CAltarToggleButton.registerMessage(ALTAR_TOGGLE_BUTTON_TO_SERVER,
                AltarToggleButtonMessageToServer.class, AltarToggleButtonMessageToServer::encode,
                AltarToggleButtonMessageToServer::decode,
                AltarToggleButtonMessageHandlerOnServer::onMessageReceived);
        CAltarToggleButton.registerMessage(ALTAR_TOGGLE_BUTTON_TO_CLIENT,
                AltarToggleButtonMessageToClient.class, AltarToggleButtonMessageToClient::encode,
                AltarToggleButtonMessageToClient::decode,
                AltarToggleButtonMessageHandlerOnClient::onMessageReceived);
    }
}
