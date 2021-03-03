/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.network.altar.arrow_toggles;

import java.util.ArrayList;
import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.network.altar.arrow_toggles.AltarToggleButtonMessageToServer.ToggleDirection;
import genelectrovise.magiksmostevile.registry.orbital.registries.RitualOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.Ritual;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarToggleButtonMessageHandlerOnServer {

  public static void onMessageReceived(final AltarToggleButtonMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.debug("Message recieved by server!");

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      MagiksMostEvile.LOGGER.error("Invalid message!");
      return;
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  /**
   * @param message
   * @param ctxSupplier
   * @return
   */
  private static void processMessage(AltarToggleButtonMessageToServer message,
      Supplier<Context> ctxSupplier) {
    ArrayList<Supplier<Ritual>> allRituals = new ArrayList<Supplier<Ritual>>();
    ArrayList<ResourceLocation> possibleRitualKeys = new ArrayList<ResourceLocation>();

    // Create a list of all the rituals
    RitualOrbitalRegistry.RITUALS.getEntries().forEach((ritual) -> {
      allRituals.add(ritual);
    });

    // Find which ones the player can cast (has advancement for). Creative players
    // get all.
    for (Supplier<Ritual> ritualSupplier : allRituals) {
      if (playerHasAdvancement(ctxSupplier.get().getSender(),
          ritualSupplier.get().getRegistryName())) {
        possibleRitualKeys.add(ritualSupplier.get().getRegistryName());
      }
    }

    // Get the index of the current resource location
    int indexOfResourceLocation;
    if (possibleRitualKeys.contains(message.getRitualRL())) {
      indexOfResourceLocation = possibleRitualKeys.indexOf(message.getRitualRL());
    } else {
      indexOfResourceLocation = 0;
    }

    // Generate an index of the new resource location
    int indexOfNew;
    if (message.getToggleDirection() == ToggleDirection.LEFT) {
      indexOfNew = ((indexOfResourceLocation - 1) < 0 ? possibleRitualKeys.size() - 1
          : indexOfResourceLocation - 1);
    } else if (message.getToggleDirection() == ToggleDirection.RIGHT) {
      indexOfNew = ((indexOfResourceLocation + 1) > possibleRitualKeys.size() - 1 ? 0
          : indexOfResourceLocation + 1);
    } else {
      throw new IllegalStateException("Invalid ToggleDirection on processing message!");
    }

    // Send a packet of the new resource location to the player
    AltarNetworkingManager.CAltarToggleButton.send(
        PacketDistributor.PLAYER.with(() -> ctxSupplier.get().getSender()),
        new AltarToggleButtonMessageToClient(possibleRitualKeys.get(indexOfNew)));
  }

  /**
   * @param sender
   * @param resourceLocation
   * @return Whether the given player has the given advancement.
   */
  private static boolean playerHasAdvancement(ServerPlayerEntity sender,
      ResourceLocation resourceLocation) {

    if (sender.isCreative()) {
      return true;
    }

    Advancement advancement =
        sender.server.getAdvancementManager().getAdvancement(resourceLocation);

    if (advancement == null) {
      MagiksMostEvile.LOGGER
          .warn("The advancement-ritual " + resourceLocation + " does not exist!");
      return false;
    }

    if (sender.getAdvancements().getProgress(advancement).isDone()) {
      return true;
    }

    return false;
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }
}
