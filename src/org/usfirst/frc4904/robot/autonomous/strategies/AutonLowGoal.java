package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.autonomous.commands.FuelBoilerApproach;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoal extends CommandGroup {
	public AutonLowGoal(boolean useSensors) {
		super(useSensors ? "AutonLowGoal" : "AutonLowGoalDR");
		addSequential(new FuelBoilerApproach(useSensors));
		addSequential(new BallioOuttake());
	}
}
