package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonDriveOut extends CommandGroup {
	public static final double TIME_TURN = 0.5;
	public static final double DEGREES_TURN = -30;
	public static final double TIME_DRIVE = 5;
	public static final double INCHES_DRIVE = -288;

	public AutonDriveOut(boolean useSensors) {
		super(useSensors ? "AutonDriveOut" : "AutonDriveOutDR");
		ChassisConstant timeTurn = new ChassisConstant(RobotMap.Component.chassis, 0, 0,
			AutonConfig.DEAD_RECKON_TURN_SPEED * AutonConfig.ALLIANCE_FACTOR, AutonDriveOut.TIME_TURN);
		ChassisConstant timeMove = new ChassisConstant(RobotMap.Component.chassis, 0, -AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
			AutonDriveOut.TIME_DRIVE);
		if (useSensors) {
			addSequential(
				new ChassisTurn(RobotMap.Component.chassis, AutonDriveOut.DEGREES_TURN, RobotMap.Component.navx,
					timeTurn, RobotMap.Component.chassisTurnMC));
			addSequential(new ChassisMoveDistance(RobotMap.Component.chassis, AutonDriveOut.INCHES_DRIVE,
				RobotMap.Component.chassisDriveMC, timeMove));
		} else {
			addSequential(timeTurn);
			addSequential(timeMove);
		}
	}
}
