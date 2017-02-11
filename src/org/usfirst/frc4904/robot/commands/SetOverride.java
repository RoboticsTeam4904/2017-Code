package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class SetOverride extends Command {
	public boolean override;
	public boolean setValue;
	public OverridableCommand command;

	public SetOverride(boolean setValue, OverridableCommand command) {
		this.setValue = setValue;
		this.command = command;
	}

	@Override
	protected void initialize() {
		command.setOverride(setValue);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
