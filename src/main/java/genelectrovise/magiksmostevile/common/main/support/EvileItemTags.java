package genelectrovise.magiksmostevile.common.main.support;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.ITagCollectionSupplier;
import net.minecraft.tags.TagRegistry;
import net.minecraft.tags.TagRegistryManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class EvileItemTags {

    public static final INamedTag<Item> AMETHYST = makeWrapperTag("amethyst");
    public static final INamedTag<Item> POWERED_AMETHYST = makeWrapperTag("powered_amethyst");
    public static final INamedTag<Item> OVER_POWERED_AMETHYST = makeWrapperTag("over_powered_amethyst");

    /**
     * Contains and creates new tags
     */
    private static TagRegistry<Item> itemTags =
        TagRegistryManager.create(new ResourceLocation(MagiksMostEvile.MODID, "itemTags"),
            ITagCollectionSupplier::getItemTags);

    /**
     * @return A simple {@link INamedTag} of the given id.
     */
    public static INamedTag<Item> makeWrapperTag(String id) {
      return itemTags.createTag(id);
    }

    /**
     * @see #createOptional(ResourceLocation, Set), where defaults are null.
     */
    public static IOptionalNamedTag<Item> createOptional(ResourceLocation name) {
      return createOptional(name, null);
    }

    /**
     * @param name The name of the tag to create
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
      return itemTags.getCollection();
    }

    /**
     * @return A list of each registered {@link INamedTag}
     */
    public static List<? extends INamedTag<Item>> getAllTags() {
      return itemTags.getTags();
    }
  }