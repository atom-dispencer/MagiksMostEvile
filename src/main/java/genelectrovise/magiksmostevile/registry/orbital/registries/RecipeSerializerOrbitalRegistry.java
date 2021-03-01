package genelectrovise.magiksmostevile.registry.orbital.registries;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.data.recipe.SimpleRecipe;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * {@link IRecipeSerializer}
 */
public class RecipeSerializerOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MagiksMostEvile.MODID);

  // Simple
  public static final RegistryObject<IRecipeSerializer<SimpleRecipe>> SIMPLE = RECIPE_SERIALIZERS.register("simple", () -> new SimpleRecipe());
  public static final IRecipeType<SimpleRecipe> SIMPLE_TYPE = new SimpleRecipe();

  @Override
  public String name() {
    return "recipeserializers";
  }

  @Override
  public int priority() {
    return 16;
  }

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(RECIPE_SERIALIZERS);
  }

  /**
   * This method lets you get all of the recipe data for a given recipe type. The existing methods for
   * this require an IInventory, and this allows you to skip that overhead. This method uses
   * reflection to get the recipes map, but an access transformer would also work.
   * 
   * @param world The World instance.
   * @return A map containing all recipes contained in the recipe manager for the World.
   */
  public static Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> getRecipes(World world) {

    Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap = ImmutableMap.of();

    try {

      RecipeManager manager = world.getRecipeManager();
      recipesMap = ObfuscationReflectionHelper.getPrivateValue(RecipeManager.class, manager, "recipes");
      //ReflectionUtil.makeUniversallyAccessible(field);
      //recipesMap = (Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>>) field.get(manager);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return recipesMap;
  }

}
