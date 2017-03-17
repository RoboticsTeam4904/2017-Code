package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.OverridableCommandGroup;

public class Shoot extends OverridableCommandGroup {
	public Shoot() {
		super(RobotMap.Component.hopper);
		addSequentialUnlessOverridden(new HopperSetShooter());
		// addSequential(new RunAllParallel(
		// new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME),
		// new WaitUntil(RobotMap.Component.shooter.flywheel::isReady)));
		addParallel(new Agitate());
		addParallel(new IndexerLoad());
	}
}
