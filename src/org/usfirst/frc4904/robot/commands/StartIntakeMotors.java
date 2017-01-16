package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartIntakeMotors extends CommandGroup {
	
	public StartIntakeMotors() {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.Component.ballIO);
		addParallel(MotorConstant(RobotMap.Component.ballIO.topMotor, 1.0));
		addParallel(MotorConstant(RobotMap.Component.ballIO.leftMotor, -1.0));
		addParallel(MotorConstant(RobotMap.Component.ballIO.mainMotor, 1.0));
	}
}
