package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPegAndLowGoalRed extends CommandGroup {
	public static final int ANGLE_MOVE_1_IN_DEGREES = 90;
	public static final double MOVE_CHASSIS_TO_X_DISTANCE_IN_INCHES = 82.44;
	public static final int ANGLE_MOVE_2_IN_DEGREES = 45;
	public static final double MOVE_CHASSIS_TO_2ndX_DISTANCE_IN_INCHES = 14.9;

	public AutonGearCenterPegAndLowGoalRed() {
		addSequential(new AutonGearCenterPeg());
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, AutonGearCenterPegAndLowGoalRed.ANGLE_MOVE_1_IN_DEGREES,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndLowGoalRed.MOVE_CHASSIS_TO_X_DISTANCE_IN_INCHES, RobotMap.Component.chassisDriveMC));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, AutonGearCenterPegAndLowGoalRed.ANGLE_MOVE_2_IN_DEGREES,
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndLowGoalRed.MOVE_CHASSIS_TO_2ndX_DISTANCE_IN_INCHES, RobotMap.Component.chassisDriveMC));
		addSequential(new BallioOuttake());
	}
}
