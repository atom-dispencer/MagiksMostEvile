/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageHandlerOnClient;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageHandlerOnServer;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageToClient;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageToServer;
import genelectrovise.magiksmostevile.common.network.altar.cast_button.AltarCastButtonPressedMessageHandlerOnClient;
import genelectrovise.magiksmostevile.common.network.altar.cast_button.AltarCastButtonPressedMessageHandlerOnServer;
import genelectrovise.magiksmostevile.common.network.altar.cast_button.AltarCastButtonPressedMessageToClient;
import genelectrovise.magiksmostevile.common.network.altar.cast_button.AltarCastButtonPressedMessageToServer;
import genelectrovise.magiksmostevile.common.network.altar.energy_update.AltarEnergyUpdateMessageHandlerOnClient;
import genelectrovise.magiksmostevile.common.network.altar.energy_update.AltarEnergyUpdateMessageHandlerOnServer;
import genelectrovise.magiksmostevile.common.network.altar.energy_update.AltarEnergyUpdateMessageToClient;
import genelectrovise.magiksmostevile.common.network.altar.energy_update.AltarEnergyUpdateMessageToServer;
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
public class AltarNetworkingManager {

  // Energy update
  public static SimpleChannel CAltarEnergyUpdate;
  public static final ResourceLocation RLEnergyUpdate =
      new ResourceLocation(MagiksMostEvile.MODID, "altar_energy_update");
  public static final String ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ALTAR_ENERGY_UPDATE_TO_SERVER = 11;
  public static final int ALTAR_ENERGY_UPDATE_TO_CLIENT = 12;

  // Cast button
  public static SimpleChannel CAltarCastButton;
  public static final ResourceLocation RLCastButtonPressed =
      new ResourceLocation(MagiksMostEvile.MODID, "altar_cast_button_pressed");
  public static final String CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ALTAR_CAST_BUTTON_PRESSED_TO_SERVER = 21;
  public static final int ALTAR_CAST_BUTTON_PRESSED_TO_CLIENT = 22;

  // Arrow toggles
  public static SimpleChannel CAltarToggleButton;
  public static final ResourceLocation RLToggleButton =
      new ResourceLocation(MagiksMostEvile.MODID, "altar_toggle_button");
  public static final String TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ALTAR_TOGGLE_BUTTON_TO_SERVER = 31;
  public static final int ALTAR_TOGGLE_BUTTON_TO_CLIENT = 32;

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.dev("FMLCommonSetupEvent heard by AltarNetworkingManager!");
    CAltarEnergyUpdate = NetworkRegistry.newSimpleChannel(RLEnergyUpdate,
        () -> ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION,
        AltarEnergyUpdateMessageHandlerOnClient::isProtocolAccepted,
        AltarEnergyUpdateMessageHandlerOnServer::isProtocolAccepted);
    CAltarEnergyUpdate.registerMessage(ALTAR_ENERGY_UPDATE_TO_SERVER,
        AltarEnergyUpdateMessageToServer.class, AltarEnergyUpdateMessageToServer::encode,
        AltarEnergyUpdateMessageToServer::decode,
        AltarEnergyUpdateMessageHandlerOnServer::onMessageReceived);
    CAltarEnergyUpdate.registerMessage(ALTAR_ENERGY_UPDATE_TO_CLIENT,
        AltarEnergyUpdateMessageToClient.class, AltarEnergyUpdateMessageToClient::encode,
        AltarEnergyUpdateMessageToClient::decode,
        AltarEnergyUpdateMessageHandlerOnClient::onMessageReceived);

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
