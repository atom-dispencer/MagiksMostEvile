package genelectrovise.magiksmostevile.common.data;

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

//@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class ModdedTags {

  public static ITag<Block> AMETHYST_ORE_SPAWNABLE;

  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void makeBlockTags(FMLCommonSetupEvent event) {
    AMETHYST_ORE_SPAWNABLE = BlockTags.makeWrapperTag(
        new ResourceLocation(MagiksMostEvile.MODID, "amethyst_ore_spawnable").toString());

    Blocks.END_STONE.getTags()
        .add(new ResourceLocation(MagiksMostEvile.MODID, "amethyst_ore_spawnable"));
  }
}
