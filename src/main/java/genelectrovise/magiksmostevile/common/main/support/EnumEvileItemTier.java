package genelectrovise.magiksmostevile.common.main.support;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.ITagCollectionSupplier;
import net.minecraft.tags.TagRegistry;
import net.minecraft.tags.TagRegistryManager;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;;

public enum EnumEvileItemTier implements IItemTier {

  // Tags to allow repairing of items in an anvil
  // Amethyst
  AMETHYST(2, 512, 9.0F, 1.5F, 50, () -> {
    return Ingredient.fromItems(EvileDeferredRegistry.AMETHYST.get());
  }),
  // Powered amethyst
  POWERED_AMETHYST(3, 512, 15.0F, 3.0F, 200, () -> {
    return Ingredient.fromItems(EvileDeferredRegistry.POWERED_AMETHYST.get());
  });

  private final int harvestLevel;
  private final int maxUses;
  private final float efficiency;
  private final float attackDamage;
  private final int enchantability;
  private final LazyValue<Ingredient> repairMaterial;

  private EnumEvileItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn,
      float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
    this.harvestLevel = harvestLevelIn;
    this.maxUses = maxUsesIn;
    this.efficiency = efficiencyIn;
    this.attackDamage = attackDamageIn;
    this.enchantability = enchantabilityIn;
    this.repairMaterial = new LazyValue<>(repairMaterialIn);
  }

  public int getMaxUses() {
    return this.maxUses;
  }

  public float getEfficiency() {
    return this.efficiency;
  }

  public float getAttackDamage() {
    return this.attackDamage;
  }

  public int getHarvestLevel() {
    return this.harvestLevel;
  }

  public int getEnchantability() {
    return this.enchantability;
  }

  public Ingredient getRepairMaterial() {
    return this.repairMaterial.getValue();
  }

  // +++++++++++++++++++++++++++++++++++++++_EVILE_ITEM_TAGS_++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

  public static class EvileItemTags {

    public static final INamedTag<Item> AMETHYST = makeWrapperTag("amethyst");

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

}
