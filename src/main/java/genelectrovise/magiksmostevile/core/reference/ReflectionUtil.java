package genelectrovise.magiksmostevile.core.reference;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;

public class ReflectionUtil {


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
}
