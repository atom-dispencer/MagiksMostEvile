/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author GenElectrovise 23 May 2020
 */
public class AltarNetworkingManager {
	public static SimpleChannel channel;
	public static final ResourceLocation channelRL = new ResourceLocation(MagiksMostEvile.MODID, "altar_channel");
	public static final String MESSAGE_PROTOCOL_VERSION = "1.0";

	public static final int ALTAR_ENERGY_TO_SERVER = 35; // a unique ID for this message type. It helps detect errors if you don't use
														// zero!
	public static final int ALTAR_ENERGY_TO_CLIENT = 63;

	public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
		MagiksMostEvile.LOGGER.dev("FMLCommonSetupEvent heard by AltarNetworkingManager");
		channel = NetworkRegistry.newSimpleChannel(channelRL, () -> MESSAGE_PROTOCOL_VERSION, AltarEnergyUpdateMessageHandlerOnClient::isProtocolAccepted, AltarEnergyUpdateMessageHandlerOnServer::isProtocolAccepted);

		channel.registerMessage(ALTAR_ENERGY_TO_SERVER, AltarEnergyUpdateMessageToServer.class, AltarEnergyUpdateMessageToServer::encode, AltarEnergyUpdateMessageToServer::decode, AltarEnergyUpdateMessageHandlerOnServer::onMessageReceived);
		channel.registerMessage(ALTAR_ENERGY_TO_CLIENT, AltarEnergyUpdateMessageToClient.class, AltarEnergyUpdateMessageToClient::encode, AltarEnergyUpdateMessageToClient::decode, AltarEnergyUpdateMessageHandlerOnClient::onMessageReceived);
	}
}