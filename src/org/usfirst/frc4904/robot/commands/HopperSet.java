package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.Command;

public class HopperSet extends Command {
	protected final Hopper.HopperState targetState;

	public HopperSet(Hopper.HopperState targetState) {
		requires(RobotMap.Component.hopper);
		this.targetState = targetState;
	}

	@Override
	protected void initialize() {
		RobotMap.Component.hopper.setState(targetState);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
