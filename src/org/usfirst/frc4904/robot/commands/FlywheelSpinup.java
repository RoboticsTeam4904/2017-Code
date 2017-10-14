package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

/**
 * Sets Flywheel speed to constant SHOOTING_SPEED
 **/

public class FlywheelSpinup extends MotorSet {
	public FlywheelSpinup() {
		super(RobotMap.Component.flywheel);
	}

	@Override
	protected void execute() {
		set(Flywheel.SHOOTING_SPEED);
		super.execute();
	}
}
