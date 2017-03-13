package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.standard.commands.OverridableCommandGroup;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class BallioFloorClear extends OverridableCommandGroup {
	public BallioFloorClear() {
		super("BallioFloorClear", RobotMap.Component.hopper);
		requires(RobotMap.Component.ballIO);
		addSequential(new BallioDoorSetIntake());
		addSequentialUnlessOverridden(new HopperSetBallio());
		addParallel(new MotorConstant(RobotMap.Component.ballIO.directionalRoller, BallIO.DIRECTIONAL_ROLLER_OUTTAKE_SPEED));
		addParallel(new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller,
			BallIO.ELEVATOR_AND_INTAKE_ROLLER_FORWARD_SPEED));
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, BallIO.HOPPER_ROLLERS_FORWARD_SPEED));
	}
}
