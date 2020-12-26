package genelectrovise.magiksmostevile.common.tileentity.altar;

import net.minecraft.client.gui.ScreenManager.IScreenFactory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarScreenManager implements IScreenFactory<AltarContainer, AltarContainerScreen> {

  @Override
  public AltarContainerScreen create(AltarContainer altarContainer, PlayerInventory playerInventory,
      ITextComponent title) {
    return new AltarContainerScreen(altarContainer, playerInventory, title);
  }

}
