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

public class DistanceAutonGearBoilerPegVision extends CommandGroup {
	public static final double INCHES_INITIAL_APPROACH_1 = -111; // SHOULD BE NEGATIVE FOR GEARIO
	public static final double DEGREES_TURN = 60;
	public static final double INCHES_INITIAL_APPROACH_2 = -39; // SHOULD BE NEGATIVE FOR GEARIO
	public static final double OUTTAKE_TIME_TOTAL = 3;
	public static final double PRE_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_APPROACH_SPEED = -0.3;
	public static final double POST_ALIGN_APPROACH_TIME = 1.25; // SHOULD BE NEGATIVE FOR GEARIO
	public static final double PRE_OUTTAKE_DELAY = 0.5;

	public DistanceAutonGearBoilerPegVision(boolean isBlue) {
		// Initial approach
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, DistanceAutonGearBoilerPegVision.INCHES_INITIAL_APPROACH_1,
				RobotMap.Component.chassisDriveMC));
		// Turn
		addSequential(
			new ChassisTurn(RobotMap.Component.chassis, DistanceAutonGearBoilerPegVision.DEGREES_TURN * (isBlue ? 1.0 : -1.0),
				RobotMap.Component.navx,
				RobotMap.Component.chassisTurnMC));
		// Second approach
		addSequential(
			new ChassisMoveDistance(RobotMap.Component.chassis, DistanceAutonGearBoilerPegVision.INCHES_INITIAL_APPROACH_2,
				RobotMap.Component.chassisDriveMC));
		// Pause before align
		addSequential(new WaitCommand(DistanceAutonGearBoilerPegVision.PRE_ALIGN_DELAY));
		// Align
		addSequential(new GearAlign());
		// Actually get to the peg
		addSequential(
			new ChassisConstant(RobotMap.Component.chassis, 0, DistanceAutonGearBoilerPegVision.POST_ALIGN_APPROACH_SPEED, 0,
				DistanceAutonGearBoilerPegVision.POST_ALIGN_APPROACH_TIME));
		addSequential(new WaitCommand(DistanceAutonGearBoilerPegVision.PRE_OUTTAKE_DELAY));
		addParallel(new RunFor(new GearioOuttake(), DistanceAutonGearBoilerPegVision.OUTTAKE_TIME_TOTAL));
		addParallel(new RunAllSequential(
			new WaitCommand(
				DistanceAutonGearBoilerPegVision.OUTTAKE_TIME_TOTAL - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
