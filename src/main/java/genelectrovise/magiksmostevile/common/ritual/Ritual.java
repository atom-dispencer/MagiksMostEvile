/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarContainerScreen;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * No longer abstract! Defines the base properties of a {@link Ritual}, but
 * should not be instantiated itself!
 * 
 * @author GenElectrovise 19 May 2020
 */
public class Ritual extends ForgeRegistryEntry<Ritual> implements INBTSerializable<CompoundNBT> {
	public static final ResourceLocation NONE = new ResourceLocation(MagiksMostEvile.MODID, "none");
	
	protected final String displayName;
	protected final String description;
	protected final String information;
	protected final int energyRequirement;
	protected ResultHandler<?> resultHandler;

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
	 * 
	 * @param convertAmethystResultHandler
	 */
	public Ritual(String displayName, String description, String information, int energyRequirement) {
		this.displayName = displayName;
		this.description = description;
		this.information = information;
		this.energyRequirement = energyRequirement;
	}

	/**
	 * Starts casting the ritual. Shouldn't really <i>do</i> a lot, as that should
	 * take place in the {@link #tick()} method, but is perfect for preliminary
	 * setup!
	 */
	public void begin() {
		setTick((this.getTick() == 0) ? 0 : this.getTick());
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
		if (this.canStart() || this.getTick() != 0) {
			this.begin();
		}
	}

	/**
	 * @return Whether the ritual is able to start.
	 */
	protected boolean canStart() {

		if (!(altar.getEnergyStored() > energyRequirement / 2)) {
			return false;
		}

		return true;
	}

	/**
	 * Ticks the next tick of this {@link Ritual} during casting. Called from
	 * {@link AltarTileEntity}#{@link #tick()}
	 */
	protected RitualResult tick() {
		return RitualResult.FAILURE;
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
			getResultHandler().handle(tick());
			tick++;
		}

		if (isDone()) {
			end();
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

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();

		tag.putInt("tick", getTick());

		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		setTick(nbt.getInt("tick"));
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

	/**
	 * @param resultHandler the resultHandler to set
	 */
	public void setResultHandler(ResultHandler<?> resultHandler) {
		this.resultHandler = resultHandler;
	}

	/**
	 * @return the resultHandler
	 */
	public ResultHandler<?> getResultHandler() {
		return resultHandler;
	}

	/**
	 * @return the energyRequirement
	 */
	public final int getEnergyRequirement() {
		return energyRequirement;
	}

	/**
	 * @return the information
	 */
	public final String getInformation() {
		return information;
	}

	/**
	 * How can a {@link Ritual} end? <br>
	 * <br>
	 * <b>SUCCESS</b> - Where everything is happy and goes well (ish)<br>
	 * <b>FAILURE</b> - Where the ritual cannot start or fails happily<br>
	 * <b>CATACLYSM</b> - Where everything goes badly wrong, i.e. you ran out of
	 * amethyst flux
	 * 
	 * @author GenElectrovise 14 Jun 2020
	 */
	public static enum RitualResult {
		SUCCESS, CASTING, FAILURE, CATACLYSM;
	}
}
