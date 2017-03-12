package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO.RampState;
import edu.wpi.first.wpilibj.command.Command;

public class SetRampState extends Command {
	protected final RampState state;

	public SetRampState(RampState state) {
		this.state = state;
	}

	@Override
	protected void initialize() {
		RobotMap.Component.gearIO.setRampState(state);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
