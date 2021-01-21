package genelectrovise.magiksmostevile.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.tileentity.altar.AltarBlock;
import genelectrovise.magiksmostevile.tileentity.amethyst_crystal.AmethystCrystalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, MagiksMostEvile.MODID);

  // =========BLOCKS======================================================================================================================
  public static final RegistryObject<Block> AMETHYST_BLOCK = BLOCKS.register("amethyst_block",
      () -> new Block(Block.Properties.create(Material.GLASS, MaterialColor.MAGENTA)
          .harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).setLightLevel((state) -> 5 / 16)
          .hardnessAndResistance(3F, 3F)));
  public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block",
      () -> new Block(Block.Properties.create(Material.IRON, MaterialColor.IRON)
          .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).hardnessAndResistance(5F, 6F)));

  // Amethyst Ore
  public static final RegistryObject<Block> AMETHYST_ORE_OVERWORLD =
      BLOCKS.register("amethyst_ore_overworld",
          () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE)
              .harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL).hardnessAndResistance(1.5F, 6F)
              .setRequiresTool()));
  public static final RegistryObject<Block> AMETHYST_ORE_NETHER =
      BLOCKS.register("amethyst_ore_nether",
          () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK)
              .harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL)
              .hardnessAndResistance(0.4F, 0.4F).setRequiresTool()));
  public static final RegistryObject<Block> AMETHYST_ORE_END =
      BLOCKS.register("amethyst_ore_end",
          () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND)
              .harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL).hardnessAndResistance(3F, 9F)
              .setRequiresTool()));

  // Lead Ore
  public static final RegistryObject<Block> LEAD_ORE_OVERWORLD =
      BLOCKS.register("lead_ore_overworld",
          () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE)
              .harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL).hardnessAndResistance(1.5F, 6F)
              .setRequiresTool()));
  public static final RegistryObject<Block> LEAD_ORE_NETHER = BLOCKS.register("lead_ore_nether",
      () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK)
          .harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL).hardnessAndResistance(0.4F, 0.4F)
          .setRequiresTool()));
  public static final RegistryObject<Block> LEAD_ORE_END =
      BLOCKS.register("lead_ore_end",
          () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND)
              .harvestTool(ToolType.PICKAXE).sound(SoundType.CORAL).hardnessAndResistance(3F, 9F)
              .setRequiresTool()));

  // Assorted
  public static final RegistryObject<Block> AMETHYST_CRYSTAL = BLOCKS.register("amethyst_crystal",
      () -> new AmethystCrystalBlock(Block.Properties.create(Material.GLASS, MaterialColor.MAGENTA)
          .harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).hardnessAndResistance(2F, 10F)));
  public static final RegistryObject<Block> ALTAR = BLOCKS.register("altar",
      () -> new AltarBlock(Block.Properties.create(Material.GLASS, MaterialColor.SAND)
          .harvestTool(ToolType.PICKAXE).sound(SoundType.CLOTH).hardnessAndResistance(6F, 10F)));

  @Override
  public int priority() {
    return 1;
  }

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(BLOCKS);
  }

  @Override
  public String name() {
    return "blocks";
  }

}
