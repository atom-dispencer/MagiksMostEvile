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
package genelectrovise.magiksmostevile.world.gen.ore;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.data.tag.ModdedTags;
import genelectrovise.magiksmostevile.registry.orbital.registries.BlockOrbitalRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class OreFeatures {

  public static ConfiguredFeature<?, ?> AMETHYST_ORE_OVERWORLD;
  public static ConfiguredFeature<?, ?> AMETHYST_ORE_NETHER;
  public static ConfiguredFeature<?, ?> AMETHYST_ORE_END;
  public static ConfiguredFeature<?, ?> LEAD_ORE_OVERWORLD;
  public static ConfiguredFeature<?, ?> LEAD_ORE_NETHER;
  public static ConfiguredFeature<?, ?> LEAD_ORE_END;

  public static ConfiguredFeature<?, ?>[] OVERWORLD_ORES;
  public static ConfiguredFeature<?, ?>[] NETHER_ORES;
  public static ConfiguredFeature<?, ?>[] END_ORES;

  /**
   * Definitions:
   * <ul>
   * <li><code>DepthAverageConfig(baseline, spread)</code> == "Most common on baseline, forming a bell
   * curve of frequency to a range (each side?) of the spread"
   * <li><code>.func_242731_b(2)</code> == "spawn attempts per chunk"?
   * </ul>
   * 
   * @see net.minecraft.world.gen.feature.Features
   * @param event
   */
  @SubscribeEvent
  public static void createConfiguredOreFeatures(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("Creating MME configured ore features");

    // Amethyst Ore Overworld
    AMETHYST_ORE_OVERWORLD = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "amethyst_ore_overworld_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.AMETHYST_ORE_SPAWNABLE), //
                BlockOrbitalRegistry.AMETHYST_ORE_OVERWORLD.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(32, 32))) //
            .func_242731_b(1)); //

    // Amethyst Ore Nether
    AMETHYST_ORE_NETHER = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "amethyst_ore_nether_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.AMETHYST_ORE_SPAWNABLE), //
                BlockOrbitalRegistry.AMETHYST_ORE_NETHER.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(32, 32))//
                .func_242731_b(2)) //
    ); //

    // Amethyst Ore End
    AMETHYST_ORE_END = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "amethyst_ore_end_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.AMETHYST_ORE_SPAWNABLE), //
                BlockOrbitalRegistry.AMETHYST_ORE_END.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(40, 20))//
                .func_242731_b(4)) //
    ); //

    // Lead Ore Overworld
    LEAD_ORE_OVERWORLD = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "lead_ore_overworld_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.LEAD_ORE_SPAWNABLE), //
                BlockOrbitalRegistry.LEAD_ORE_OVERWORLD.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(40, 32))//
                .func_242731_b(2)) //
    ); //

    // Lead Ore Nether
    LEAD_ORE_NETHER = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "lead_ore_overworld_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.LEAD_ORE_SPAWNABLE), //
                BlockOrbitalRegistry.LEAD_ORE_NETHER.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(65, 20))//
                .func_242731_b(3)) //
    ); //

    // Lead Ore End
    LEAD_ORE_END = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "lead_ore_overworld_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.LEAD_ORE_SPAWNABLE), //
                BlockOrbitalRegistry.LEAD_ORE_END.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(40, 32))//
                .func_242731_b(1)) //
    ); //

    applyToLists();
  }

  private static void applyToLists() {
    OVERWORLD_ORES = new ConfiguredFeature<?, ?>[] {AMETHYST_ORE_OVERWORLD, LEAD_ORE_OVERWORLD};
    NETHER_ORES = new ConfiguredFeature<?, ?>[] {AMETHYST_ORE_NETHER, LEAD_ORE_NETHER};
    END_ORES = new ConfiguredFeature<?, ?>[] {AMETHYST_ORE_END, LEAD_ORE_END};
  }
}
