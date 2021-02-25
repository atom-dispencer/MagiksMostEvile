package genelectrovise.magiksmostevile.tileentity.mortar;

import com.google.gson.JsonObject;
import genelectrovise.magiksmostevile.registry.orbital.registries.BlockOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.registries.RecipeSerializerOrbitalRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * {@link StonecuttingRecipe} <br>
 * <a href="https://github.com/Minecraft-Forge-Tutorials/Custom-Json-Recipes">Example</a>
 * 
 * @author GenElectrovise
 *
 */
public class MortarRecipe implements IRecipe<IInventory> {

  public static final Serializer SERIALIZER = new Serializer();
  private final ResourceLocation id;

  private Ingredient inputItem;
  private ITag<Item> inputTag;
  private ItemStack output;

  public MortarRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
    this.id = id;
    this.inputItem = input;
    this.output = output;
  }

  @Override
  public String toString() {
    return "MortarRecipe [input=" + this.inputItem + ", output=" + this.output + ", id=" + this.id + "]";
  }

  /**
   * Mostly ignored here, as the Mortar does not use an {@link IInventory}
   */
  @Override
  public boolean matches(IInventory inv, World worldIn) {
    return this.inputItem.test(inv.getStackInSlot(0));
  }

  @Override
  public ItemStack getCraftingResult(IInventory inv) {
    return this.output.copy();
  }

  @Override
  public boolean canFit(int width, int height) {
    return width > 0 && height > 0;
  }

  @Override
  public ItemStack getRecipeOutput() {
    return this.output.copy();
  }

  @Override
  public ResourceLocation getId() {
    return this.id;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return SERIALIZER;
  }

  @Override
  public ItemStack getIcon() {
    return new ItemStack(BlockOrbitalRegistry.MORTAR.get());
  }

  public boolean isValid(ItemStack input) {
    return this.inputItem.test(input);
  }

  @Override
  public IRecipeType<?> getType() {
    return RecipeSerializerOrbitalRegistry.MORTAR_TYPE;
  }

  /**
   * A shell class for the mortar recipe type
   * 
   * @author GenElectrovise
   *
   */
  public static class Type implements IRecipeType<MortarRecipe> {

  }

  /**
   * JSON to Java conversion for {@link MortarRecipe}s
   * 
   * @author GenElectrovise
   *
   */
  public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MortarRecipe> {

    @Override
    public MortarRecipe read(ResourceLocation recipeId, JsonObject json) {
      
      
      return null;
    }

    @Override
    public MortarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

      final Ingredient input = Ingredient.read(buffer);
      final ItemStack output = buffer.readItemStack();

      return new MortarRecipe(recipeId, input, output);
    }

    @Override
    public void write(PacketBuffer buffer, MortarRecipe recipe) {
      recipe.inputItem.write(buffer);
      buffer.writeItemStack(recipe.output);
    }

  }

}
