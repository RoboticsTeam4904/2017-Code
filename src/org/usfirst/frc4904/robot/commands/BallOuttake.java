package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallOuttake extends CommandGroup {
	public BallOuttake() {
		super("BallOuttake");
		addSequential(new IntakeDoorOuttake());
		addSequential(new RunOuttakeMotors());
	}
}
