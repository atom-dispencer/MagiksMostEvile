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
package genelectrovise.magiksmostevile.world.ore;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.world.noisyore.NoisyOreFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.FORGE)
public class OreFeatureAdditonManager {

    /**
     * Apply ores to {@link Biome}s of
     * their various {@link Biome.Category}.
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerOres(final BiomeLoadingEvent event) {
        MagiksMostEvile.LOGGER.debug("Adding MME ores to biome: " + event.getName());

        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        switch (event.getCategory()) {
            case NETHER:
                for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : OreFeatures.NETHER_ORES) {
                    registerTo(generation, simpleConfiguredOreFeature);
                }
                break;

            case THEEND:
                for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : OreFeatures.END_ORES) {
                    registerTo(generation, simpleConfiguredOreFeature);
                }
                break;

            default:
                for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : OreFeatures.OVERWORLD_ORES) {
                    registerTo(generation, simpleConfiguredOreFeature);
                }
                break;
        }

        registerTo(generation, NoisyOreFeature.CONFIGURED_ORE);

    }

    /**
     * Convenience method to tidy up {@link #registerOres(BiomeLoadingEvent)} by abstracting away
     * {@link BiomeGenerationSettingsBuilder} calls.
     *
     */
    private static void registerTo(BiomeGenerationSettingsBuilder builder,
                                   ConfiguredFeature<?, ?> feature) {

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
    }
}
