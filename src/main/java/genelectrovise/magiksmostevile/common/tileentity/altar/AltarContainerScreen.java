/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import static genelectrovise.magiksmostevile.common.main.reference.GuiReference.ZERO;

import java.util.ArrayList;

import com.mojang.blaze3d.systems.RenderSystem;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.reference.GuiReference;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageToServer;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageToServer.ToggleDirection;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainerScreen extends ContainerScreen<AltarContainer> {
	private AltarContainer altarContainer;

	protected ArrayList<Advancement> completetedRitualAdvancements = new ArrayList<Advancement>();
	protected ArrayList<Ritual> castableRituals = new ArrayList<Ritual>();

	private int scaledWidth;
	private int halfWidth;
	private int posX_main;

	private int halfHeight;
	private int scaledHeight;
	private int posY_main;

	/**
	 * @param altarContainer
	 * @param inv
	 * @param titleIn
	 */
	public AltarContainerScreen(AltarContainer altarContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(altarContainer, inv, titleIn);
		this.altarContainer = altarContainer;
	}

	@Override
	public void init(Minecraft p_init_1_, int p_init_2_, int p_init_3_) {
		super.init(p_init_1_, p_init_2_, p_init_3_);

		evaluateDimensions();

		addCastButton(posX_main, posY_main);
		addArrowToggles(posX_main, posY_main);
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

		evaluateDimensions();

		// Draw images
		drawMain(posX_main, posY_main);

		// Draw text
		this.font.drawString(this.title.getFormattedText() + " Amethyst Flux: " + altarContainer.currentAmethystFlux.get() + "/" + altarContainer.maxAmethystFlux.get(), 72.0F, 46.0F, 4210752);
	}

	private void evaluateDimensions() {
		// Find dimensions
		scaledWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
		halfWidth = scaledWidth / 2;
		posX_main = halfWidth - (GuiReference.Altar.Main.MAIN_WIDTH / 2);

		scaledHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
		halfHeight = scaledHeight / 2;
		posY_main = halfHeight - (GuiReference.Altar.Main.MAIN_HEIGHT / 2);
	}

	private void drawMain(int posX, int posY) {
		getMinecraft().getTextureManager().bindTexture(GuiReference.Altar.Main.MAIN_TEXTURE);

		blit(posX, posY, 0, ZERO, ZERO, GuiReference.Altar.Main.MAIN_WIDTH, GuiReference.Altar.Main.MAIN_HEIGHT, GuiReference.Altar.Main.MAIN_HEIGHT, GuiReference.Altar.Main.MAIN_WIDTH);
	}

	private void addCastButton(int posX, int posY) {
		addButton(new ImageButton(posX + 9, posY + 72, GuiReference.Altar.Button.CAST_WIDTH, GuiReference.Altar.Button.CAST_HEIGHT, 0, 0, 0, GuiReference.Altar.Button.CAST_TEXTURE, (btn) -> {
			castButtonPressed();
			altarContainer.inv.player.closeScreen();
		}));

	}

	private void addArrowToggles(int posX, int posY) {
		// Left
		addButton(new ImageButton(posX + 9 + 10, posY + 72 + 10, GuiReference.Altar.Button.CAST_WIDTH, GuiReference.Altar.Button.CAST_HEIGHT, 0, 0, 0, GuiReference.Altar.Button.CAST_TEXTURE, (btn) -> {
			AltarNetworkingManager.CAltarCastButton.sendToServer(new AltarToggleButtonMessageToServer(ToggleDirection.LEFT));
		}));

		// Right
		addButton(new ImageButton(posX + 9 + 20, posY + 72 + 20, GuiReference.Altar.Button.CAST_WIDTH, GuiReference.Altar.Button.CAST_HEIGHT, 0, 0, 0, GuiReference.Altar.Button.CAST_TEXTURE, (btn) -> {
			AltarNetworkingManager.CAltarCastButton.sendToServer(new AltarToggleButtonMessageToServer(ToggleDirection.RIGHT));
		}));
	}

	private void castButtonPressed() {
		MagiksMostEvile.LOGGER.debug("Button pressed!");
	}

}
