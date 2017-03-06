package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.autonomous.strategies.AutonGearCenterPegTime;
import org.usfirst.frc4904.standard.custom.ChassisController;

public class WiggleApproach implements ChassisController {
	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return AutonGearCenterPegTime.WIGGLE_SPEED;
	}

	@Override
	public double getTurnSpeed() {
		return Math.sin(System.currentTimeMillis() * AutonGearCenterPegTime.WIGGLE_PERIOD)
			* AutonGearCenterPegTime.WIGGLE_AMPLITUDE;
	}
}
