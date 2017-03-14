package org.usfirst.frc4904.robot.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonConfig;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FuelBoilerApproach extends CommandGroup {
	public static final double TIME_TURN = 0.25;
	public static final double DEGREES_TURN = -20;
	public static final double TIME_DRIVE = 3;
	public static final double INCHES_DRIVE = 115;
	public static final double SPEED_RAM = 0.75;
	public static final double TIME_RAM = 1;

	public FuelBoilerApproach(boolean useSensors) {
		super(useSensors ? "FuelBoilerApproach" : "FuelBoilerApproachDR");
		ChassisConstant timeTurn = new ChassisConstant(RobotMap.Component.chassis, 0, 0,
			AutonConfig.DEAD_RECKON_TURN_SPEED * AutonConfig.ALLIANCE_FACTOR, FuelBoilerApproach.TIME_TURN);
		ChassisConstant timeMove = new ChassisConstant(RobotMap.Component.chassis, 0, -AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			FuelBoilerApproach.TIME_DRIVE);
		if (useSensors) {
			addSequential(
				new ChassisTurn(RobotMap.Component.chassis, FuelBoilerApproach.DEGREES_TURN, RobotMap.Component.navx,
					timeTurn, RobotMap.Component.chassisTurnMC));
			addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, FuelBoilerApproach.INCHES_DRIVE,
				RobotMap.Component.chassisDriveMC, timeMove));
		} else {
			addSequential(timeTurn);
			addSequential(timeMove);
		}
		addSequential(
			new ChassisConstant(RobotMap.Component.chassis, 0, FuelBoilerApproach.SPEED_RAM, 0, FuelBoilerApproach.TIME_RAM));
	}
}
