package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

	public boolean override;
public abstract class OverridableCommand extends Command implements Overridable {

	@Override
	public void setOverridden(boolean override) {
		this.override = override;
	}

	@Override
	public boolean isOverridden() {
		return override;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
