package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPeg extends CommandGroup {
	public static final double DISTANCE_TO_GEAR2_IN_INCHES = 114.3;
	public static final double ROBOT_LENGTH_IN_INCHES = 40;
	// Geario is in the back, invert values
	public static final double DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES = 14.3;
	public static final double DISTANCE_TO_DRIVE_IN_INCHES = -1
		* (AutonGearCenterPeg.DISTANCE_TO_GEAR2_IN_INCHES - AutonGearCenterPeg.ROBOT_LENGTH_IN_INCHES);

	public AutonGearCenterPeg() {
		addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonGearCenterPeg.DISTANCE_TO_DRIVE_IN_INCHES,
			RobotMap.Component.chassisDriveMC));
		addSequential(new GearioOuttake());
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, AutonGearCenterPeg.DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES,
				RobotMap.Component.chassisDriveMC));
	}
}
