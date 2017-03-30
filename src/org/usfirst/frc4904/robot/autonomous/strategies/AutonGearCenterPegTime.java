package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearCenterPegTime extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH = 1.5;

	public AutonGearCenterPegTime() {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearCenterPegTime.TIME_INITIAL_APPROACH));
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()), AutonConfig.DEAD_RECKON_WIGGLE_TIME));
		addSequential(new AutonGearOuttake());
	}
}
