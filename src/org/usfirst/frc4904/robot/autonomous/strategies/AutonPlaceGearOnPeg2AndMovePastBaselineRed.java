package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearOnPeg2AndMovePastBaselineRed extends CommandGroup {
	public static final double DEGREES_OF_ANGLE_TO_PASS_BASELINE = 60;
	public static final double DISTANCE_FORWARD_FROM_TURN_IN_INCHES = 39.1;

	public AutonPlaceGearOnPeg2AndMovePastBaselineRed() {
		addSequential(new AutonPlaceGearOnPeg2Red());
		addSequential(new ChassisTurn(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg2AndMovePastBaselineRed.DEGREES_OF_ANGLE_TO_PASS_BASELINE, RobotMap.Component.navx,
			RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg2AndMovePastBaselineRed.DISTANCE_FORWARD_FROM_TURN_IN_INCHES, RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}