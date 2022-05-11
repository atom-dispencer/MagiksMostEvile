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
package genelectrovise.magiksmostevile.registry.orbital;

import com.google.common.collect.ImmutableMap;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.data.recipe.SimpleRecipe;
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

import java.util.Map;

/**
 * {@link IRecipeSerializer}
 */
public class RecipeSerializerOrbitalRegistry implements IOrbitalRegistry {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MagiksMostEvile.MODID);

    // Simple
    public static final RegistryObject<IRecipeSerializer<SimpleRecipe>> SIMPLE = RECIPE_SERIALIZERS.register("simple", () -> new SimpleRecipe());
    public static final IRecipeType<SimpleRecipe> SIMPLE_TYPE = new SimpleRecipe();

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
            // ReflectionUtil.makeUniversallyAccessible(field);
            // recipesMap = (Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>>) field.get(manager);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipesMap;
    }

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
        OrbitalRegistryHandler.registerDeferredRegister(RECIPE_SERIALIZERS);
    }

}
