package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoalRed extends CommandGroup {
	public AutonLowGoalRed() {
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, AutonLowGoalBlue.DISTANCE_TO_APPROACH_BOALER_AT_45_IN_INCHES,
				RobotMap.Component.chassisDriveMC));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				-1 * (AutonLowGoalBlue.ANGLE_TO_APPROACH_BOILER_IN_DEGREES),
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonLowGoalBlue.DISTANCE_TO_APPROACH_BOALER_IN_INCHES, RobotMap.Component.chassisDriveMC));
	}
}
