package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoalAndCrossBaseline extends CommandGroup {
	public static final double BACKING_UP_DISTANCE_IN_INCHES = -24;
	public static final int TURNING_ANGLE_FOR_BASELINE_IN_DEGREES = 135;
	public static final double MOVING_FORWARD_DISTANCE_IN_INCHES = 60;

	public AutonLowGoalAndCrossBaseline() {
		addSequential(new AutonLowGoal());
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonLowGoalAndCrossBaseline.BACKING_UP_DISTANCE_IN_INCHES,
				RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				AutonLowGoalAndCrossBaseline.TURNING_ANGLE_FOR_BASELINE_IN_DEGREES,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonLowGoalAndCrossBaseline.MOVING_FORWARD_DISTANCE_IN_INCHES,
				RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
