/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import com.mojang.blaze3d.systems.RenderSystem;

import genelectrovise.magiksmostevile.common.main.Main;
import genelectrovise.magiksmostevile.common.main.reference.GuiReference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarScreen extends ContainerScreen<AltarContainer> {

	/**
	 * @param altarContainer
	 * @param inv
	 * @param titleIn
	 */
	public AltarScreen(AltarContainer altarContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(altarContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		Main.LOGGER.info("Drawing AltarScreen Foreground!");
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

		ResourceLocation location = GuiReference.GUI_ALTAR_LOCATION;
		getMinecraft().getTextureManager().bindTexture(GuiReference.GUI_ALTAR_LOCATION);

		//Viewport pos, ???, img width & height
		blit(0, 0, 43, 63, 258, 122); // Top left: 145 103 Bottom right: 404 281
		
		
		this.font.drawString(this.title.getFormattedText(), 12.0F, 5.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Main.LOGGER.info("Drawing AltarScreen Background!");
	}

}
