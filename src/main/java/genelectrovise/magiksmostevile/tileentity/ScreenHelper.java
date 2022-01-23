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
package genelectrovise.magiksmostevile.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

public class ScreenHelper {

    public static final int ZERO = 0;

    public static void bind(Screen screen, ResourceLocation texture) {
        screen.getMinecraft().getTextureManager().bind(texture);
    }

    /**
     * An abstraction of the 9-argument
     * {@link AbstractGui#blit(MatrixStack, int, int, int, float, float, int, int, int, int)}
     * method.<br>
     * <br>
     * X coordinates are measured from the left of the screen. <br>
     * Y coordinates are measured from the top of the screen.
     *
     * @param stack         The stack to draw onto.
     * @param drawAtX       The x position to draw the object at.
     * @param drawAtY       The y position to draw the object at.
     * @param blitOffset    ?
     * @param uOffset
     * @param vOffset
     * @param uWidth        The width of the object to draw (defines the bounding box to draw to).
     * @param vHeight       The height of the object to draw (defines the bounding box to draw to).
     * @param textureWidth  The width of the texture (defines the bounding box to draw from).
     * @param textureHeight The height of the texture (defines the bounding box to draw from).
     */
    public static void blit_9(MatrixStack stack, int drawAtX, int drawAtY, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        AbstractGui.blit(stack, drawAtX, drawAtY, blitOffset, uOffset, vOffset, uWidth, vHeight, textureHeight, textureWidth);
    }

    /**
     * @param drawAtX           Where should the button be drawn?
     * @param drawAtY           Where should the button be drawn?
     * @param buttonDrawnWidth  How big is the button hitbox?
     * @param buttonDrawnHeight How big is the button hitbox?
     * @param textureStartsAtX  Where should the image start being drawn from (on the source texture)?
     * @param textureStartsAtY  Where should the image start being drawn from (on the source texture)?
     * @param textYDifference   ??
     * @param imageLocation     Where is the source texture?
     * @param textureXSize      How big is the source texture?
     * @param textureYSize      How big is the source texture?
     * @param onPressed         (button) -> {do something when pressed}
     * @return The constructed {@link ImageButton}
     */
    public static ImageButton createImageButton(int drawAtX, int drawAtY, int buttonDrawnWidth, int buttonDrawnHeight, int textureStartsAtX, int textureStartsAtY, int textYDifference,
                                                ResourceLocation imageLocation, int textureXSize, int textureYSize, IPressable onPressed) {
        return new ImageButton(drawAtX, drawAtY, buttonDrawnWidth, buttonDrawnHeight, textureStartsAtX, textureStartsAtY, textYDifference, imageLocation, textureXSize, textureYSize, onPressed);
    }
}
