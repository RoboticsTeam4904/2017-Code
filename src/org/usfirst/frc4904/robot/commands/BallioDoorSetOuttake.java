package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.RunUnless;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallioDoorSetOuttake extends CommandGroup {
	private static final double SERVO_PATH_CLEAR_TIME = 0.25;
	private static final double ELEVATOR_CLEAR_SPEED = 0.5;

	public BallioDoorSetOuttake() {
		Command elevatorsUp = new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller, ELEVATOR_CLEAR_SPEED);
		Command clearServoPath = new RunFor(elevatorsUp, SERVO_PATH_CLEAR_TIME);
		addSequential(new RunUnless(clearServoPath, this::isAlreadySetToOuttake));
		addSequential(new BallioDoorSetUnsafely(BallIO.DoorState.OUTTAKE));
	}

	boolean isAlreadySetToOuttake() {
		return RobotMap.Component.ballIO.getState() == BallIO.DoorState.OUTTAKE;
	}
}