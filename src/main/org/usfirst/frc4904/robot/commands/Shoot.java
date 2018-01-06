package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
	public Shoot() {
		super("Shoot");
		// addSequential(new RunAllParallel(
		// new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME),
		// new WaitUntil(RobotMap.Component.shooter.flywheel::isReady)));
		addParallel(new IndexerLoad());
	}
}
