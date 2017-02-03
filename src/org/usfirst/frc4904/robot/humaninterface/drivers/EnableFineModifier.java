package org.usfirst.frc4904.robot.humaninterface.drivers;


import edu.wpi.first.wpilibj.command.Command;

public class EnableFineModifier extends Command {
	private final FineModifier modifier;

	public EnableFineModifier(FineModifier modifier) {
		this.modifier = modifier;
	}

	@Override
	protected void initialize() {
		modifier.setFineControl(true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void interrupted() {
		modifier.setFineControl(false);
	}
}