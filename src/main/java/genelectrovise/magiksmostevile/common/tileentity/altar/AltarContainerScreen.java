/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import static genelectrovise.magiksmostevile.common.core.reference.GuiReference.ZERO;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.reference.GuiReference;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageToServer;
import genelectrovise.magiksmostevile.common.network.altar.arrow_toggles.AltarToggleButtonMessageToServer.ToggleDirection;
import genelectrovise.magiksmostevile.common.network.altar.cast_button.AltarCastButtonPressedMessageToServer;
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
  public AltarContainerScreen(AltarContainer altarContainer, PlayerInventory inv,
      ITextComponent titleIn) {
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
  protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {

    drawText(stack);
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
  protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX,
      int mouseY) {
    // Flush the colour buffer
    RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

    evaluateDimensions();

    // Draw images
    drawMain(stack, posX_main, posY_main);
  }

  private void drawText(MatrixStack stack) {

    float baseX = -80F;
    float baseY = 2F;
    float seperator = 3;

    // Used to use split = 100

    // Draw text
    this.font.drawString(
        stack, this.title.getString() + " - Amethyst Flux: "
            + altarContainer.currentAmethystFlux.get() + "/" + altarContainer.maxAmethystFlux.get(),
        baseX, baseY, 4210752);

    this.font.drawString(stack, "Cast", baseX + 3, baseY + 68, 4210752);

    String displayName = altarContainer.getSelector().getRitualSupplier().get().getDisplayName();
    this.font.drawString(stack, displayName, new Integer(Math.round(baseX)),
        new Integer(Math.round(baseY + seperator + 10)), 11024322);

    String description = altarContainer.getSelector().getRitualSupplier().get().getDescription();
    this.font.drawString(stack, description, new Integer(Math.round(baseX)),
        new Integer(Math.round(baseY + seperator + 20)), 6961030);

    String energyRequirement = "Required Energy: "
        + new Integer(altarContainer.getSelector().getRitualSupplier().get().getIchorRequirement())
            .toString();
    this.font.drawString(stack, energyRequirement, new Integer(Math.round(baseX + 2)),
        new Integer(Math.round(baseY + seperator + 81)), 13018111);
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

  private void drawMain(MatrixStack stack, int posX, int posY) {
    getMinecraft().getTextureManager().bindTexture(GuiReference.Altar.Main.MAIN_TEXTURE);

    blit(stack, posX, posY, 0, ZERO, ZERO, GuiReference.Altar.Main.MAIN_WIDTH,
        GuiReference.Altar.Main.MAIN_HEIGHT, GuiReference.Altar.Main.MAIN_HEIGHT,
        GuiReference.Altar.Main.MAIN_WIDTH);
  }

  private void addCastButton(int posX, int posY) {
    addButton(new ImageButton(posX + 9, posY + 72, GuiReference.Altar.CastButton.CAST_WIDTH,
        GuiReference.Altar.CastButton.CAST_HEIGHT, 0, 0, 0,
        GuiReference.Altar.CastButton.CAST_TEXTURE, (btn) -> {
          castButtonPressed();
          altarContainer.inv.player.closeScreen();
        }));

  }

  private void addArrowToggles(int posX, int posY) {

    int modX = posX + 9;
    int modY = posY + 72;

    // Left
    addButton(new ImageButton(modX + 32, modY, GuiReference.Altar.ToggleButtons.LEFT_WIDTH,
        GuiReference.Altar.ToggleButtons.LEFT_HEIGHT, GuiReference.Altar.ToggleButtons.LEFT_X,
        GuiReference.Altar.ToggleButtons.LEFT_Y, 0, GuiReference.Altar.ToggleButtons.TEXTURE,
        (btn) -> {
          AltarNetworkingManager.CAltarToggleButton
              .sendToServer(new AltarToggleButtonMessageToServer(ToggleDirection.LEFT,
                  altarContainer.getSelector().getLocation()));
        }));

    // Right
    addButton(new ImageButton(modX + 32 + 16, modY, GuiReference.Altar.ToggleButtons.RIGHT_WIDTH,
        GuiReference.Altar.ToggleButtons.RIGHT_HEIGHT, GuiReference.Altar.ToggleButtons.RIGHT_X,
        GuiReference.Altar.ToggleButtons.RIGHT_Y, 0, GuiReference.Altar.ToggleButtons.TEXTURE,
        (btn) -> {
          AltarNetworkingManager.CAltarToggleButton
              .sendToServer(new AltarToggleButtonMessageToServer(ToggleDirection.RIGHT,
                  altarContainer.getSelector().getLocation()));
        }));
  }

  private void castButtonPressed() {
    MagiksMostEvile.LOGGER.debug("Button pressed!");
    AltarNetworkingManager.CAltarCastButton.sendToServer(
        new AltarCastButtonPressedMessageToServer(altarContainer.getSelector().getLocation()));
  }

}
