/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.registry;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.KittyTheKrakenRenderer;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile.SquidMissileRenderer;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatRenderer;
import genelectrovise.magiksmostevile.registry.orbital.EntityOrbitalRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author GenElectrovise 12 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class RendererRegistry {

    @SubscribeEvent
    public static void renderers(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityOrbitalRegistry.VAMPIRE_BAT.get(), VampireBatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOrbitalRegistry.THE_KRAKEN.get(), KittyTheKrakenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOrbitalRegistry.SQUID_MISSILE.get(), SquidMissileRenderer::new);
    }
}
