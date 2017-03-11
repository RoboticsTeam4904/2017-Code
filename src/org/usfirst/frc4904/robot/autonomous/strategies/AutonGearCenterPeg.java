package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.robot.commands.WiggleApproach;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearCenterPeg extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH = 4;
	public static final double DISTANCE_TO_GEAR2_IN_INCHES = 114;
	public static final double ROBOT_LENGTH_IN_INCHES = 40;
	public static final double DISTANCE_TO_WIGGLE_IN_INCHES = 24;
	// Geario is in the back, invert values
	public static final double DISTANCE_BACK_TO_CLEAR_PEG_IN_INCHES = 14.3;
	public static final double DISTANCE_TO_DRIVE_IN_INCHES = -1
		* (AutonGearCenterPeg.DISTANCE_TO_GEAR2_IN_INCHES - AutonGearCenterPeg.ROBOT_LENGTH_IN_INCHES
			- AutonGearCenterPeg.DISTANCE_TO_WIGGLE_IN_INCHES);
	public static final double WIGGLE_SPEED = -0.3;
	public static final double WIGGLE_TIME = 0.5; // It's wiggle time!
	public static final double WIGGLE_AMPLITUDE = 0.5;
	public static final double WIGGLE_PERIOD = 1.0 / 64.0;

	public AutonGearCenterPeg() {
		addSequential(new RunFor(new ChassisMoveDistance(RobotMap.Component.chassis,
			AutonGearCenterPeg.DISTANCE_TO_DRIVE_IN_INCHES, RobotMap.Component.chassisDriveMC,
			RobotMap.Component.chassisEncoders), AutonGearCenterPeg.TIME_INITIAL_APPROACH));
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()), AutonGearCenterPegTime.WIGGLE_TIME));
		addParallel(new RunFor(new GearioOuttake(), 2));
		addParallel(new RunAllSequential(new WaitCommand(1),
			new ChassisConstant(RobotMap.Component.chassis, 0, -AutonGearCenterPegTime.DRIVE_SPEED, 0,
				AutonGearCenterPegTime.TIME_BACK_TO_CLEAR_PEG)));
	}
}
