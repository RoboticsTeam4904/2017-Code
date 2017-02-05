package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class HighShooter extends CommandGroup {
	public HighShooter() {
		RunAllSequential sequential = new RunAllSequential(
			new Command() {
				@Override
				protected boolean isFinished() {
					return RobotMap.Component.highFlywheel.isReady();
				}
			},
			new HighConveyorLoad());
		addParallel(new HighFlywheelSpinup());
		addParallel(sequential);
	}
}
