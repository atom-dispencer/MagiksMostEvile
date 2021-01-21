/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.particle;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.particle.ender.EnderParticleMessageHandlerOnClient;
import genelectrovise.magiksmostevile.common.network.particle.ender.EnderParticleMessageHandlerOnServer;
import genelectrovise.magiksmostevile.common.network.particle.ender.EnderParticleMessageToClient;
import genelectrovise.magiksmostevile.common.network.particle.ender.EnderParticleMessageToServer;
import genelectrovise.magiksmostevile.common.network.particle.transfer_energy.TransferEnergyMessageHandlerOnClient;
import genelectrovise.magiksmostevile.common.network.particle.transfer_energy.TransferEnergyMessageHandlerOnServer;
import genelectrovise.magiksmostevile.common.network.particle.transfer_energy.TransferEnergyMessageToClient;
import genelectrovise.magiksmostevile.common.network.particle.transfer_energy.TransferEnergyMessageToServer;
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
public class ParticleNetworkingManager {

  // Glyph
  public static SimpleChannel CEnderParticle;
  public static final ResourceLocation RLEnderParticle =
      new ResourceLocation(MagiksMostEvile.MODID, "ender_particle");
  public static final String ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int ENDER_PARTICLE_TO_SERVER = 11;
  public static final int ENDER_PARTICLE_TO_CLIENT = 12;

  // Energy Tranfer
  public static SimpleChannel CTransferEnergy;
  public static final ResourceLocation RLEnergyTransfer =
      new ResourceLocation(MagiksMostEvile.MODID, "energy_transfer");
  public static final String TRANSFER_ENERGY_MESSAGE_PROTOCOL_VERSION = "1.0";
  public static final int TRANSFER_ENERGY_TO_SERVER = 11;
  public static final int TRANSFER_ENERGY_TO_CLIENT = 12;

  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("FMLCommonSetupEvent heard by GlyphNetworkManager!");
    CEnderParticle = NetworkRegistry.newSimpleChannel(RLEnderParticle,
        () -> ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION,
        EnderParticleMessageHandlerOnClient::isProtocolAccepted,
        EnderParticleMessageHandlerOnServer::isProtocolAccepted);
    CEnderParticle.registerMessage(ENDER_PARTICLE_TO_SERVER, EnderParticleMessageToServer.class,
        EnderParticleMessageToServer::encode, EnderParticleMessageToServer::decode,
        EnderParticleMessageHandlerOnServer::onMessageReceived);
    CEnderParticle.registerMessage(ENDER_PARTICLE_TO_CLIENT, EnderParticleMessageToClient.class,
        EnderParticleMessageToClient::encode, EnderParticleMessageToClient::decode,
        EnderParticleMessageHandlerOnClient::onMessageReceived);

    CTransferEnergy = NetworkRegistry.newSimpleChannel(RLEnergyTransfer,
        () -> TRANSFER_ENERGY_MESSAGE_PROTOCOL_VERSION,
        TransferEnergyMessageHandlerOnClient::isProtocolAccepted,
        TransferEnergyMessageHandlerOnServer::isProtocolAccepted);
    CTransferEnergy.registerMessage(TRANSFER_ENERGY_TO_SERVER, TransferEnergyMessageToServer.class,
        TransferEnergyMessageToServer::encode, TransferEnergyMessageToServer::decode,
        TransferEnergyMessageHandlerOnServer::onMessageReceived);
    CTransferEnergy.registerMessage(TRANSFER_ENERGY_TO_CLIENT, TransferEnergyMessageToClient.class,
        TransferEnergyMessageToClient::encode, TransferEnergyMessageToClient::decode,
        TransferEnergyMessageHandlerOnClient::onMessageReceived);
  }

}
