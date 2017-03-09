package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO.RampState;

public class SetRampState extends OverridableCommand {
	protected final RampState state;
	public static boolean rampOverride;

	public SetRampState(RampState state) {
		this.state = state;
	}

	@Override
	protected void initialize() {
		override = SetRampState.rampOverride;
		RobotMap.Component.gearIO.setRampState(state);
	}
}
