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
package genelectrovise.magiksmostevile.ritual.glyph;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.particle.glyph.GlyphParticleData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.resources.IResource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Creates a floating image of particles.
 *
 * @author GenElectrovise 15 Jun 2020
 *
 * @see EnderPearlEntity
 * @see PortalParticle
 */
public class Glyph {
    private ResourceLocation imageLocation;
    private ImageProperties imageProperties;

    /**
     *
     * @param location ../src/main/resources/assets/magiksmostevile/ is added to the start
     */
    public Glyph(ResourceLocation location) {
        this.imageLocation = location;
        this.imageProperties = generateImageProperties(imageLocation);
    }

    private static Color[] getPixels(BufferedImage image) {

        try {
            Color[] result = new Color[image.getHeight() * image.getWidth()];
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {

                    Color c = new Color(image.getRGB(x, y), true);

                    result[(image.getWidth() * x) + y] = c;
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean drawCentered(World world, BlockPos pos, double spacing,
                                GlyphOrientation orientation) {

        if (!world.isClientSide) {
            return false;
        }

        BlockPos newPos = null;

        if (orientation == GlyphOrientation.HORIZONTAL) {
            double newX = pos.getX() - ((imageProperties.width * spacing) / 2);
            double newY = pos.getY();
            double newZ = pos.getZ() - ((imageProperties.height * spacing) / 2);

            newPos = new BlockPos(newX, newY, newZ);
        }

        if (orientation == GlyphOrientation.VERTICAL) {
            double newX = pos.getX() - ((imageProperties.width * spacing) / 2) + 1;
            double newY = pos.getY() - ((imageProperties.height * spacing) / 2);
            double newZ = pos.getZ();

            newPos = new BlockPos(newX, newY, newZ);
        }

        return draw(world, newPos, spacing, orientation, true);
    }

    public boolean draw(World world, BlockPos pos, double spacing, GlyphOrientation orientation) {
        return draw(world, pos, spacing, orientation, false);
    }

    private boolean draw(World world, BlockPos pos, double spacing, GlyphOrientation orientation,
                         boolean centered) {

        if (!world.isClientSide) {
            return false;
        }

        try {

            if (world.isClientSide) {
                // Iterate through the width and height
                for (int x = 0; x < imageProperties.width; x++) {
                    for (int y = 0; y < imageProperties.height; y++) {

                        Color tint = imageProperties.pixels[(imageProperties.width * x) + y];

                        MagiksMostEvile.LOGGER.debug("Adding glyph particle : " + x + " " + y + " " + tint);

                        GlyphParticleData data = new GlyphParticleData(tint, 5);

                        if (orientation == null) {
                            orientation = GlyphOrientation.VERTICAL;
                        }

                        double centerModifier = centered ? -0.75 : 0;

                        double posX = 0;
                        double posY = 0;
                        double posZ = 0;

                        if (orientation == GlyphOrientation.VERTICAL) {
                            posX =
                                    pos.getX() - (x * spacing) + (imageProperties.width * spacing) + centerModifier;
                            posY = pos.getY() - (y * spacing) + (imageProperties.height * spacing);
                            posZ = pos.getZ() + centerModifier;
                        } else if (orientation == GlyphOrientation.HORIZONTAL) {
                            posX =
                                    pos.getX() - (x * spacing) + (imageProperties.width * spacing) + centerModifier;
                            posY = pos.getY();
                            posZ =
                                    pos.getZ() - (y * spacing) + (imageProperties.height * spacing) + centerModifier;
                        }

                        world.addParticle(data, true, posX, posY, posZ, 0, 0, 0);
                    }
                }

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param resourceLocation
     * @returne
     */
    private ImageProperties generateImageProperties(ResourceLocation resourceLocation) {
        ImageProperties properties = new ImageProperties();

        try {
            IResource resource =
                    Minecraft.getInstance().getResourceManager().getResource(resourceLocation);

            InputStream stream = resource.getInputStream();

            BufferedImage image = ImageIO.read(stream);

            stream.close();

            properties.setWidth(image.getWidth());
            properties.setHeight(image.getHeight());
            properties.setPixels(getPixels(image));
            properties.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static enum GlyphOrientation {
        HORIZONTAL, VERTICAL;

        public static int toInt(GlyphOrientation orientation) {
            switch (orientation) {
                case HORIZONTAL:
                    return 0;
                case VERTICAL:
                    return 1;
                default:
                    return 0;
            }
        }

        public static GlyphOrientation fromInt(int i) {
            switch (i) {
                case 0:
                    return HORIZONTAL;
                case 1:
                    return VERTICAL;
                default:
                    return HORIZONTAL;
            }
        }
    }

    protected static class ImageProperties {
        private int width;
        private int height;
        private Color[] pixels;
        private BufferedImage image;

        public int getWidth() {
            return width;
        }

        // Width
        public void setWidth(int width) {
            this.width = width;
        }

        // Height
        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        // Pixels
        public Color[] getPixels() {
            return pixels;
        }

        public void setPixels(Color[] colors) {
            this.pixels = colors;
        }

        // Image
        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }
}
