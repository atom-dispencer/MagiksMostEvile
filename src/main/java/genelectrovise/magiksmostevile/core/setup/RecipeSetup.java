package genelectrovise.magiksmostevile.core.setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class RecipeSetup {
  
  private static final Logger LOGGER = LogManager.getLogger();

  public static void editPreExistingItems(FMLCommonSetupEvent event) {
    LOGGER.info("Editing pre-existing items... Going incognito...");

    // Make an iron sword return an iron sword when placed into a crafting grid.
    ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.IRON_SWORD, Items.IRON_SWORD, "containerItem");
    LOGGER.debug("New containerItem injected for Items.IRON_SWORD");
  }
}
