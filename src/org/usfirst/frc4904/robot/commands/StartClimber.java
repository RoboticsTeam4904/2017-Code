package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class StartClimber extends MotorConstant {
	public StartClimber() {
		super(RobotMap.Component.climber, RobotMap.Constant.HumanInput.CLIMBER_TARGET_SPEED);
	}
}
