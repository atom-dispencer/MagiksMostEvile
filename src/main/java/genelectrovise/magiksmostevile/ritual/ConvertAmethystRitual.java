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
package genelectrovise.magiksmostevile.ritual;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.glyph.GlyphMessageToClient;
import genelectrovise.magiksmostevile.network.glyph.GlyphNetworkingManager;
import genelectrovise.magiksmostevile.registry.orbital.registries.ItemOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
import genelectrovise.magiksmostevile.ritual.result.ConvertAmethystResultHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author GenElectrovise 19 May 2020
 */
public class ConvertAmethystRitual extends Ritual {
    public static final String displayName = "Magikify Amethyst";
    public static final String description = "Imbue an amethyst with magiky powers!";
    public static final String information = "Convert an amethyst into a powered amethyst";
    private static final int energyRequirement = 50;

    public ConvertAmethystRitual() {
        super(displayName, description, information, energyRequirement);
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
            if (stacks[i].getItem() == ItemOrbitalRegistry.AMETHYST.get()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void begin() {
        GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
                new GlyphMessageToClient(new ResourceLocation(MagiksMostEvile.MODID, "textures/items/general/amethyst.png"), GlyphOrientation.VERTICAL, altar.getBlockPos().above(7), true, 0.5));
        super.begin();
    }

    @Override
    protected RitualResult tick() {
        super.tick();

        if (isBetweenTicks(1, 50, true)) {
            if (altar.drainIchor(1)) {
                return RitualResult.CASTING;
            } else {
                return RitualResult.CATACLYSM;
            }
        }

        if (getTick() > 360) {
            return RitualResult.SUCCESS;
        }

        return RitualResult.CASTING;
    }

    @Override
    public ResultHandler<?> getResultHandler() {
        return new ConvertAmethystResultHandler(getAltar(), this);
    }

}
