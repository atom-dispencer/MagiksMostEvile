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
package genelectrovise.magiksmostevile.core.reference;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
