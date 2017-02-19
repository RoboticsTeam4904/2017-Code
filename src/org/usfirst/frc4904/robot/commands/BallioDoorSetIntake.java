package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.standard.commands.RunAllParallel;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunUnless;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallioDoorSetIntake extends CommandGroup {
	private static final double HOPPER_ROLLERS_CLEAR_SPEED = -0.5;
	private static final double ELEVATOR_CLEAR_SPEED = 0.5;
	private static final double SERVO_PATH_CLEAR_TIME = 0.25;

	public BallioDoorSetIntake() {
		Command hopperRollersDown = new MotorConstant(RobotMap.Component.ballIO.hopperRollers,
			BallioDoorSetIntake.HOPPER_ROLLERS_CLEAR_SPEED);
		Command elevatorsUp = new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller,
			BallioDoorSetIntake.ELEVATOR_CLEAR_SPEED);
		Command clearServoPath = new RunFor(new RunAllParallel(hopperRollersDown, elevatorsUp),
			BallioDoorSetIntake.SERVO_PATH_CLEAR_TIME);
		addSequential(new RunUnless(clearServoPath, this::isAlreadySetToIntake));
		addSequential(new BallioDoorSetUnsafely(BallIO.DoorState.INTAKE));
	}

	boolean isAlreadySetToIntake() {
		return RobotMap.Component.ballIO.getState() == BallIO.DoorState.INTAKE;
	}
}
