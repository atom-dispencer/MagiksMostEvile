package com.magiksmostevile;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class AlterGui extends GuiScreen{
	
	int guiWidth = 188;
	int guiHeight = 121;
	
	int button1Width = 50;
	int button1Height = 13; 
	GuiButton button1;
	
	
	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		Minecraft mc = Minecraft.getMinecraft();
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		GL11.glColor4f(1, 1, 1, 1);
		drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation(Main.MODID, "textures/gui/altergui.png"));
		drawTexturedModalRect(guiX, guiY, 3, 2, guiWidth, guiHeight); //3 and 2 are the U and V coords of the main gui texture
		EvileLog.LogText(true, 1, "About to draw AlterGui text");
		fontRendererObj.drawString("Test", guiX + 50, guiY + 10, 0xAAAAAA);
		drawTexturedModalRect(guiX + 5, guiY + 5, 3, 126, button1Width, button1Height );       //BUTTON 1 DRAWING
		super.drawScreen(x, y, ticks);
	}
	
	@Override
	public void initGui() {
		int guiX = (width - guiHeight) / 2;
		int guiY = (height - guiHeight) / 2;
		buttonList.clear();
		buttonList.add(button1 = new GuiButton(0, guiX + 5, guiY + 5, 20, 10, "Test"));  //BUTTON 1 PARAMS         //31.04
		super.initGui();
	}

}

//https://youtu.be/AMI622wAR5k