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
package genelectrovise.magiksmostevile.core.support;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Supplier;

public class EvileItemTags {

    /**
     * Contains and creates new tags
     */
    private static TagRegistry<Item> itemTags = TagRegistryManager.create(
            new ResourceLocation(MagiksMostEvile.MODID, "itemTags"), ITagCollectionSupplier::getItems);
    public static final INamedTag<Item> AMETHYST = makeWrapperTag("amethyst");
    public static final INamedTag<Item> POWERED_AMETHYST = makeWrapperTag("powered_amethyst");
    public static final INamedTag<Item> OVER_POWERED_AMETHYST =
            makeWrapperTag("over_powered_amethyst");

    /**
     * @return A simple {@link INamedTag} of the given id.
     */
    public static INamedTag<Item> makeWrapperTag(String id) {
        return itemTags.bind(id);
    }

    /**
     * @see #createOptional(ResourceLocation, Set), where defaults are null.
     */
    public static IOptionalNamedTag<Item> createOptional(ResourceLocation name) {
        return createOptional(name, null);
    }

    /**
     * @param name     The name of the tag to create
     * @param defaults A {@link Set} of {@link Supplier}s to give the default values of the tag
     * @return The completed {@link IOptionalNamedTag}
     */
    public static IOptionalNamedTag<Item> createOptional(ResourceLocation name,
                                                         @Nullable Set<Supplier<Item>> defaults) {
        return itemTags.createOptional(name, defaults);
    }

    /**
     * @return An {@link ITagCollection} of the the registered {@link ITag}s.
     */
    public static ITagCollection<Item> getCollection() {
        return itemTags.getAllTags();
    }

    /**
     * @return A list of each registered {@link INamedTag}
     */
    public static ITagCollection<Item> getAllTags() {
        return itemTags.getAllTags();
    }
}
