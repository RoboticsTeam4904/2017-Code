package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPegAndCrossBaselineRed extends CommandGroup {
	public static final double DEGREES_OF_ANGLE_TO_PASS_BASELINE = 60;
	public static final double DISTANCE_FORWARD_FROM_TURN_IN_INCHES = 39.1;

	public AutonGearCenterPegAndCrossBaselineRed() {
		addSequential(new AutonGearCenterPeg());
		addSequential(new ChassisTurn(RobotMap.Component.chassis,
			AutonGearCenterPegAndCrossBaselineRed.DEGREES_OF_ANGLE_TO_PASS_BASELINE, RobotMap.Component.navx,
			RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPegAndCrossBaselineRed.DISTANCE_FORWARD_FROM_TURN_IN_INCHES, RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}