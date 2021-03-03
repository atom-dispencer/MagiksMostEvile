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
package genelectrovise.magiksmostevile.data.recipe;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Lists;
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
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
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
public class SimpleRecipe extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SimpleRecipe>, ICustomRecipe, IRecipeType<SimpleRecipe> {

  public static final Logger LOGGER = LogManager.getLogger(SimpleRecipe.class);

  public static final IRecipeSerializer<SimpleRecipe> SERIALIZER = (IRecipeSerializer<SimpleRecipe>) new SimpleRecipe();

  private ResourceLocation id;
  private ArrayList<Usage> usages;
  private ArrayList<Ingredient> ingredients;
  private ArrayList<ItemStack> results;
  private int primaryOutput;
  private int processingTime;
  private float experience;

  /**
   * If you want to access the serializer and type methods etc, use this constructor.
   */
  public SimpleRecipe() {}

  public SimpleRecipe(ResourceLocation id, ArrayList<Usage> usages, ArrayList<Ingredient> ingredients, ArrayList<ItemStack> results) {
    this(id, usages, ingredients, results, 0);
  }

  public SimpleRecipe(ResourceLocation id, ArrayList<Usage> usages, ArrayList<Ingredient> ingredients, ArrayList<ItemStack> results, int primaryOutput) {
    this(id, usages, ingredients, results, primaryOutput, 0, 0);
  }

  public SimpleRecipe(ResourceLocation id, ArrayList<Usage> usages, ArrayList<Ingredient> ingredients, ArrayList<ItemStack> results, int primaryOutput, int processingTime, float experience) {
    this.ingredients = ingredients;
    this.usages = usages;
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

    if (inv == null || worldIn == null) {
      LOGGER.error("SimpleRecipe#matches(inv, world) found a parameter null. Unable to proceed with checks.");
      return false;
    }

    for (int j = 0; j < ingredients.size(); j++) {
      if (!ingredients.get(j).test(inv.getStackInSlot(0))) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean craftable(CombinedInvWrapper inv, Usage usage) {

    if (!hasUsage(usage)) {
      LOGGER.warn("SimpleRecipe#craftable(inv, usage):" + usage + " is not compatible with the provider.");
      return false;
    }

    if (inv == null) {
      LOGGER.error("SimpleRecipe#craftable(inv, usage) found inv null. Unable to proceed with checks.");
      return false;
    }

    for (int j = 0; j < ingredients.size(); j++) {
      if (!ingredients.get(j).test(inv.getStackInSlot(0))) {
        return false;
      }
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

    ArrayList<Usage> usages = json.has("usages") ? Usage.deserialiseJsonArray(recipeId, JSONUtils.getJsonArray(json, "usages")) : new ArrayList<Usage>(Lists.newArrayList(Usage.ALL));
    ArrayList<Ingredient> ingredients = deserializeIngredients(recipeId, JSONUtils.getJsonArray(json, "ingredients"));
    ArrayList<ItemStack> results = deserializeResults(recipeId, JSONUtils.getJsonArray(json, "results"));
    float experience = JSONUtils.getFloat(json, "experience", 0.0F);
    int processingTime = JSONUtils.getInt(json, "processingtime", this.processingTime);

    // usages
    return new SimpleRecipe(recipeId, usages, ingredients, results, processingTime, processingTime, experience);
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

    // Read usages
    int usageCount = buffer.readInt();
    ArrayList<Usage> usages = new ArrayList<Usage>();
    for (int i = 0; i < usageCount; i++) {
      usages.add(Usage.deserialise(buffer.readString()));
    }

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

    // usages
    return new SimpleRecipe(recipeId, usages, ingredients, results, primaryResult, processingTime, experience);
  }

  /**
   * {@link CookingRecipeSerializer}
   * <ol>
   * <li>count of usages
   * <li>usages
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

    // Write usages
    buffer.writeInt(usages.size());
    for (Usage usage : usages) {
      buffer.writeString(usage.serialise());
    }

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

  public boolean hasUsage(Usage usage) {
    return usages.contains(usage) || usage == Usage.ALL;
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
    return ICustomRecipe.super.getIngredients();
  }

  @Override
  public String getGroup() {
    return ICustomRecipe.super.getGroup();
  }

  //

  public ArrayList<Usage> getUsages() {
    return usages;
  }

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
