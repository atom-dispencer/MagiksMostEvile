/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.ArrayList;

import com.mojang.blaze3d.systems.RenderSystem;

import genelectrovise.magiksmostevile.common.main.reference.GuiReference;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import genelectrovise.magiksmostevile.common.ritual.Rituals;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainerScreen extends ContainerScreen<AltarContainer> {
	private AltarContainer altarContainer;

	protected ArrayList<Advancement> completetedRitualAdvancements = new ArrayList<Advancement>();
	protected ArrayList<Ritual> castableRituals = new ArrayList<Ritual>();

	private final int ZERO = 0;

	private final int MAIN_WIDTH = 356;
	private final int MAIN_HEIGHT = 179;

	/**
	 * @param altarContainer
	 * @param inv
	 * @param titleIn
	 */
	public AltarContainerScreen(AltarContainer altarContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(altarContainer, inv, titleIn);
		this.altarContainer = altarContainer;
		this.completetedRitualAdvancements = this.altarContainer.completedAdvancements;
	}
	
	

	// Logic
	private ArrayList<Ritual> createAvailableRitualsList() {
		ArrayList<Ritual> rituals = new ArrayList<Ritual>();

		try {
			for (Advancement adv : completetedRitualAdvancements) {
				ITextComponent itcName = adv.getDisplay().getTitle();
				String name = itcName.getString();

				rituals.add(Rituals.ALL.get(name).newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rituals;
	}

	// Drawing

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
		int posX_main = halfWidth - (MAIN_WIDTH / 2);

		int scaledHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
		int halfHeight = scaledHeight / 2;
		int posY_main = halfHeight - (MAIN_HEIGHT / 2);

		// Draw images
		drawMain(posX_main, posY_main);

		// Draw text
		this.font.drawString(this.title.getFormattedText() + " Amethyst Flux: " + altarContainer.currentAmethystFlux.get() + "/" + altarContainer.maxAmethystFlux.get(), 72.0F, 46.0F, 4210752);
	}

	private void drawMain(int posX, int posY) {
		getMinecraft().getTextureManager().bindTexture(GuiReference.Altar.MAIN);

		blit(posX, posY, 0, ZERO, ZERO, MAIN_WIDTH, MAIN_HEIGHT, MAIN_HEIGHT, MAIN_WIDTH);
	}

}
