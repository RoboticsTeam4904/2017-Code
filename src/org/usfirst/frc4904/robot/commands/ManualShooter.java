package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class ManualShooter extends MotorConstant {
	public static int shooterSlider = 0;

	public ManualShooter() {
		super(RobotMap.Component.flywheel, RobotMap.Component.teensyStick.getAxis(ManualShooter.shooterSlider));
	}
}
