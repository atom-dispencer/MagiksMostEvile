package genelectrovise.magiksmostevile.common.main.support;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;

public enum EnumEvileItemTier implements IItemTier {
  AMETHYST(2, 512, 9.0F, 1.5F, 50, () -> {
    return Ingredient.fromTag(EvileItemTags.AMETHYST);
  }), POWERED_AMETHYST(3, 512, 15.0F, 3.0F, 200, () -> {
    return Ingredient.fromTag(EvileItemTags.AMETHYST);
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
    private static TagCollection<Item> evile_itemtag_collection =
        new TagCollection<>((p_203643_0_) -> {
          return Optional.empty();
        }, "", false, "");
    private static int generation;
    public static final Tag<Item> AMETHYST = makeWrapperTag("amethyst");
    public static final Tag<Item> POWERED_AMETHYST = makeWrapperTag("powered_amethyst");
    public static final Tag<Item> OVER_POWERED_AMETHYST = makeWrapperTag("over_powered_amethyst");


    public static void setCollection(TagCollection<Item> collectionIn) {
      evile_itemtag_collection = collectionIn;
      ++generation;
    }

    public static TagCollection<Item> getCollection() {
      return evile_itemtag_collection;
    }

    public static int getGeneration() {
      return generation;
    }

    private static Tag<Item> makeWrapperTag(String p_199901_0_) {
      return new ItemTags.Wrapper(new ResourceLocation(p_199901_0_));
    }

    public static class Wrapper extends Tag<Item> {
      private int lastKnownGeneration = -1;
      private Tag<Item> cachedTag;

      public Wrapper(ResourceLocation resourceLocationIn) {
        super(resourceLocationIn);
      }

      public boolean contains(Item itemIn) {
        if (this.lastKnownGeneration != EvileItemTags.generation) {
          this.cachedTag = EvileItemTags.evile_itemtag_collection.getOrCreate(this.getId());
          this.lastKnownGeneration = EvileItemTags.generation;
        }

        return this.cachedTag.contains(itemIn);
      }

      public Collection<Item> getAllElements() {
        if (this.lastKnownGeneration != EvileItemTags.generation) {
          this.cachedTag = EvileItemTags.evile_itemtag_collection.getOrCreate(this.getId());
          this.lastKnownGeneration = EvileItemTags.generation;
        }

        return this.cachedTag.getAllElements();
      }

      public Collection<Tag.ITagEntry<Item>> getEntries() {
        if (this.lastKnownGeneration != EvileItemTags.generation) {
          this.cachedTag = EvileItemTags.evile_itemtag_collection.getOrCreate(this.getId());
          this.lastKnownGeneration = EvileItemTags.generation;
        }

        return this.cachedTag.getEntries();
      }
    }
  }

}
