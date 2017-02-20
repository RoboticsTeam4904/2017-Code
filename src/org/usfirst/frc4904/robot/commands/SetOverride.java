package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class SetOverride extends Command {
	protected final boolean setValue;
	protected final OverridableCommand[] commands;

	public SetOverride(boolean setValue, OverridableCommand... commands) {
		this.setValue = setValue;
		this.commands = commands;
	}

	@Override
	protected void initialize() {
		for (OverridableCommand command : commands) {
			command.setOverride(setValue);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}