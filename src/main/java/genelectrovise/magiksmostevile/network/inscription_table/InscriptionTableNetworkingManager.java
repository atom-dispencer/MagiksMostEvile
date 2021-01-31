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
