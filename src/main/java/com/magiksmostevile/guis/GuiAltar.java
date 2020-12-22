package com.magiksmostevile.guis;

import com.magiksmostevile.Main;
import com.magiksmostevile.tileentity.TileEntityAltar;
import com.magiksmostevile.tileentity.container.ContainerAltar;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiAltar extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/altar/gui_altar.png");
	private final InventoryPlayer player;
	private final TileEntityAltar tileentity;

	public GuiAltar(InventoryPlayer player, TileEntityAltar tileentity) {
		super(new ContainerAltar(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		System.out.println("path to textures" + TEXTURES.getResourcePath());
		System.out.println("path to textures" + TEXTURES.getResourceDomain());
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		System.out.println("Drawing gui bg");
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		System.out.println("Drawing gui fg");
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 115, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 295, this.ySize - 96 + 2, 4210752);
	}

}

// https://www.minecraftforge.net/forum/topic/38014-19-solved-gui-doesnt-open-on-right-click/