package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallIntake extends CommandGroup {
	public BallIntake() {
		super("BallIntake");
		addSequential(new IntakeDoorIntake());
		addSequential(new RunIntakeMotors());
	}
}
