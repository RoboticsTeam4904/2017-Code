package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearCenterPegVision extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH = 1.25;

	public AutonGearCenterPegVision() {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearCenterPegVision.TIME_INITIAL_APPROACH));
		addSequential(new WaitCommand(0.5));
		addSequential(new GearAlign());
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, -0.3, 0,
			0.75));
		addParallel(new RunFor(new GearioOuttake(), 3));
		addParallel(new RunAllSequential(
			new WaitCommand(3 - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_OUTTAKE_BACKOFF_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
