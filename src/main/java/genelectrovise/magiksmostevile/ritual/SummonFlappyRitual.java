/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.ritual;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.glyph.GlyphMessageToClient;
import genelectrovise.magiksmostevile.network.glyph.GlyphNetworkingManager;
import genelectrovise.magiksmostevile.network.particle.ParticleNetworkingManager;
import genelectrovise.magiksmostevile.network.particle.ender.EnderParticleMessageToClient;
import genelectrovise.magiksmostevile.registry.orbital.registries.ItemOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
import genelectrovise.magiksmostevile.ritual.result.SummonFlappyResultHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author GenElectrovise 13 Jun 2020
 */
public class SummonFlappyRitual extends Ritual {
  public static final String displayName = "Summon Flappy!";
  public static final String description = "Summon the almighty Flappy the Bat!";
  public static final String information = "Summons a vampire bat into the world!";
  private static final int ichorRequirement = 60;

  /**
   * @param registryName
   * @param displayName
   * @param description
   */
  public SummonFlappyRitual() {
    super(displayName, description, information, ichorRequirement);
  }

  @Override
  protected boolean canStart() {

    if (!super.canStart()) {
      return false;
    }

    LazyOptional<IItemHandler> itemHandler =
        altar.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

    ItemStack[] stacks = new ItemStack[4];

    itemHandler.ifPresent((handler) -> {
      stacks[0] = handler.getStackInSlot(0);
      stacks[1] = handler.getStackInSlot(1);
      stacks[2] = handler.getStackInSlot(2);
      stacks[3] = handler.getStackInSlot(3);
    });

    for (int i = 0; i < stacks.length; i++) {
      if (stacks[i].getItem() == ItemOrbitalRegistry.VAMPIRE_BAT_TOOTH.get()
          && stacks[i].getCount() == 1) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void begin() {
    super.begin();
    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID,
                "textures/items/general/vampire_bat_tooth.png"),
            GlyphOrientation.VERTICAL, altar.getPos().up(5), true, 0.5));

    LazyOptional<IItemHandler> itemHandler =
        altar.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

    ItemStack[] stacks = new ItemStack[4];
    itemHandler.ifPresent((handler) -> {
      stacks[0] = handler.getStackInSlot(0);
      stacks[1] = handler.getStackInSlot(1);
      stacks[2] = handler.getStackInSlot(2);
      stacks[3] = handler.getStackInSlot(3);
    });

    for (int i = 0; i < stacks.length; i++) {
      if (stacks[i].getItem() == ItemOrbitalRegistry.VAMPIRE_BAT_TOOTH.get()) {

        int slot = i;
        itemHandler.ifPresent((handler) -> {
          // Have to extract old item stack before inserting new one
          handler.extractItem(slot, stacks[slot].getCount(), false);

          ItemStack stack = ItemStack.EMPTY;
          handler.insertItem(slot, stack, false);
        });
      }
    }
  }

  @Override
  protected RitualResult tick() {
    super.tick();

    if (altar.getWorld().isDaytime()) {
      return RitualResult.FAILURE;
    }

    if (isBetweenTicks(1, 60)) {
      if (altar.drainIchor(1)) {
        return RitualResult.CASTING;
      } else {
        return RitualResult.CATACLYSM;
      }
    }

    if (isBetweenTicks(100, 140)) {
      ParticleNetworkingManager.CEnderParticle.send(PacketDistributor.ALL.noArg(),
          new EnderParticleMessageToClient(altar.getPos().up(3), 2));
      return RitualResult.CASTING;
    }

    if (getTick() > 149) {
      return RitualResult.SUCCESS;
    }

    return RitualResult.CASTING;
  }

  @Override
  public ResultHandler<?> getResultHandler() {
    return new SummonFlappyResultHandler(getAltar(), this);
  }

}
