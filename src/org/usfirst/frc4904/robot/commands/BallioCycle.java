package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class BallioCycle extends OverridableCommandGroup {
	public BallioCycle() {
		super("BallioCycle", RobotMap.Component.hopper);
		requires(RobotMap.Component.ballIO);
		addSequential(new BallioDoorSetOuttake());
		addSequentialUnlessOverridden(new HopperSetBallio());
		addParallel(new MotorConstant(RobotMap.Component.ballIO.directionalRoller, BallIO.DIRECTIONAL_ROLLER_INTAKE_SPEED));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller,
			BallIO.ELEVATOR_AND_INTAKE_ROLLER_FORWARD_SPEED));
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, BallIO.HOPPER_ROLLERS_FORWARD_SPEED));
		addSequentialUnlessOverridden(new HopperSetBallio());
	}
}
