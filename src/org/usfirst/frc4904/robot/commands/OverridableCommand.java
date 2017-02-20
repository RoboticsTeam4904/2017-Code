package org.usfirst.frc4904.robot.commands;


/**
 * 
 * @author niksure
 *         This interface allows commands to easily override other commands
 */
public interface OverridableCommand {
	/**
	 * sets an override to an static boolean you give it
	 * 
	 * @param override
	 */
	void setOverride(boolean setValue);

	/**
	 * returns if override is on or off
	 * 
	 * @return
	 */
	boolean getOverride();

	/**
	 * opposite of getOverride
	 * 
	 * @return
	 */
	boolean getNotOverride();
}
