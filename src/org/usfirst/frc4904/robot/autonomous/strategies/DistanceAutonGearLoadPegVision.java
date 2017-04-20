package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DistanceAutonGearLoadPegVision extends CommandGroup {
	public static final double INCHES_INITIAL_APPROACH_1 = -114; // SHOULD BE NEGATIVE FOR GEARIO
	public static final double DEGREES_TURN = -60;
	public static final double OUTTAKE_TIME_TOTAL = 3;
	public static final double PRE_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_APPROACH_SPEED = -0.3;
	public static final double POST_ALIGN_APPROACH_INCHES = -33; // SHOULD BE NEGATIVE FOR GEARIO
	public static final double PRE_OUTTAKE_DELAY = 0.5;

	public DistanceAutonGearLoadPegVision(boolean isBlue) {
		// Initial approach
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, DistanceAutonGearLoadPegVision.INCHES_INITIAL_APPROACH_1,
				RobotMap.Component.chassisDriveMC));
		// Turn
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, DistanceAutonGearLoadPegVision.DEGREES_TURN * (isBlue ? 1.0 : -1.0),
				RobotMap.Component.navx,
				RobotMap.Component.chassisTurnMC));
		// Pause before align
		addSequential(new WaitCommand(DistanceAutonGearLoadPegVision.PRE_ALIGN_DELAY));
		// Align
		addSequential(new GearAlign());
		// Approach peg
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, DistanceAutonGearLoadPegVision.POST_ALIGN_APPROACH_INCHES,
				RobotMap.Component.chassisDriveMC));
		// Pause before outtake
		addSequential(new WaitCommand(DistanceAutonGearLoadPegVision.PRE_OUTTAKE_DELAY));
		// Outtake
		addParallel(new RunFor(new GearioOuttake(), DistanceAutonGearLoadPegVision.OUTTAKE_TIME_TOTAL));
		// Go away
		addParallel(new RunAllSequential(
			new WaitCommand(DistanceAutonGearLoadPegVision.OUTTAKE_TIME_TOTAL - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
