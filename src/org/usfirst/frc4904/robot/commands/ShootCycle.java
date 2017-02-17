package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootCycle extends CommandGroup {
	public ShootCycle() {
		super("ShootCycle");
		requires(RobotMap.Component.shooter);
		addParallel(new Align());
		addSequential(new FlywheelSpinup());
		addSequential(new Shoot());
	}
}
