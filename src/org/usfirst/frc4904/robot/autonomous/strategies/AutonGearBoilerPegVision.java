package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearBoilerPegVision extends CommandGroup {
	// These constants are based on those in AutonGearLoadPegTime.
	public static final double TIME_INITIAL_APPROACH_1 = 1.65;
	public static final double TIME_TURN = 1.05;
	public static final double TIME_INITIAL_APPROACH_2 = 0.3;
	public static final double OUTTAKE_TIME_TOTAL = 3;
	public static final double PRE_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_APPROACH_SPEED = -0.3;
	public static final double POST_ALIGN_APPROACH_TIME = 0.2;

	public AutonGearBoilerPegVision(boolean isBlue) {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearBoilerPegVision.TIME_INITIAL_APPROACH_1));
		addSequential(
			new ChassisConstant(RobotMap.Component.chassis, 0, 0,
				-AutonConfig.DEAD_RECKON_TURN_SPEED * (isBlue ? 1.0 : -1.0), AutonGearBoilerPegVision.TIME_TURN));
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearBoilerPegVision.TIME_INITIAL_APPROACH_2));
		addSequential(new WaitCommand(AutonGearBoilerPegVision.PRE_ALIGN_DELAY));
		addSequential(new GearAlign());
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonGearBoilerPegVision.POST_ALIGN_APPROACH_SPEED, 0,
			AutonGearBoilerPegVision.POST_ALIGN_APPROACH_TIME));
		addParallel(new RunFor(new GearioOuttake(), AutonGearBoilerPegVision.OUTTAKE_TIME_TOTAL));
		addParallel(new RunAllSequential(
			new WaitCommand(AutonGearBoilerPegVision.OUTTAKE_TIME_TOTAL - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
