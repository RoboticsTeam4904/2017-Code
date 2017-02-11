package org.usfirst.frc4904.robot.commands;


/**
 * 
 * @author niksure
 *         This interface allows commands to easily override other commands
 */
public interface OverridableCommand {
	/**
	 * sets an internal override to an boolean you give it
	 * 
	 * @param override
	 */
	void setOverride(boolean override);

	/**
	 * returns if override is on or off
	 * 
	 * @return
	 */
	boolean getOverride();
}
