package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public abstract class OverridableCommand extends Command implements Overridable {
	private volatile boolean isOverridden;

	/**
	 * Set whether this command is overridden.
	 * 
	 * @param isOverridden
	 *        Whether to override the command or not
	 */
	@Override
	final public synchronized void setOverridden(boolean isOverridden) {
		this.isOverridden = isOverridden;
	}

	/**
	 * Get whether this command is overridden.
	 * 
	 * @returns Whether this command is overridden.
	 */
	@Override
	final public synchronized boolean isOverridden() {
		return isOverridden;
	}
}
