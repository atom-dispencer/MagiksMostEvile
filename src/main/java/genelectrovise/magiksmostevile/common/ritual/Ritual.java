/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarContainerScreen;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * No longer abstract! Defines the base properties of a {@link Ritual}, but
 * should not be instantiated itself!
 * 
 * @author GenElectrovise 19 May 2020
 */
public class Ritual extends ForgeRegistryEntry<Ritual> {
	protected String displayName;
	protected String description;
	protected AltarTileEntity altar;
	protected boolean done;
	protected int tick;

	@Override
	public String toString() {
		return "Ritual{" + getRegistryName() + "}";
	}

	/**
	 * Takes some parameters to set the display variables for use in the
	 * {@link AltarContainerScreen}. Should <b><i>not</i></b> do anything critical
	 * to the {@link Ritual} itself, as that should be done in the {@link #begin()}
	 * method. This is only used for registering the {@link Ritual} in the
	 * {@link EvileDeferredRegistry}.
	 */
	public Ritual(String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}

	/**
	 * Starts casting the ritual. Shouldn't really <i>do</i> a lot, as that should
	 * take place in the {@link #tick()} method, but is perfect for preliminary
	 * setup!
	 */
	public void begin() {
		setDone(false);
		altar.setCasting(true);
		altar.currentRitual = this;
	}

	/**
	 * Ends the {@link Ritual} and ties up loose ends.
	 */
	public void end() {
		setDone(true);
		altar.setCasting(false);
		altar.currentRitual = null;
	}

	/**
	 * Equivalent of a constructor, where one isn't easy to implement.
	 * 
	 * @param altarTileEntity
	 */
	public void init(AltarTileEntity altarTileEntity) {
		this.altar = altarTileEntity;
	}

	/**
	 * Attempts to start the ritual. Should do some preliminary checks here, then
	 * call <code>super.tryStart()</code> to run
	 * <code>if(this.canBegin){this.begin}</code>, if no custom implementation is
	 * needed.
	 */
	public void tryStart() {
		if (this.canStart()) {
			this.begin();
		}
	}

	/**
	 * @return Whether the ritual is able to start.
	 */
	protected boolean canStart() {
		return true;
	}

	/**
	 * Ticks the next tick of this {@link Ritual} during casting. Called from
	 * {@link AltarTileEntity}#{@link #tick()}
	 */
	protected void tick() {
		if (isDone()) {
			end();
		}
	}

	/**
	 * @return Whether this {@link Ritual} should be ticked on this calling.
	 */
	protected boolean shouldtick() {
		if (isDone()) {
			return false;
		}

		return true;
	}

	/**
	 * The outward facing "ticking" method. Inner methods are encapsulated for
	 * greater simplicity! Should tick this {@link Ritual} if it should be ticked.
	 * If this {@link Ritual} should not be ticked, the tick variable is not
	 * incremented, i.e. the {@link Ritual} is frozen.
	 */
	public void nextCycle() {
		if (shouldtick()) {
			tick();
			tick++;
		}
	}

	public boolean isBetweenTicks(int min, int max) {
		return isBetweenTicks(min, max, false);
	}

	public boolean isBetweenTicks(int min, int max, boolean inclusive) {
		if (inclusive) {
			return (getTick() >= min) && (getTick() <= max);
		}

		return (getTick() > min) && (getTick() < max);
	}

	// Get and set

	/**
	 * @return The name
	 */
	public final String getDisplayName() {
		return displayName;
	}

	/**
	 * @return The description of the ritual
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @return the altar
	 */
	public final AltarTileEntity getAltar() {
		return altar;
	}

	/**
	 * @param done the done to set
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * @return the tick
	 */
	public int getTick() {
		return tick;
	}

	/**
	 * @param tick the tick to set
	 */
	public void setTick(int tick) {
		this.tick = tick;
	}
}
