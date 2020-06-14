package genelectrovise.magiksmostevile.common.ritual.result;

import genelectrovise.magiksmostevile.common.ritual.ConvertAmethystRitual;
import genelectrovise.magiksmostevile.common.ritual.ResultHandler;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;

public class ConvertAmethystResultHandler extends ResultHandler<ConvertAmethystRitual> {

	/**
	 * @param altar
	 * @param ritual
	 */
	public ConvertAmethystResultHandler(AltarTileEntity altar, ConvertAmethystRitual ritual) {
		super(altar, ritual);
	}

	@Override
	public void handleSuccess() {

	}
	
	@Override
	public void handleCasting() {
		
	}

	@Override
	public void handleFailure() {

	}

	@Override
	public void handleCataclysm() {

	}

}