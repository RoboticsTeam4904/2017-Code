package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoalAndCrossBaselineBlue extends CommandGroup {
	public static final double BACKING_UP_DISTANCE_IN_INCHES = -24;
	public static final int TURNING_ANGLE_FOR_BASELINE_IN_DEGREES = 135;
	public static final double MOVING_FORWARD_DISTANCE_IN_INCHES = 60;

	public AutonLowGoalAndCrossBaselineBlue() {
		addSequential(new AutonLowGoalBlue());
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, AutonLowGoalAndCrossBaselineBlue.BACKING_UP_DISTANCE_IN_INCHES,
				RobotMap.Component.chassisDriveMC));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				AutonLowGoalAndCrossBaselineBlue.TURNING_ANGLE_FOR_BASELINE_IN_DEGREES,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonLowGoalAndCrossBaselineBlue.MOVING_FORWARD_DISTANCE_IN_INCHES, RobotMap.Component.chassisDriveMC));
	}
}
