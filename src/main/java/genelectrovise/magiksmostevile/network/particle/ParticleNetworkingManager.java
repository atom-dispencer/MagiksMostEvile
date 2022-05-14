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
package genelectrovise.magiksmostevile.network.particle;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.particle.ender.EnderParticleMessageHandlerOnClient;
import genelectrovise.magiksmostevile.network.particle.ender.EnderParticleMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.particle.ender.EnderParticleMessageToClient;
import genelectrovise.magiksmostevile.network.particle.ender.EnderParticleMessageToServer;
import genelectrovise.magiksmostevile.network.particle.transfer_energy.TransferEnergyMessageHandlerOnClient;
import genelectrovise.magiksmostevile.network.particle.transfer_energy.TransferEnergyMessageHandlerOnServer;
import genelectrovise.magiksmostevile.network.particle.transfer_energy.TransferEnergyMessageToClient;
import genelectrovise.magiksmostevile.network.particle.transfer_energy.TransferEnergyMessageToServer;
import net.minecraft.resources.ResourceLocation;
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

    public static final ResourceLocation RLEnderParticle =
            new ResourceLocation(MagiksMostEvile.MODID, "ender_particle");
    public static final String ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION = "1.0";
    public static final int ENDER_PARTICLE_TO_SERVER = 11;
    public static final int ENDER_PARTICLE_TO_CLIENT = 12;
    public static final ResourceLocation RLEnergyTransfer =
            new ResourceLocation(MagiksMostEvile.MODID, "energy_transfer");
    public static final String TRANSFER_ENERGY_MESSAGE_PROTOCOL_VERSION = "1.0";
    public static final int TRANSFER_ENERGY_TO_SERVER = 11;
    public static final int TRANSFER_ENERGY_TO_CLIENT = 12;
    // Glyph
    public static SimpleChannel CEnderParticle;
    // Energy Tranfer
    public static SimpleChannel CTransferEnergy;

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
