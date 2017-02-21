package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoalBlue extends CommandGroup {
	public static final double DISTANCE_TO_APPROACH_BOALER_AT_45_IN_INCHES = 48;
	public static final int ANGLE_TO_APPROACH_BOILER_IN_DEGREES = 45;
	public static final double DISTANCE_TO_APPROACH_BOALER_IN_INCHES = 44;

	public AutonLowGoalBlue() {
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonLowGoalBlue.DISTANCE_TO_APPROACH_BOALER_AT_45_IN_INCHES,
				RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				AutonLowGoalBlue.ANGLE_TO_APPROACH_BOILER_IN_DEGREES,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonLowGoalBlue.DISTANCE_TO_APPROACH_BOALER_IN_INCHES,
				RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
