package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCrossBaselineTime extends CommandGroup {
	public static final double TIME_CLEAR_BASELINE = 4.5;

	public AutonCrossBaselineTime() {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonCrossBaselineTime.TIME_CLEAR_BASELINE));
	}
}