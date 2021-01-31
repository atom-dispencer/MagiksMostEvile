package genelectrovise.magiksmostevile.tileentity.inscription_table;

import net.minecraft.client.gui.ScreenManager.IScreenFactory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

/**
 * @author GenElectrovise 14 May 2020
 */
public class InscriptionTableScreenManager implements IScreenFactory<InscriptionTableContainer, InscriptionTableContainerScreen> {

  @Override
  public InscriptionTableContainerScreen create(InscriptionTableContainer inscriptionTable, PlayerInventory playerInventory, ITextComponent title) {
    return new InscriptionTableContainerScreen(inscriptionTable, playerInventory, title);
  }

}
