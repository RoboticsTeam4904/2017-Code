package org.usfirst.frc4904.robot.commands;


/**
 * This interface allows commands to easily override other commands
 */
public interface Overridable {
	/**
	 * sets a static override to a boolean you give it
	 * 
	 * @param override
	 */
	void setOverridden(boolean setValue);

	/**
	 * returns if override is on or off
	 * 
	 * @return
	 */
	public boolean isOverridden();

	/**
	 * opposite of getOverride
	 * 
	 * @return
	 */
	default boolean isNotOverridden() {
		return !isOverridden();
	}
}