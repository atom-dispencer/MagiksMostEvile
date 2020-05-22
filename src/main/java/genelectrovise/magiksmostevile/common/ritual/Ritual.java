/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

/**
 * @author GenElectrovise
 * 19 May 2020
 */
public abstract class Ritual {
	String name;
	String description;
	
	/**
	 * 
	 */
	public Ritual(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
