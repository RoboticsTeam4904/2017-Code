package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearOnPeg1RedAndPeg3BlueAndCrossBaseline extends CommandGroup {
	public AutonPlaceGearOnPeg1RedAndPeg3BlueAndCrossBaseline() {
		addSequential(new AutonPlaceGearOnPeg1BlueAndPeg3Red());
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis,
				-1 * (AutonPlaceGearOnPeg1BlueAndPeg3RedAndCrossBaseline.ANGLE_TURNING_TO_GET_TO_BASELINE_IN_DEGREES),
				RobotMap.Component.navx, RobotMap.Component.chassisTurnMC));
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonPlaceGearOnPeg1BlueAndPeg3RedAndCrossBaseline.DISTANCE_TO_BASELINE_IN_INCHES,
			RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
	}
}
