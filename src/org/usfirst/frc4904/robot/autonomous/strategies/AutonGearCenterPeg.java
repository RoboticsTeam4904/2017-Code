package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPeg extends CommandGroup {
	public static final double DISTANCE_TO_GEAR2_IN_INCHES = 78.5;
	public static final double DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES = -14.3;

	public AutonGearCenterPeg() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPeg.DISTANCE_TO_GEAR2_IN_INCHES - 40, RobotMap.Component.chassisDriveMC,
			RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder));
		addSequential(new GearioOuttake());
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, AutonGearCenterPeg.DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES,
				RobotMap.Component.chassisDriveMC, RobotMap.Component.leftWheelEncoder,
				RobotMap.Component.rightWheelEncoder));
	}
}
