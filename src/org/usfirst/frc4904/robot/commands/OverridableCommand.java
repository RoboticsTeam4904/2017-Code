package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public abstract class OverridableCommand extends Command implements Overridable {
	private volatile boolean isOverridden;

	@Override
	public void setOverridden(boolean override) {
		this.isOverridden = isOverridden;
	}

	@Override
	public boolean isOverridden() {
		return isOverridden;
	}
}
