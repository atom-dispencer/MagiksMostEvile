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
package genelectrovise.magiksmostevile.tileentity.inscription_table;

import com.mojang.blaze3d.matrix.MatrixStack;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.item.equipment.tabulae.Signa;
import genelectrovise.magiksmostevile.item.equipment.tabulae.Signum;
import genelectrovise.magiksmostevile.tileentity.ScreenHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static genelectrovise.magiksmostevile.tileentity.ScreenHelper.ZERO;

/**
 * {@link CraftingScreen}
 *
 * @author GenElectrovise
 */
public class InscriptionTableContainerScreen extends ContainerScreen<InscriptionTableContainer> {

    public ResourceLocation mainTexture = new ResourceLocation(MagiksMostEvile.MODID, "textures/container/inscription_table/inscription_table.png");
    private int mainHeight = 166;
    private int mainWidth = 176;

    private int signumTextureSideLength = 16;
    private int buttonSideLength = 16;

    private List<SignumButtonData> buttonDatas = new ArrayList<SignumButtonData>();

    public InscriptionTableContainerScreen(InscriptionTableContainer inscriptionTableContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(inscriptionTableContainer, playerInventory, title);

    }

    @Override
    public void init(Minecraft minecraft, int width, int height) {
        super.init(minecraft, width, height);

        createButtons();
        activateButtons();
    }

    public void createButtons() {

        int horizontalCount = 0;
        int verticalCount = 0;
        for (Signum signum : Signa.SIGNA.values()) {

            // If too tall, next column
            if (verticalCount >= 3) {
                horizontalCount++;
                verticalCount = 0;
            }

            int startX = 401;
            int startY = 188;

            SignumButtonData data = new SignumButtonData(new Point(startX + horizontalCount * 18, startY + verticalCount * 18), signum);
            buttonDatas.add(data);

            verticalCount++;
        }
    }

    private void activateButtons() {

        for (SignumButtonData data : buttonDatas) {

            String namespace = data.getSignum().getName().getNamespace();
            String path = "textures/items/tabulae/" + data.getSignum().getName().getPath() + ".png";

            ResourceLocation textureLocation = new ResourceLocation(namespace, path);

            ImageButton button = ScreenHelper.createImageButton(data.getPoint().x, data.getPoint().y, buttonSideLength, buttonSideLength, ZERO, ZERO, ZERO, textureLocation, signumTextureSideLength,
                    signumTextureSideLength,
                    (btn) -> {
                        menu.buttonPressed(data.getSignum().getName());
                    });

            addButton(button);
        }
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {

        int windowWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int windowHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int halfWidth = windowWidth / 2;
        int halfHeight = windowHeight / 2;

        int rootX = halfWidth - (mainWidth / 2);
        int rootY = halfHeight - (mainHeight / 2);

        // x on screen
        // y on screen
        // blitoffset
        // uOffset x on texture
        // vOffset y on texture
        // uWidth Width of tex
        // vHeight Height of tex
        // texWidth total tex height
        // texHeight total tex width

        // Main
        ScreenHelper.bind(this, mainTexture);
        ScreenHelper.blit_9(matrixStack, rootX, rootY, ZERO, ZERO, ZERO, 175, 165, 256, 256);


    }

}
