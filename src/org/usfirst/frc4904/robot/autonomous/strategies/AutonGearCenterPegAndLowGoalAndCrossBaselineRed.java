package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPegAndLowGoalAndCrossBaselineRed extends CommandGroup {
	public static final double DISTANCE_TO_CLEAR_TURN_RADIUS_IN_INCHES = 10;
	public static final double DISTANCE_TO_BASELINE_IN_INCHES = 60;
	public static final double TURN_NEEDED_TO_ALIGN_WITH_BASELINE_IN_DEGREES = -135;

	public AutonGearCenterPegAndLowGoalAndCrossBaselineRed() {
		addSequential(new AutonGearCenterPegAndLowGoalRed());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndLowGoalAndCrossBaselineRed.DISTANCE_TO_CLEAR_TURN_RADIUS_IN_INCHES,
			RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				AutonGearCenterPegAndLowGoalAndCrossBaselineRed.TURN_NEEDED_TO_ALIGN_WITH_BASELINE_IN_DEGREES,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndLowGoalAndCrossBaselineRed.DISTANCE_TO_BASELINE_IN_INCHES,
			RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
