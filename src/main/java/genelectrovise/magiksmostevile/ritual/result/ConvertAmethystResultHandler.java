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
package genelectrovise.magiksmostevile.ritual.result;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.glyph.GlyphMessageToClient;
import genelectrovise.magiksmostevile.network.glyph.GlyphNetworkingManager;
import genelectrovise.magiksmostevile.registry.orbital.ItemOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.ConvertAmethystRitual;
import genelectrovise.magiksmostevile.ritual.ResultHandler;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
import genelectrovise.magiksmostevile.tileentity.altar.AltarTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ConvertAmethystResultHandler extends ResultHandler<ConvertAmethystRitual> {

    private AltarTileEntity altar;
    private ConvertAmethystRitual ritual;

    /**
     * @param altar
     * @param ritual
     */
    public ConvertAmethystResultHandler(AltarTileEntity altar, ConvertAmethystRitual ritual) {
        super(altar, ritual);

        this.altar = altar;
        this.ritual = ritual;
    }

    @Override
    public void handleSuccess() {
        ritual.setDone(true);
        MagiksMostEvile.LOGGER.info("Done!");

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
            if (stacks[i].getItem() == ItemOrbitalRegistry.AMETHYST.get()) {

                int slot = i;
                itemHandler.ifPresent((handler) -> {
                    // Have to extract old item stack before inserting new one
                    handler.extractItem(slot, stacks[slot].getCount(), false);

                    int count = stacks[slot].getCount();
                    ItemStack stack = new ItemStack(ItemOrbitalRegistry.POWERED_AMETHYST.get(), count);
                    handler.insertItem(slot, stack, false);
                });
            }
        }

        GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
                new GlyphMessageToClient(
                        new ResourceLocation(MagiksMostEvile.MODID,
                                "textures/items/general/powered_amethyst.png"),
                        GlyphOrientation.VERTICAL, altar.getBlockPos().above(7), true, 0.5));

    }

    @Override
    public void handleCasting() {

    }

    @Override
    public void handleFailure() {
        GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
                new GlyphMessageToClient(
                        new ResourceLocation(MagiksMostEvile.MODID, "textures/ritual/fail.png"),
                        GlyphOrientation.VERTICAL, altar.getBlockPos().above(7), true, 0.5));
        MagiksMostEvile.LOGGER.info("Failed!");
    }

    @Override
    public void handleCataclysm() {
        GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
                new GlyphMessageToClient(
                        new ResourceLocation(MagiksMostEvile.MODID, "textures/ritual/fail.png"),
                        GlyphOrientation.VERTICAL, altar.getBlockPos().above(7), true, 0.5));
        MagiksMostEvile.LOGGER.info("BOOM!");
    }

}
