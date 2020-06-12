/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
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
	public static final ResourceLocation RLEnergyUpdate = new ResourceLocation(MagiksMostEvile.MODID, "altar_energy_update");
	public static final String ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION = "1.0";
	public static final int ALTAR_ENERGY_UPDATE_TO_SERVER = 11;
	public static final int ALTAR_ENERGY_UPDATE_TO_CLIENT = 12;

	// Cast button pressed
	public static SimpleChannel CAltarCastButtonPressed;
	public static final ResourceLocation RLCastButtonPressed = new ResourceLocation(MagiksMostEvile.MODID, "altar_cast_button_pressed");
	public static final String CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION = "1.0";
	public static final int ALTAR_CAST_BUTTON_PRESSED_TO_SERVER = 21;
	public static final int ALTAR_CAST_BUTTON_PRESSED_TO_CLIENT = 22;

	@SubscribeEvent
	public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
		MagiksMostEvile.LOGGER.dev("FMLCommonSetupEvent heard by AltarNetworkingManager!");
		CAltarEnergyUpdate = NetworkRegistry.newSimpleChannel(RLEnergyUpdate, () -> ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION, AltarEnergyUpdateMessageHandlerOnClient::isProtocolAccepted, AltarEnergyUpdateMessageHandlerOnServer::isProtocolAccepted);
		CAltarEnergyUpdate.registerMessage(ALTAR_ENERGY_UPDATE_TO_SERVER, AltarEnergyUpdateMessageToServer.class, AltarEnergyUpdateMessageToServer::encode, AltarEnergyUpdateMessageToServer::decode, AltarEnergyUpdateMessageHandlerOnServer::onMessageReceived);
		CAltarEnergyUpdate.registerMessage(ALTAR_ENERGY_UPDATE_TO_CLIENT, AltarEnergyUpdateMessageToClient.class, AltarEnergyUpdateMessageToClient::encode, AltarEnergyUpdateMessageToClient::decode, AltarEnergyUpdateMessageHandlerOnClient::onMessageReceived);

		CAltarCastButtonPressed = NetworkRegistry.newSimpleChannel(RLCastButtonPressed, () -> CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION, AltarCastButtonPressedMessageHandlerOnClient::isProtocolAccepted, AltarCastButtonPressedMessageHandlerOnServer::isProtocolAccepted);
		CAltarCastButtonPressed.registerMessage(ALTAR_CAST_BUTTON_PRESSED_TO_SERVER, AltarCastButtonPressedMessageToServer.class, AltarCastButtonPressedMessageToServer::encode, AltarCastButtonPressedMessageToServer::decode, AltarCastButtonPressedMessageHandlerOnServer::onMessageReceived);
		CAltarCastButtonPressed.registerMessage(ALTAR_CAST_BUTTON_PRESSED_TO_CLIENT, AltarCastButtonPressedMessageToClient.class, AltarCastButtonPressedMessageToClient::encode, AltarCastButtonPressedMessageToClient::decode, AltarCastButtonPressedMessageHandlerOnClient::onMessageReceived);
	}
}