package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoalAndCrossBaselineRed extends CommandGroup {
	public AutonLowGoalAndCrossBaselineRed() {
		addSequential(new AutonLowGoalRed());
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, AutonLowGoalAndCrossBaselineBlue.BACKING_UP_DISTANCE_IN_INCHES,
				RobotMap.Component.chassisDriveMC));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				-1 * (AutonLowGoalAndCrossBaselineBlue.TURNING_ANGLE_FOR_BASELINE_IN_DEGREES),
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonLowGoalAndCrossBaselineBlue.MOVING_FORWARD_DISTANCE_IN_INCHES, RobotMap.Component.chassisDriveMC));
	}
}
