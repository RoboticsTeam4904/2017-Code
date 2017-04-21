package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DistanceAutonGearCenterPegVision extends CommandGroup {
	public static final double INCHES_INITIAL_APPROACH = -103; // SHOULD BE NEGATIVE FOR GEARIO
	public static final double OUTTAKE_TIME_TOTAL = 3;
	public static final double PRE_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_APPROACH_INCHES = -6; // SHOULD BE NEGATIVE FOR GEARIO

	public DistanceAutonGearCenterPegVision() {
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, DistanceAutonGearCenterPegVision.INCHES_INITIAL_APPROACH,
				RobotMap.Component.chassisDriveMC));
		addSequential(new WaitCommand(DistanceAutonGearCenterPegVision.PRE_ALIGN_DELAY));
		addSequential(new GearAlign());
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, DistanceAutonGearCenterPegVision.POST_ALIGN_APPROACH_INCHES,
				RobotMap.Component.chassisDriveMC));
		addSequential(new WaitCommand(DistanceAutonGearCenterPegVision.POST_ALIGN_DELAY));
		addParallel(new RunFor(new GearioOuttake(), DistanceAutonGearCenterPegVision.OUTTAKE_TIME_TOTAL));
		addParallel(new RunAllSequential(
			new WaitCommand(
				DistanceAutonGearCenterPegVision.OUTTAKE_TIME_TOTAL - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
