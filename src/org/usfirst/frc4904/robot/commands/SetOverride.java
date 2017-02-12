package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class SetOverride extends Command {
	public OverridableCommand command;
	public boolean setValue;

	public SetOverride(boolean setValue, OverridableCommand command) {
		this.setValue = setValue;
		this.command = command;
	}

	@Override
	protected void initialize() {
		command.setOverride(setValue);// makes command set its override to a bool
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
