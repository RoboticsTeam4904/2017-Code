package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public abstract class OverridableCommand extends Command implements Overridable {
	private volatile boolean isOverridden;

	@Override
	final public synchronized void setOverridden(boolean isOverridden) {
		this.isOverridden = isOverridden;
	}

	@Override
	final public synchronized boolean isOverridden() {
		return isOverridden;
	}
}
