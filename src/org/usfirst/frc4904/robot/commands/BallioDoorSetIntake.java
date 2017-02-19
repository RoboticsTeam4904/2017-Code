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
	public BallioDoorSetIntake() {
		Command hopperRollersDown = new MotorConstant(RobotMap.Component.ballIO.hopperRollers, -0.5);
		Command intakeRollersUp = new MotorConstant(RobotMap.Component.ballIO.elevatorAndIntakeRoller, 0.5);
		Command clearServoPath = new RunFor(new RunAllParallel(hopperRollersDown, intakeRollersUp), 0.25);
		addSequential(new RunUnless(clearServoPath, this::isAlreadySetToIntake));
		addSequential(new BallioDoorSet(BallIO.DoorState.INTAKE));
	}

	boolean isAlreadySetToIntake() {
		return RobotMap.Component.ballIO.getState() == BallIO.DoorState.INTAKE;
	}
}
