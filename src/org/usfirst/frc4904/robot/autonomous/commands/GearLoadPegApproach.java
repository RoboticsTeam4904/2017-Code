package org.usfirst.frc4904.robot.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonConfig;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearLoadPegApproach extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH_1 = 2;
	public static final double TIME_TURN = 1;
	public static final double TIME_INITIAL_APPROACH_2 = 0.75;
	public static final double INCHES_INITIAL_APPROACH_1 = 100;
	public static final double DEGREES_TURN = 60;
	public static final double INCHES_INITIAL_APPROACH_2 = 69;

	public GearLoadPegApproach(boolean useSensors) {
		ChassisConstant timeMoveA = new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED,
			0, GearLoadPegApproach.INCHES_INITIAL_APPROACH_1);
		ChassisConstant timeTurn = new ChassisConstant(RobotMap.Component.chassis, 0, 0,
			AutonConfig.DEAD_RECKON_TURN_SPEED * AutonConfig.ALLIANCE_FACTOR, GearLoadPegApproach.TIME_TURN);
		ChassisConstant timeMoveB = new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED,
			0,
			GearLoadPegApproach.INCHES_INITIAL_APPROACH_2);
		if (useSensors) {
			addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, GearLoadPegApproach.INCHES_INITIAL_APPROACH_1,
				RobotMap.Component.chassisDriveMC, timeMoveA));
			addSequential(new ChassisTurn(RobotMap.Component.chassis, GearLoadPegApproach.DEGREES_TURN, RobotMap.Component.navx,
				timeTurn, RobotMap.Component.chassisTurnMC));
			addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, GearLoadPegApproach.INCHES_INITIAL_APPROACH_2,
				RobotMap.Component.chassisDriveMC, timeMoveB));
		} else {
			addSequential(timeMoveA);
			addSequential(timeTurn);
			addSequential(timeMoveB);
		}
	}
}
