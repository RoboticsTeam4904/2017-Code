package org.usfirst.frc4904.robot.autonomous.strategies;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPlaceGearOnPeg2 extends CommandGroup {
	public static double DISTANCE_TO_GEAR2_IN_INCHES = 78.5;
	public static double DISTANCE_BACK_TO_CLEAR_BOILER = -14.3;

	public AutonPlaceGearOnPeg2() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
				AutonPlaceGearOnPeg2.DISTANCE_TO_GEAR2_IN_INCHES - 40, RobotMap.Component.chassisDriveMC,
				RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new GearioOuttake());
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonPlaceGearOnPeg2.DISTANCE_BACK_TO_CLEAR_BOILER,
				RobotMap.Component.chassisDriveMC, RobotMap.Component.leftWheelEncoder,
				RobotMap.Component.rightWheelEncoder));
	}

}
