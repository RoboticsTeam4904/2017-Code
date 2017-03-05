package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class OverridableCommand extends Command implements Overridable {
	public boolean override;

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
