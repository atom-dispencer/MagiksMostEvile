package genelectrovise.magiksmostevile.data.recipe;

import java.util.ArrayList;
import com.google.gson.JsonObject;
import genelectrovise.magiksmostevile.registry.orbital.registries.RecipeSerializerOrbitalRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * Deprecated! In development! Do not use!
 * 
 * @author GenElectrovise
 *
 */
public class SimpleRecipe extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SimpleRecipe>, IRecipe<IInventory>, IRecipeType<SimpleRecipe> {

  public static final IRecipeSerializer<SimpleRecipe> SERIALIZER = new SimpleRecipe();
  private ResourceLocation id;
  private ArrayList<Ingredient> ingredients;
  private ArrayList<ItemStack> outputs;
  private int primaryOutput;

  @Override
  public String toString() {
    return "SimpleRecipe [input=" + this.ingredients + ", output=" + this.outputs + ", id=" + this.id + "]";
  }

  @Override
  public boolean matches(IInventory inv, World worldIn) {

    int i = 0;
    for (Ingredient ing : ingredients) {
      if (!ing.test(inv.getStackInSlot(i))) {
        return false;
      }
      i++;
    }

    return true;
  }

  @Override
  public ItemStack getCraftingResult(IInventory inv) {
    return outputs.get(primaryOutput).copy();
  }

  @Override
  public boolean canFit(int width, int height) {
    return (width * height) >= ingredients.size();
  }

  @Override
  public ItemStack getRecipeOutput() {
    return outputs.get(primaryOutput).copy();
  }

  @Override
  public ResourceLocation getId() {
    return id;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return SimpleRecipe.SERIALIZER;
  }

  @Override
  public IRecipeType<?> getType() {
    return RecipeSerializerOrbitalRegistry.SIMPLE_TYPE;
  }

  @Override
  public ItemStack getIcon() {
    return outputs.get(primaryOutput).copy();
  }

  @Override
  public SimpleRecipe read(ResourceLocation recipeId, JsonObject json) {
    return null;
  }

  @Override
  public SimpleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
    return null;
  }

  @Override
  public void write(PacketBuffer buffer, SimpleRecipe recipe) {
    
  }

}
