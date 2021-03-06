package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearLoadPegTime extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH_1 = 1.6;
	public static final double TIME_TURN = 1;
	public static final double TIME_INITIAL_APPROACH_2 = 1;

	public AutonGearLoadPegTime(boolean isBlue) {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearLoadPegTime.TIME_INITIAL_APPROACH_1));
		addSequential(
			new ChassisConstant(RobotMap.Component.chassis, 0, 0,
				AutonConfig.DEAD_RECKON_TURN_SPEED * (isBlue ? 1.0 : -1.0), AutonGearLoadPegTime.TIME_TURN));
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearLoadPegTime.TIME_INITIAL_APPROACH_2));
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()),
				AutonConfig.DEAD_RECKON_WIGGLE_TIME));
		addParallel(new RunFor(new GearioOuttake(), AutonConfig.DEAD_RECKON_OUTTAKE_TIME));
		addParallel(new RunAllSequential(
			new WaitCommand(AutonConfig.DEAD_RECKON_OUTTAKE_TIME - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
