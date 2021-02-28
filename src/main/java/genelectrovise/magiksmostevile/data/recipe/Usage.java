package genelectrovise.magiksmostevile.data.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;

/**
 * Used by {@link SimpleRecipe}s to determine whether a recipe can be used by a recipe user. For
 * example, custom crafting recipes cannnot be used by the mortar, hence a modder would add
 * {@link Usage#MORTAR} to the recipe file. This would mean that the
 * {@link SimpleRecipe#craftable(CombinedInvWrapper, Usage)} method returns false when the
 * {@link Usage} is not present.
 * 
 * @author GenElectrovise
 *
 */
public class Usage {
  private static Map<String, Usage> usages = new HashMap<String, Usage>(); // Usage id to usage
  private ResourceLocation id;

  public static final Usage ALL = Usage.create(new ResourceLocation(MagiksMostEvile.MODID, "all"));
  public static final Usage MORTAR = Usage.create(new ResourceLocation(MagiksMostEvile.MODID, "mortar"));

  private Usage(ResourceLocation id) {
    this.id = id;
  }

  public static Usage create(ResourceLocation id) {
    Usage usage = new Usage(id);

    if (usages.containsKey(id.toString()) || usages.containsValue(usage)) {
      throw new IllegalStateException("Usage " + usage + " already exists!");
    }
    
    usages.putIfAbsent(id.toString(), usage);
    return usage;
  }

  public ResourceLocation getId() {
    return id;
  }

  public String serialise() {
    return id.toString();
  }

  public static Usage deserialise(String string) {
    return usages.getOrDefault(string, Usage.ALL);
  }

  /**
   * 
   * @param jsonArray The "usages" {@link JsonArray} of a recipe.
   * @return
   */
  public static ArrayList<Usage> deserialiseJsonArray(ResourceLocation id, JsonArray jsonArray) {

    if (jsonArray.size() < 1) {
      throw new JsonSyntaxException(id + " 'usages' does not contain any elements! (size < 1)");
    }

    ArrayList<Usage> usages = new ArrayList<Usage>();

    jsonArray.forEach((serial) -> {

      // Is a JsonPrimitive
      if ((serial instanceof JsonPrimitive)) {

        JsonPrimitive prim = (JsonPrimitive) serial;

        // The primitive is a string
        if (prim.isString()) {

          // Add the usage
          usages.add(Usage.deserialise(serial.getAsString()));
          return;
        }
      }


      throw new JsonSyntaxException("Serial item " + serial + " of 'usages' from the recipe " + id + " is not a primitive JSON string.");

    });

    return usages;
  }

}
