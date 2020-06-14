package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.ritual.Ritual.RitualResult;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;

public abstract class ResultHandler<T extends Ritual> {

	private AltarTileEntity altar;
	private T ritual;

	public ResultHandler(AltarTileEntity altar, T ritual) {
		this.altar = altar;
		this.ritual = ritual;
	}

	public final AltarTileEntity getAltar() {
		return altar;
	}

	public final T getRitual() {
		return ritual;
	}

	public void handle(RitualResult type) {
		switch (type) {
		case SUCCESS:
			handleSuccess();
			break;
		case CASTING:
			break;
		case FAILURE:
			break;
		case CATACLYSM:
			break;
		default:
			break;
		}
	}

	/**
	 * Handles the {@link RitualResult#SUCCESS} result of the {@link Ritual#tick()}
	 * method, i.e. What should happen when the {@link Ritual} ends with success?
	 */
	public abstract void handleSuccess();

	/**
	 * Handles the {@link RitualResult#CASTING} result of the {@link Ritual#tick()}
	 * method. This is only really here as a cursory method, as during casting,
	 * logic should be handled in the {@link Ritual#tick()} method.
	 */
	public abstract void handleCasting();

	/**
	 * Handles the {@link RitualResult#FAILURE} result of the {@link Ritual#tick()}
	 * method. This is a <b>graceful</b> failure. You're looking for the explosions?
	 * Go to {@link #handleCataclysm()}...
	 */
	public abstract void handleFailure();

	/**
	 * Handles the {@link RitualResult#CATACLYSM} result of the
	 * {@link Ritual#tick()} method. This is what happens if the {@link Ritual} is
	 * interrupted, e.g. by running out of amethyst flux.
	 */
	public abstract void handleCataclysm();
}