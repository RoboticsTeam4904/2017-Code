package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearOuttake extends CommandGroup {
	public static final double PRE_OUTTAKE_DELAY = 0.5;
	public static final double OUTTAKE_TIME_TOTAL = 3;
	public static final double DEAD_RECKON_OUTTAKE_TIME = 2.5;
	public static final double DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG = 2.0;
	public static final double DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED = 0.3;

	public AutonGearOuttake() {
		addSequential(new WaitCommand(AutonGearOuttake.PRE_OUTTAKE_DELAY));
		addParallel(new RunFor(new GearioOuttake(), AutonGearOuttake.OUTTAKE_TIME_TOTAL));
		addParallel(new RunAllSequential(
			new WaitCommand(AutonGearOuttake.OUTTAKE_TIME_TOTAL - AutonGearOuttake.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonGearOuttake.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonGearOuttake.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
