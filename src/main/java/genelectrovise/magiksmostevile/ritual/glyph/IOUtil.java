/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.ritual.glyph;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import org.lwjgl.BufferUtils;
import static org.lwjgl.BufferUtils.*;

/**
 * Taken from {@link IOUtil} in my other project, <a href="https://github.com/Volts-of-Doom">Volts
 * of Doom</a>
 * 
 * @author GenElectrovise 18 Jun 2020
 */
public final class IOUtil {

  private IOUtil() {

  }

  private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
    ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
    buffer.flip();
    newBuffer.put(buffer);
    return newBuffer;
  }

  /**
   * Reads the specified resource and returns the raw data as a ByteBuffer.
   *
   * @param resource the resource to read
   * @param bufferSize the initial buffer size
   *
   * @return the resource data
   *
   * @throws IOException if an IO error occurs
   */
  public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) {

    try {

      ByteBuffer buffer;

      Path path = Paths.get(resource);

      if (Files.isReadable(path)) {
        try (SeekableByteChannel fc = Files.newByteChannel(path)) {
          buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
          while (fc.read(buffer) != -1) {
            ;
          }
        }
      } else {
        try (InputStream source = IOUtil.class.getClassLoader().getResourceAsStream(resource);
            ReadableByteChannel rbc = Channels.newChannel(source)) {
          buffer = createByteBuffer(bufferSize);

          while (true) {
            int bytes = rbc.read(buffer);
            if (bytes == -1) {
              break;
            }
            if (buffer.remaining() == 0) {
              buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // 50%
            }
          }
        }
      }

      buffer.flip();
      return buffer;
    } catch (Exception e) {
      e.printStackTrace();
      return BufferUtils.createByteBuffer(256);
    }
  }
}
