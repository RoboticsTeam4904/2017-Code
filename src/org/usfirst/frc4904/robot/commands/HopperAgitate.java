package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.Command;

public class HopperAgitate extends Command {
	protected long lastSwitchTime;

	public HopperAgitate() {
		requires(RobotMap.Component.hopper);
	}

	@Override
	protected void execute() {
		if (System.currentTimeMillis() - lastSwitchTime > Hopper.AGITATE_DELAY_MS) {
			if (RobotMap.Component.hopper.getState() == Hopper.HopperState.BALLIO) {
				RobotMap.Component.hopper.setState(Hopper.HopperState.SHOOTER);
			} else {
				RobotMap.Component.hopper.setState(Hopper.HopperState.BALLIO);
			}
			lastSwitchTime = System.currentTimeMillis();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}