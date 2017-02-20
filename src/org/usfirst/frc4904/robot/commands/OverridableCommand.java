package org.usfirst.frc4904.robot.commands;


/**
 * @author niksure
 */
/**
 * 
 * This interface allows commands to easily override other commands
 *
 */
public interface OverridableCommand {
	/**
	 * sets a static override to a boolean you give it
	 * 
	 * @param override
	 */
	void setOverride(boolean setValue);

	/**
	 * returns if override is on or off
	 * 
	 * @return
	 */
	boolean isOverridden();

	/**
	 * opposite of getOverride
	 * 
	 * @return
	 */
	default boolean isNotOverridden() {
		return !isOverridden();
	}
}