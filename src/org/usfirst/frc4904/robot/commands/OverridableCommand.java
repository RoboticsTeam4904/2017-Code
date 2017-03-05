package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class OverridableCommand extends Command implements Overridable {
	public static boolean override;

	@Override
	public void setOverridden(boolean override) {
		OverridableCommand.override = override;
	}

	@Override
	public boolean isOverridden() {
		return OverridableCommand.override;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
