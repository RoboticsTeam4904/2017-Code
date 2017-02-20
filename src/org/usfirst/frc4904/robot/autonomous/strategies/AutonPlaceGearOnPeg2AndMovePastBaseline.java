package org.usfirst.frc4904.robot.autonomous.strategies;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearOnPeg2AndMovePastBaseline extends CommandGroup {
	public static double DEGREES_OF_ANGLE_TO_PASS_BASELINE = 60;
	public static double DISTANCE_FORWARD_FROM_TURN = 39.1;

	public AutonPlaceGearOnPeg2AndMovePastBaseline() {
		addSequential(new AutonPlaceGearOnPeg2());
		addSequential(new ChassisTurn(RobotMap.Component.chassis,
				AutonPlaceGearOnPeg2AndMovePastBaseline.DEGREES_OF_ANGLE_TO_PASS_BASELINE, RobotMap.Component.navx,
				RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonPlaceGearOnPeg2AndMovePastBaseline.DISTANCE_FORWARD_FROM_TURN, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}

}