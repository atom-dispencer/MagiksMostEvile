package genelectrovise.magiksmostevile.common.data;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class ModdedTags {

  public static Supplier<ITag<Block>> AMETHYST_ORE_SPAWNABLE;

  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void makeBlockTags(FMLCommonSetupEvent event) {

    // Amethyst Ore Spawnable
    AMETHYST_ORE_SPAWNABLE = new Supplier<ITag<Block>>() {
      @Override
      public ITag<Block> get() {
        return BlockTags.getCollection()
            .get(new ResourceLocation(MagiksMostEvile.MODID, "amethyst_ore_spawnable"));
      }
    };



    if (AMETHYST_ORE_SPAWNABLE != null)
      MagiksMostEvile.LOGGER.info(
          "Testing MME BlockTags: #magiksmostevile:amethyst_ore_spawnable contains END_STONE = "
              + (AMETHYST_ORE_SPAWNABLE.get() != null ? AMETHYST_ORE_SPAWNABLE.get().contains(Blocks.END_STONE) : false));
  }
}
