package org.usfirst.frc4904.robot.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonConfig;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearBoilerPegApproach extends CommandGroup {
	public static final double TIME_INITIAL_APPROACH_1 = 1.7;
	public static final double TIME_TURN = 1;
	public static final double TIME_INITIAL_APPROACH_2 = 0.75;
	public static final double INCHES_INITIAL_APPROACH_1 = -81;
	public static final double INCHES_INITIAL_APPROACH_2 = -51;
	public static final double DEGREES_TURN = 60; // Inverted for boiler.

	public GearBoilerPegApproach(boolean useSensors) {
		super(useSensors ? "GearBoilerPegApproach" : "GearBoilerPegApproachDR");
		ChassisConstant timeMoveA = new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED,
			0, GearBoilerPegApproach.TIME_INITIAL_APPROACH_1);
		// Turn speed inverted (*-1) for boiler.
		ChassisConstant timeTurn = new ChassisConstant(RobotMap.Component.chassis, 0, 0,
			-AutonConfig.DEAD_RECKON_TURN_SPEED * AutonConfig.ALLIANCE_FACTOR, GearBoilerPegApproach.TIME_TURN);
		ChassisConstant timeMoveB = new ChassisConstant(RobotMap.Component.chassis, 0, AutonConfig.DEAD_RECKON_DRIVE_SPEED,
			0,
			GearBoilerPegApproach.TIME_INITIAL_APPROACH_2);
		if (useSensors) {
			addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, GearBoilerPegApproach.INCHES_INITIAL_APPROACH_1,
				RobotMap.Component.chassisDriveMC, timeMoveA));
			addSequential(
				new ChassisTurn(RobotMap.Component.chassis, GearBoilerPegApproach.DEGREES_TURN, RobotMap.Component.navx,
					timeTurn, RobotMap.Component.chassisTurnMC));
			addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, GearBoilerPegApproach.INCHES_INITIAL_APPROACH_2,
				RobotMap.Component.chassisDriveMC, timeMoveB));
		} else {
			addSequential(timeMoveA);
			addSequential(timeTurn);
			addSequential(timeMoveB);
		}
	}
}
