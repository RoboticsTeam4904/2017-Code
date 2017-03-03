package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.BallioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPegAndLowGoalBlue extends CommandGroup {
	public AutonGearCenterPegAndLowGoalBlue() {
		addSequential(new AutonGearCenterPeg());
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, -1 * (AutonGearCenterPegAndLowGoalRed.ANGLE_MOVE_1_IN_DEGREES),
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndLowGoalRed.MOVE_CHASSIS_TO_X_DISTANCE_IN_INCHES,
			RobotMap.Component.chassisDriveMC, RobotMap.Component.leftWheelEncoder,
			RobotMap.Component.rightWheelEncoder));
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, -1 * (AutonGearCenterPegAndLowGoalRed.ANGLE_MOVE_2_IN_DEGREES),
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndLowGoalRed.MOVE_CHASSIS_TO_2ndX_DISTANCE_IN_INCHES,
			RobotMap.Component.chassisDriveMC, RobotMap.Component.leftWheelEncoder,
			RobotMap.Component.rightWheelEncoder));
		addSequential(new BallioOuttake());
	}
}
