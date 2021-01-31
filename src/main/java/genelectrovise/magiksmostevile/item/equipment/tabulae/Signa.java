package genelectrovise.magiksmostevile.item.equipment.tabulae;

import java.util.function.Supplier;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.item.equipment.tabulae.Signum.EnumSignumMaterialType;
import genelectrovise.magiksmostevile.registry.orbital.registries.ItemOrbitalRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Signa {
  public static final BiMap<ResourceLocation, Signum> SIGNA = HashBiMap.create();
  
  public static Signum register(ResourceLocation name, EnumSignumMaterialType type, Supplier<Item> item) {
    return SIGNA.putIfAbsent(name, new Signum(name, type, item));
  }

  public static final Signum BLANK = register(new ResourceLocation(MagiksMostEvile.MODID, "blank_signum"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.BLANK_SIGNUM);
  public static final Signum NATURES_KIN = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_natures_kin"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_NATURES_KIN);
  public static final Signum SOUL = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_soul"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_SOUL);
  public static final Signum MUDDIED_DUST = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_muddied_dust"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_MUDDIED_DUST);
  public static final Signum SHAKEN_STONE = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_shaken_stone"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_SHAKEN_STONE);
  public static final Signum REACHING_GAZE = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_reaching_gaze"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_REACHING_GAZE);
  public static final Signum SPRING_BREEZE = register(new ResourceLocation(MagiksMostEvile.MODID, "signum_of_the_spring_breeze"), EnumSignumMaterialType.WOOD, ItemOrbitalRegistry.SIGNUM_OF_THE_SPRING_BREEZE);
}
