package genelectrovise.magiksmostevile.common.data;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class ModdedTags {

  public static final ITag<Block> AMETHYST_ORE_SPAWNABLE = BlockTags.makeWrapperTag(
      new ResourceLocation(MagiksMostEvile.MODID, "amethyst_ore_spawnable").toString());
}

