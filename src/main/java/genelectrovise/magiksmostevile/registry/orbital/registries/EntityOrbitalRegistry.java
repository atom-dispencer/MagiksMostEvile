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
package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.entity.boss.egg_capone.EggCaponeEntity;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.KittyTheKrakenEntity;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile.SquidMissileEntity;
import genelectrovise.magiksmostevile.entity.boss.tinder_and_cinder.TinderAndCinderEntity;
import genelectrovise.magiksmostevile.entity.boss.tom_the_troll.TomTheTrollEntity;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatEntity;
import genelectrovise.magiksmostevile.item.spawn_egg.VampireBatSpawnEgg;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<EntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, MagiksMostEvile.MODID);

  // =========MOBS====================================================================================================================
  public static final RegistryObject<EntityType<VampireBatEntity>> VAMPIRE_BAT =
      ENTITIES.register("vampire_bat",
          () -> EntityType.Builder.create(VampireBatEntity::new, EntityClassification.MONSTER).setTrackingRange(64).size(0.5f, 0.5f)
              .build("vampire_bat"));
  public static final RegistryObject<Item> VAMPIRE_BAT_EGG =
      ItemOrbitalRegistry.ITEMS.register("vampire_bat_egg", () -> new VampireBatSpawnEgg(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(64)));

  // =========BOSSES====================================================================================================================
  public static final RegistryObject<EntityType<TomTheTrollEntity>> TOM_THE_TROLL =
      ENTITIES.register("tom_the_troll",
          () -> EntityType.Builder.create(TomTheTrollEntity::new, EntityClassification.MONSTER).setTrackingRange(64).size(0.5f, 0.5f)
              .build("tom_the_troll"));

  public static final RegistryObject<EntityType<EggCaponeEntity>> EGG_CAPONE =
      ENTITIES.register("egg_capone",
          () -> EntityType.Builder.create(EggCaponeEntity::new, EntityClassification.MONSTER).setTrackingRange(64).size(0.5f, 0.5f)
              .build("egg_capone"));

  public static final RegistryObject<EntityType<KittyTheKrakenEntity>> THE_KRAKEN =
      ENTITIES.register("kitty_the_kraken",
          () -> EntityType.Builder.create(KittyTheKrakenEntity::new, EntityClassification.MONSTER).setTrackingRange(64).size(6f, 8f)
              .build("kitty_the_kraken"));

  public static final RegistryObject<EntityType<TinderAndCinderEntity>> TINDER_AND_CINDER =
      ENTITIES.register("tinder_and_cinder",
          () -> EntityType.Builder.create(TinderAndCinderEntity::new, EntityClassification.MONSTER).immuneToFire().setTrackingRange(64).size(0.5f, 0.5f)
              .build("tinder_and_cinder"));

  // =========MISC====================================================================================================================
  public static final RegistryObject<EntityType<SquidMissileEntity>> SQUID_MISSILE =
      ENTITIES.register("squid_missile",
          () -> EntityType.Builder.<SquidMissileEntity>create(SquidMissileEntity::new, EntityClassification.MISC).setTrackingRange(80).size(1f, 1f).func_233608_b_(2) /* Update interval */
              .build("squid_missile"));

  @Override
  public int priority() {
    return 11;
  }

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(ENTITIES);
  }

  @Override
  public String name() {
    return "entities";
  }

}
