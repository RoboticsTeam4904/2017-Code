package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearLoadPegVision extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH_1 = 1.9;
	public static final double TIME_TURN = 0.7;
	public static final double PRE_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_APPROACH_SPEED = -0.3;
	public static final double POST_ALIGN_APPROACH_TIME = 1.25;

	public AutonGearLoadPegVision(boolean isBlue) {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearLoadPegVision.TIME_INITIAL_APPROACH_1));
		addSequential(
			new ChassisConstant(RobotMap.Component.chassis, 0, 0,
				AutonConfig.DEAD_RECKON_TURN_SPEED * (isBlue ? 1.0 : -1.0), AutonGearLoadPegVision.TIME_TURN));
		addSequential(new WaitCommand(AutonGearLoadPegVision.PRE_ALIGN_DELAY));
		addSequential(new GearAlign());
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonGearLoadPegVision.POST_ALIGN_APPROACH_SPEED, 0,
			AutonGearLoadPegVision.POST_ALIGN_APPROACH_TIME));
		addSequential(new AutonGearOuttake());
	}
}
