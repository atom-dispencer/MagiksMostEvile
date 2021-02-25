package genelectrovise.magiksmostevile.data.recipe;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import genelectrovise.magiksmostevile.registry.orbital.registries.RecipeSerializerOrbitalRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * A simple recipe for all of your simple recipe needs! Handles a list of inputs and outputs,
 * extends {@link ForgeRegistryEntry} so it can be directly registered, and implements
 * {@link IRecipeSerializer}, {@link IRecipeType} and {@link IRecipe}, all typed to itself so it's
 * fully self contained.
 * 
 * @author GenElectrovise
 *
 */
public class SimpleRecipe extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SimpleRecipe>, IRecipe<IInventory>, IRecipeType<SimpleRecipe> {

  public static final IRecipeSerializer<SimpleRecipe> SERIALIZER = (IRecipeSerializer<SimpleRecipe>) new SimpleRecipe();
  
  private ResourceLocation id;
  private ArrayList<Ingredient> ingredients;
  private ArrayList<ItemStack> results;
  private int primaryOutput;
  private int processingTime;
  private float experience;

  public SimpleRecipe() {}

  public SimpleRecipe(ResourceLocation id, ArrayList<Ingredient> ingredients, ArrayList<ItemStack> results) {
    this(id, ingredients, results, 0);
  }

  public SimpleRecipe(ResourceLocation id, ArrayList<Ingredient> ingredients, ArrayList<ItemStack> results, int primaryOutput) {
    this(id, ingredients, results, primaryOutput, 0, 0);
  }

  public SimpleRecipe(ResourceLocation id, ArrayList<Ingredient> ingredients, ArrayList<ItemStack> results, int primaryOutput, int processingTime, float experience) {
    this.ingredients = ingredients;
    this.results = results;
    this.primaryOutput = primaryOutput;
    this.processingTime = processingTime;
    this.experience = experience;
  }


  @Override
  public String toString() {
    return "SimpleRecipe [ingredients=" + this.ingredients + ", results=" + this.results + ", id=" + this.id + "]";
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


  /**
   * Read whole recipe from JSON to Java
   */
  @Override
  public SimpleRecipe read(ResourceLocation recipeId, JsonObject json) {

    if (!(json.has("results") || json.has("ingredients"))) {
      throw new JsonSyntaxException("Recipe " + recipeId + " does not contain all mandatory fields!");
    }

    ArrayList<Ingredient> ingredients = deserializeIngredients(recipeId, JSONUtils.getJsonArray(json, "ingredient"));
    ArrayList<ItemStack> results = deserializeResults(recipeId, JSONUtils.getJsonArray(json, "results"));
    float experience = JSONUtils.getFloat(json, "experience", 0.0F);
    int processingTime = JSONUtils.getInt(json, "processingtime", this.processingTime);

    return new SimpleRecipe(recipeId, ingredients, results, processingTime, processingTime, experience);
  }

  /**
   * Read results from JSON to Java
   */
  private static ArrayList<ItemStack> deserializeResults(ResourceLocation recipeId, JsonArray array) {

    ArrayList<ItemStack> results = new ArrayList<ItemStack>();

    array.forEach((elem) -> {
      if (!(elem instanceof JsonObject)) {
        throw new JsonSyntaxException("Entries in recipe " + recipeId + " #results must all be JsonObjects.");
      }

      JsonObject obj = (JsonObject) elem;
      boolean hasItem = obj.has("item");
      boolean hasTag = obj.has("tag");
      if (!hasItem && !hasTag) {
        throw new JsonSyntaxException("Results of recipe " + recipeId + "  must all contain an item or tag.");
      }

      ItemStack stack = ShapedRecipe.deserializeItem(obj);
      results.add(stack);
    });

    return results;
  }

  /**
   * Read ingredients from JSON to Java
   * 
   * @param recipeId
   * @param array
   * @return
   */
  private static ArrayList<Ingredient> deserializeIngredients(ResourceLocation recipeId, JsonArray array) {

    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    array.forEach((elem) -> {
      if (!(elem instanceof JsonObject)) {
        throw new JsonSyntaxException("Entries in recipe " + recipeId + " #ingredients must all be JsonObjects.");
      }

      Ingredient ingredient = Ingredient.deserialize(elem);
      ingredients.add(ingredient);
    });

    return ingredients;
  }

  /**
   * Read from a packet buffer
   */
  @Override
  public SimpleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

    // Read ingredients
    int ingredientCount = buffer.readInt();
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    for (int i = 0; i < ingredientCount; i++) {
      ingredients.add(Ingredient.read(buffer));
    }

    // Read results
    int primaryResult = buffer.readInt();
    int resultCount = buffer.readInt();
    ArrayList<ItemStack> results = new ArrayList<ItemStack>();
    for (int i = 0; i < resultCount; i++) {
      results.add(buffer.readItemStack());
    }

    float experience = buffer.readFloat();
    int processingTime = buffer.readVarInt();

    return new SimpleRecipe(recipeId, ingredients, results, primaryResult, processingTime, experience);
  }

  /**
   * {@link CookingRecipeSerializer}
   * <ol>
   * <li>count of ingredients
   * <li>ingredients
   * <li>primaryResult
   * <li>count of results
   * <li>results
   * <li>experience
   * <li>processingTime
   * </ol>
   */
  @Override
  public void write(PacketBuffer buffer, SimpleRecipe recipe) {

    // Write ingredients
    buffer.writeInt(ingredients.size());
    for (Ingredient ingredient : ingredients) {
      ingredient.write(buffer);
    }

    // Write results
    buffer.writeInt(primaryOutput);
    buffer.writeInt(results.size());
    for (ItemStack stack : results) {
      buffer.writeItemStack(stack);
    }

    buffer.writeFloat(recipe.experience);
    buffer.writeVarInt(recipe.processingTime);
  }

  //

  @Override
  public ItemStack getCraftingResult(IInventory inv) {
    return results.get(primaryOutput).copy();
  }

  @Override
  public boolean canFit(int width, int height) {
    return (width * height) >= ingredients.size();
  }

  @Override
  public ItemStack getRecipeOutput() {
    return results.get(primaryOutput).copy();
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
    return results.get(primaryOutput).copy();
  }

  @Override
  public NonNullList<Ingredient> getIngredients() {
    return IRecipe.super.getIngredients();
  }

  @Override
  public String getGroup() {
    return IRecipe.super.getGroup();
  }

  //

  public int getProcessingTime() {
    return processingTime;
  }

  public int getPrimaryOutput() {
    return primaryOutput;
  }

  public ArrayList<ItemStack> getResults() {
    return results;
  }

  public float getExperience() {
    return experience;
  }

}
