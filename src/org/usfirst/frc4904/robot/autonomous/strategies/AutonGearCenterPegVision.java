package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearCenterPegVision extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH = 1.25;
	public static final double OUTTAKE_TIME_TOTAL = 3;
	public static final double PRE_ALIGN_DELAY = 0.5;
	public static final double POST_ALIGN_APPROACH_SPEED = -0.3;
	public static final double POST_ALIGN_APPROACH_TIME = 0.75;

	public AutonGearCenterPegVision() {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonGearCenterPegVision.TIME_INITIAL_APPROACH));
		addSequential(new WaitCommand(AutonGearCenterPegVision.PRE_ALIGN_DELAY));
		addSequential(new GearAlign());
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonGearCenterPegVision.POST_ALIGN_APPROACH_SPEED, 0,
			AutonGearCenterPegVision.POST_ALIGN_APPROACH_TIME));
		addSequential(new AutonGearOuttake());
	}
}
