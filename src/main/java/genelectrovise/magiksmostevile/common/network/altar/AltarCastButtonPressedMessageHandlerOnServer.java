/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageHandlerOnServer {

	public static void onMessageReceived(final AltarCastButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.dev("Message recieved by server!");

		ctxSupplier.get().setPacketHandled(true);

		if (!message.isValid()) {
			MagiksMostEvile.LOGGER.warn("Invalid message received on server.");
			return;
		}
		if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
			MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
		}

		ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
	}

	private static void processMessage(AltarCastButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
		ServerWorld world = ctxSupplier.get().getSender().getServerWorld();

		MagiksMostEvile.LOGGER.dev("processing message!");

		TileEntity tileEntity = world.getTileEntity(message.getPos());
		if (tileEntity instanceof AltarTileEntity) {
			MagiksMostEvile.LOGGER.dev("TileEntity valid! We are go for launch!");
			
			AltarTileEntity te = (AltarTileEntity) tileEntity;

			te.castRitual(message.getRitualResourceLocation());
			
			return;
		}
		
		MagiksMostEvile.LOGGER.dev("TileEntity invalid! Ritual cancelled... :(");
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}
