package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.OverridableCommandGroup;
import org.usfirst.frc4904.standard.commands.RunAllParallel;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.WaitUntil;

public class Shoot extends OverridableCommandGroup {
	public Shoot() {
		super(RobotMap.Component.hopper);
		addSequentialUnlessOverridden(new HopperSetShooter());
		addSequential(new RunAllParallel(
			new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME),
			new WaitUntil(RobotMap.Component.shooter.flywheel::isReady)));
		addParallel(new Agitate());
		addParallel(new IndexerLoad());
	}
}
