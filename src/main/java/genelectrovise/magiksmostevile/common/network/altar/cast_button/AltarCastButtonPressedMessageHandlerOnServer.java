/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.cast_button;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarContainer;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageHandlerOnServer {

  public static void onMessageReceived(final AltarCastButtonPressedMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
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

  private static void processMessage(AltarCastButtonPressedMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.dev("processing message!");

    // Get and check container
    Container container = ctxSupplier.get().getSender().openContainer;

    if (!(container instanceof AltarContainer)) {
      return;
    }

    AltarContainer altarContainer = (AltarContainer) container;
    BlockPos pos = altarContainer.getAltar().getPos();

    // Get and check tile entity
    TileEntity tileEntity = ctxSupplier.get().getSender().world.getTileEntity(pos);

    if (!(tileEntity instanceof AltarTileEntity)) {
      return;
    }

    AltarTileEntity altarTileEntity = (AltarTileEntity) tileEntity;
    altarTileEntity.castRitual(
        AltarTileEntity.getRitualFromResourceLocation(message.getRitualResourceLocation()));
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
