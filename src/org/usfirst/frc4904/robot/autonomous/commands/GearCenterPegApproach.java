package org.usfirst.frc4904.robot.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonConfig;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;

public class GearCenterPegApproach extends ChassisConstant {
	public static final double TIME_INITIAL_APPROACH = 1.5;

	public GearCenterPegApproach() {
		super(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			GearCenterPegApproach.TIME_INITIAL_APPROACH);
	}
}
