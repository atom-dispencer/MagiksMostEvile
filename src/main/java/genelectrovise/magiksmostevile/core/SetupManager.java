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
package genelectrovise.magiksmostevile.core;

import genelectrovise.magiksmostevile.entity.boss.egg_capone.EggCaponeEntity;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.KittyTheKrakenEntity;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile.SquidMissileEntity;
import genelectrovise.magiksmostevile.entity.boss.tinder_and_cinder.TinderAndCinderEntity;
import genelectrovise.magiksmostevile.entity.boss.tom_the_troll.TomTheTrollEntity;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatEntity;
import genelectrovise.magiksmostevile.registry.orbital.registries.EntityOrbitalRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

public final class SetupManager {

  @SuppressWarnings("deprecation")
  public static final Runnable[] tasks = {

      // Setup entity attributes
      () -> {
        MagiksMostEvile.LOGGER.debug("Registering MME entity attributes");

        // Vampire Bat
        GlobalEntityTypeAttributes.put(EntityOrbitalRegistry.VAMPIRE_BAT.get(),
            VampireBatEntity.getEntityAttributes().build());

        // Egg Capone
        GlobalEntityTypeAttributes.put(EntityOrbitalRegistry.EGG_CAPONE.get(),
            EggCaponeEntity.getEntityAttributes().build());

        // The Kraken
        GlobalEntityTypeAttributes.put(EntityOrbitalRegistry.THE_KRAKEN.get(),
            KittyTheKrakenEntity.getEntityAttributes().build());

        // Tinder and Cinder
        GlobalEntityTypeAttributes.put(EntityOrbitalRegistry.TINDER_AND_CINDER.get(),
            TinderAndCinderEntity.getEntityAttributes().build());

        // Tom the Troll
        GlobalEntityTypeAttributes.put(EntityOrbitalRegistry.TOM_THE_TROLL.get(),
            TomTheTrollEntity.getEntityAttributes().build());

        // Squid Missile
        GlobalEntityTypeAttributes.put(EntityOrbitalRegistry.SQUID_MISSILE.get(),
            SquidMissileEntity.getEntityAttributes().build());
      }

      //
  };

  @SubscribeEvent
  public static void setup(ParallelDispatchEvent event) {

    MagiksMostEvile.LOGGER.debug("SetupManager#setup() called for MME");

    for (Runnable runnable : tasks) {
      event.enqueueWork(runnable);
    }

  }
}
