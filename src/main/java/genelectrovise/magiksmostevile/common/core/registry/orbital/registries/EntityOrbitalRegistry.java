package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.common.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.common.entity.boss.egg_capone.EggCaponeEntity;
import genelectrovise.magiksmostevile.common.entity.boss.the_kraken.TheKrakenEntity;
import genelectrovise.magiksmostevile.common.entity.boss.tinder_and_cinder.TinderAndCinderEntity;
import genelectrovise.magiksmostevile.common.entity.boss.tom_the_troll.TomTheTrollEntity;
import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatEntity;
import genelectrovise.magiksmostevile.common.item.spawn_egg.VampireBatSpawnEgg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<EntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, MagiksMostEvile.MODID);

  // =========ENTITIES====================================================================================================================
  public static final RegistryObject<EntityType<VampireBatEntity>> VAMPIRE_BAT =
      ENTITIES.register("vampire_bat",
          () -> EntityType.Builder.create(VampireBatEntity::new, EntityClassification.MONSTER)
              .setTrackingRange(64).size(0.5f, 0.5f).build("vampire_bat"));
  public static final RegistryObject<Item> VAMPIRE_BAT_EGG =
      ItemOrbitalRegistry.ITEMS.register("vampire_bat_egg", () -> new VampireBatSpawnEgg(
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE).maxStackSize(64)));

  public static final RegistryObject<EntityType<TomTheTrollEntity>> TOM_THE_TROLL =
      ENTITIES.register("tom_the_troll",
          () -> EntityType.Builder.create(TomTheTrollEntity::new, EntityClassification.MONSTER)
              .setTrackingRange(64).size(0.5f, 0.5f).build("tom_the_troll"));

  public static final RegistryObject<EntityType<EggCaponeEntity>> EGG_CAPONE =
      ENTITIES.register("egg_capone",
          () -> EntityType.Builder.create(EggCaponeEntity::new, EntityClassification.MONSTER)
              .setTrackingRange(64).size(0.5f, 0.5f).build("egg_capone"));

  public static final RegistryObject<EntityType<TheKrakenEntity>> THE_KRAKEN =
      ENTITIES.register("the_kraken",
          () -> EntityType.Builder.create(TheKrakenEntity::new, EntityClassification.MONSTER)
              .setTrackingRange(64).size(1f, 2f).build("the_kraken"));

  public static final RegistryObject<EntityType<TinderAndCinderEntity>> TINDER_AND_CINDER =
      ENTITIES.register("tinder_and_cinder",
          () -> EntityType.Builder.create(TinderAndCinderEntity::new, EntityClassification.MONSTER)
              .immuneToFire().setTrackingRange(64).size(0.5f, 0.5f).build("tinder_and_cinder"));

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
