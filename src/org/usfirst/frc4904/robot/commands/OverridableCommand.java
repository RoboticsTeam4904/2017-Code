package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public abstract class OverridableCommand extends Command implements Overridable {
	public boolean isOverridden;

	@Override
	public void setOverridden(boolean override) {
		this.isOverridden = isOverridden;
	}

	@Override
	public boolean isOverridden() {
	}

	@Override
	protected boolean isFinished() {
		return false;
		return isOverridden;
	}
}
