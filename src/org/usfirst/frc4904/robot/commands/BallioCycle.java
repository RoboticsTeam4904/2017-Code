package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallioCycle extends CommandGroup {
	public BallioCycle() {
		super("BallioCycle");
		requires(RobotMap.Component.ballIO);
		addSequential(new BallioDoorSetOuttake());
		addParallel(new MotorConstant(RobotMap.Component.ballIO.directionalRoller, 1.0));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller, 1.0));
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, 1.0));
	}
}
