package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Climber;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class Climb extends MotorConstant {
	public Climb() {
		super(RobotMap.Component.climber, Climber.CLIMBER_TARGET_SPEED);
	}
}
