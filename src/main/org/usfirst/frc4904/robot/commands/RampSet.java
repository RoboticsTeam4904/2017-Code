package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO.RampState;
import edu.wpi.first.wpilibj.command.Command;

public class RampSet extends Command {
	protected final RampState state;

	public RampSet(RampState state) {
		this.state = state;
		requires(RobotMap.Component.gearIO.ramp);
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
