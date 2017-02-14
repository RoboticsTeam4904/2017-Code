package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.Command;

public class HopperAgitate extends Command {
	protected final double AgitateDelay;
	protected long StartTime;

	public HopperAgitate() {
		requires(RobotMap.Component.hopper);
		AgitateDelay = Hopper.AGITATE_DELAY;

	}

	@Override
	protected void initialize() {
		StartTime = System.currentTimeMillis();
		RobotMap.Component.hopper.setState(Hopper.HopperState.BALLIO);
	}

	@Override
	protected void execute() {
		if (System.currentTimeMillis() - StartTime > AgitateDelay) {
			if (RobotMap.Component.hopper.getState() == Hopper.HopperState.BALLIO) {
				RobotMap.Component.hopper.setState(Hopper.HopperState.SHOOTER);
			} else {
				RobotMap.Component.hopper.setState(Hopper.HopperState.BALLIO);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}