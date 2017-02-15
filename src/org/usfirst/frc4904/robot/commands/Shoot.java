package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
	public Shoot() {
		addParallel(new RunFor(new IndexerUnload(), Shooter.INDEXER_UNLOAD_TIME));
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {
				return RobotMap.Component.shooter.flywheel.isReady();
			}
		});
		addParallel(new IndexerLoad());
	}
}
