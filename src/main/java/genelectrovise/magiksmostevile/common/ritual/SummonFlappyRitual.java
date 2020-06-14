/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.ritual.result.SummonFlappyResultHandler;

/**
 * @author GenElectrovise 13 Jun 2020
 */
public class SummonFlappyRitual extends Ritual {
	public static final String displayName = "Summon Flappy!";
	public static final String description = "Summon the almighty Flappy the Bat!";
	public static final String information = "Summons a vampire bat into the world!";
	private static final int energyRequirement = 50;

	/**
	 * @param registryName
	 * @param displayName
	 * @param description
	 */
	public SummonFlappyRitual() {
		super(displayName, description, information, energyRequirement);
	}
	
	@Override
	public ResultHandler<?> getResultHandler() {
		return new SummonFlappyResultHandler(getAltar(), this);
	}

}
