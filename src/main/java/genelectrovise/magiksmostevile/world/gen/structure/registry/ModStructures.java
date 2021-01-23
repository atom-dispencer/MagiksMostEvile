package genelectrovise.magiksmostevile.world.gen.structure.registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IForgeRegistry;

public class ModStructures {

  public static final HashMap<Structure<?>, StructureSeparationSettings> SEPERATION_SETTINGS = new HashMap<>();

  public static final Structure<OvergroundShrineFeatureConfig> OVERGROUND_SHRINE =
      new OvergroundShrineStructure();

  public static void registerStructures(Register<Structure<?>> event) {
    register(event.getRegistry(), OVERGROUND_SHRINE, "overground_shrine");

    // spacing, seperation, salt
    setupStructure(OVERGROUND_SHRINE, new StructureSeparationSettings(3, 2, 1234567890));

    StructurePieces.registerAllPieces();
  }

  public static <F extends Structure<?>> void setupStructure(F structure, StructureSeparationSettings settings) {

    MagiksMostEvile.LOGGER.debug("Structure setup block starting for structure " + (structure != null ? structure.getRegistryName().toString() : "(name null)"));
    
    SEPERATION_SETTINGS.put(structure, settings);

    // I dont like this bit :(
    try {

      // Add to the name map
      Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

      // Place into the structure seperation settings map
      try {
        Field mapField = ObfuscationReflectionHelper.findField(DimensionStructuresSettings.class, "field_236191_b_");
        makeUniversallyAccessible(mapField);
        ImmutableMap<Structure<?>, StructureSeparationSettings> newSettings =
            ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                .putAll(DimensionStructuresSettings.field_236191_b_)
                .put(structure, SEPERATION_SETTINGS.get(structure))
                .build();
        mapField.set(DimensionStructuresSettings.class, newSettings);
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }

      // Inject into dimension settings
      try {
        // Settings
        Field settingsField = ObfuscationReflectionHelper.findField(DimensionSettings.class, "field_242740_q");
        makeUniversallyAccessible(settingsField);
        DimensionSettings originalSettings = (DimensionSettings) settingsField.get(DimensionSettings.class);
        MagiksMostEvile.LOGGER.debug("Able to access dimension settings");

        // Structures map
        DimensionStructuresSettings structuresSettings = originalSettings.getStructures();
        Field mapField = ObfuscationReflectionHelper.findField(DimensionStructuresSettings.class, "field_236193_d_");
        makeUniversallyAccessible(mapField);
        @SuppressWarnings("unchecked")
        Map<Structure<?>, StructureSeparationSettings> structureSettingsMap = (Map<Structure<?>, StructureSeparationSettings>) mapField.get(structuresSettings);
        structureSettingsMap.put(structure, SEPERATION_SETTINGS.get(structure));
        
        MagiksMostEvile.LOGGER.debug("Reached end of setting block successfully.");
      } catch (IllegalArgumentException e) {
        MagiksMostEvile.LOGGER.error("Illegal argument for retrieving value of map in the given structureSettings");
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        MagiksMostEvile.LOGGER.error("Access to structure settings prohibited!");
        e.printStackTrace();
      }

      // Errors
    } catch (Exception e) {
      MagiksMostEvile.LOGGER.error("Error whilst setting up structure " + structure != null ? structure.getRegistryName().toString() : "(name null)");
      e.printStackTrace();
    } finally {
      MagiksMostEvile.LOGGER.debug("Structure setup block ending (success unknown) for structure " + structure != null ? structure.getRegistryName().toString() : "(name null)");
    }

  }

  public static void makeUniversallyAccessible(Field field) {

    try {
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
      return;

      // Errors
    } catch (NoSuchFieldException f) {
      MagiksMostEvile.LOGGER.error("No such field 'modifiers' found in Field.class");
      f.printStackTrace();
    } catch (IllegalArgumentException e) {
      MagiksMostEvile.LOGGER.error("Illegal argument for Field.modifiers.setInt");
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      MagiksMostEvile.LOGGER.error("Read/write access to Field.modifiers prohibited");
      e.printStackTrace();
    }

    MagiksMostEvile.LOGGER.error("Unable to disable private/final modifiers of " + field);

  }

  public static void register(IForgeRegistry<Structure<?>> registry, Structure<?> entry,
      String registryKey) {

    entry.setRegistryName(new ResourceLocation(MagiksMostEvile.MODID, registryKey));
    registry.register(entry);
  }
}
