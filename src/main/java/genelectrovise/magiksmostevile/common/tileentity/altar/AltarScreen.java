/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import com.mojang.blaze3d.systems.RenderSystem;

import genelectrovise.magiksmostevile.common.main.reference.GuiReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarScreen extends ContainerScreen<AltarContainer> {

	private final int ZERO = 0;
	//private final int PLAYER_INVENTORY_VERTICAL_WIDTH = 90;
	//private final int PLAYER_INVENTORY_VERTICAL_HEIGHT = 175;
	//private final int MAIN_WIDTH = 255;
	//private final int MAIN_HEIGHT = 179;
	
	private final int MAIN_WIDTH = 356;
	private final int MAIN_HEIGHT = 179;

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

	}

	// 9 ARG blit()
	// x gui right
	// y gui down
	// z position (blitOffSet)
	// x position on the texture image to draw from
	// y position on the texture image to draw from
	// x viewport size up
	// y viewport size up
	// y image size (will stretch image to fit)
	// x image size (will scale image to fit)

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// Flush the colour buffer
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

		// Find dimensions
		int scaledWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
		int halfWidth = scaledWidth / 2;
		//int posX_main = halfWidth - ((MAIN_WIDTH + PLAYER_INVENTORY_VERTICAL_WIDTH) / 2);
		int posX_main = halfWidth - (MAIN_WIDTH / 2);

		int scaledHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
		int halfHeight = scaledHeight / 2;
		int posY_main = halfHeight - (MAIN_HEIGHT / 2);

		// Draw images
		drawMain(posX_main, posY_main);
		//drawPlayerInventory(posX_main, posY_main);

		// Draw text
		this.font.drawString(this.title.getFormattedText(), 72.0F, 46.0F, 4210752);
	}

	/**
	 * @param adjustedX
	 * 
	 */
	private void drawPlayerInventory(int posX_main, int posY_main) {
		/*getMinecraft().getTextureManager().bindTexture(GuiReference.Altar.PLAYER_INVENTORY_VERTICAL);

		
		int distanceBuffer = 5;
		int posY = posY_main + ((MAIN_HEIGHT - PLAYER_INVENTORY_VERTICAL_HEIGHT) / 2);

		blit(posX_main + distanceBuffer + MAIN_WIDTH, posY, 0, ZERO, ZERO, PLAYER_INVENTORY_VERTICAL_WIDTH, PLAYER_INVENTORY_VERTICAL_HEIGHT, PLAYER_INVENTORY_VERTICAL_HEIGHT, PLAYER_INVENTORY_VERTICAL_WIDTH);
	*/}

	private void drawMain(int posX, int posY) {
		getMinecraft().getTextureManager().bindTexture(GuiReference.Altar.MAIN);

		blit(posX, posY, 0, ZERO, ZERO, MAIN_WIDTH, MAIN_HEIGHT, MAIN_HEIGHT, MAIN_WIDTH);
	}

}
