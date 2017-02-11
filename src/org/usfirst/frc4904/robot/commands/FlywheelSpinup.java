package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class FlywheelSpinup extends MotorConstant {
	public FlywheelSpinup() {
		super(RobotMap.Component.flywheel, Flywheel.SHOOTING_SPEED);
	}

	public FlywheelSpinup(double speed) {
		super(RobotMap.Component.flywheel, speed);
	}
}
