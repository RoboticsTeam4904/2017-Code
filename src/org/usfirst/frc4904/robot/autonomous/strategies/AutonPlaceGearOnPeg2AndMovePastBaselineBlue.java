package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearOnPeg2AndMovePastBaselineBlue extends CommandGroup {
	public AutonPlaceGearOnPeg2AndMovePastBaselineBlue() {
		addSequential(new AutonPlaceGearOnPeg2());
		addSequential(new ChassisTurn(RobotMap.Component.chassis,
			-1 * (AutonPlaceGearOnPeg2AndMovePastBaselineRed.DEGREES_OF_ANGLE_TO_PASS_BASELINE), RobotMap.Component.navx,
			RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg2AndMovePastBaselineRed.DISTANCE_FORWARD_FROM_TURN_IN_INCHES, RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
