package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGearBoilerPegTime extends CommandGroup {
	// These constants are based on those in AutonGearLoadPegTime.
	public static final double TIME_INITIAL_APPROACH_1 = 1.65;
	public static final double TIME_TURN = 1.05;
	public static final double TIME_INITIAL_APPROACH_2 = 0.55;

	public AutonGearBoilerPegTime(boolean isBlue) {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearBoilerPegTime.TIME_INITIAL_APPROACH_1));
		addSequential(
			new ChassisConstant(RobotMap.Component.chassis, 0, 0,
				-AutonConfig.DEAD_RECKON_TURN_SPEED * (isBlue ? 1.0 : -1.0), AutonGearBoilerPegTime.TIME_TURN));
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearBoilerPegTime.TIME_INITIAL_APPROACH_2));
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()),
				AutonConfig.DEAD_RECKON_WIGGLE_TIME));
		addSequential(new AutonGearOuttake());
	}
}
