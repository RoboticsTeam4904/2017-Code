package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterStart extends CommandGroup {
	public final static double unloadDuration = 0.25; // TODO

	public ShooterStart() {
		addParallel(new FlywheelSpinup());
		addParallel(new RunFor(new IndexerUnload(), ShooterStart.unloadDuration));
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {
				return RobotMap.Component.flywheel.isReady();
			}
		});
		addParallel(new IndexerLoad());
	}
}
