package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterStart extends CommandGroup {
	public ShooterStart() {
		RunAllSequential sequential = new RunAllSequential(
			new Command() {
				@Override
				protected boolean isFinished() {
					return RobotMap.Component.flywheel.isReady();
				}
			},
			new IndexerLoad());
		addParallel(new FlywheelSpinup());
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {
				return RobotMap.Component.flywheel.isReady();
			}
		});
		addParallel(new IndexerLoad());
	}
}
