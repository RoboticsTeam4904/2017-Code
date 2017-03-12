package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.RunAllParallel;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.WaitUntil;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
	public final static double unloadDuration = 0.25; // TODO

	public Shoot() {
		addSequential(new HopperSetShooter());
		addSequential(new RunAllParallel(
			new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME),
			new WaitUntil(RobotMap.Component.shooter.flywheel::isReady)));
		addParallel(new Agitate());
		addParallel(new IndexerLoad());
	}
}
