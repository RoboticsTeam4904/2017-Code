package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class SetOverride extends Command {
	public boolean override;
	public ShooterStart command;

	public SetOverride(boolean override, ShooterStart command) {
		this.override = override;
		this.command = command;
	}

	@Override
	protected void initialize() {
		command.setOverride(override);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
