package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.Hopper;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class HopperSetShooter extends CommandGroup {
	public HopperSetShooter() {
		addSequential(new BallioDoorSetIntake());
		addSequential(new HopperSet(Hopper.HopperState.SHOOTER));
	}
}