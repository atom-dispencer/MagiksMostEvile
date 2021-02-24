package genelectrovise.magiksmostevile.registry.orbital.registries;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.reference.ReflectionUtil;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.tileentity.mortar.MortarRecipe;
import genelectrovise.magiksmostevile.tileentity.mortar.MortarRecipeType;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * {@link IRecipeSerializer}
 */
public class RecipeSerializerOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MagiksMostEvile.MODID);

  public static final RegistryObject<IRecipeSerializer<MortarRecipe>> MORTAR = RECIPE_SERIALIZERS.register("mortar", () -> new MortarRecipe.Serializer());
  public static final IRecipeType<MortarRecipe> MORTAR_TYPE = new MortarRecipeType();

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
   * @param recipeType The type of recipe to grab.
   * @param manager The recipe manager. This is generally taken from a World.
   * @return A map containing all recipes for the passed recipe type. This map is immutable and can
   *         not be modified.
   */
  @SuppressWarnings("unchecked")
  public static Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> recipeType, RecipeManager manager) {

    Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap = new HashMap<>();

    try {

      Field field = RecipeManager.class.getField("recipes");
      ReflectionUtil.makeUniversallyAccessible(field);
      recipesMap = (Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>>) field.get(RecipeManager.class);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return recipesMap.get(recipeType);
  }

}
