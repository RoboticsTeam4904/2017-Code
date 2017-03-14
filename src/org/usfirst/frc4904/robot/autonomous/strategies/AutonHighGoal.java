package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.autonomous.commands.FuelBoilerApproach;
import org.usfirst.frc4904.robot.commands.FlywheelSpinup;
import org.usfirst.frc4904.robot.commands.Shoot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonHighGoal extends CommandGroup {
	public AutonHighGoal(boolean useSensors) {
		super(useSensors ? "AutonHighGoal" : "AutonHighGoalDR");
		addSequential(new FuelBoilerApproach(useSensors));
		addParallel(new FlywheelSpinup());
		addSequential(new Shoot());
	}
}
