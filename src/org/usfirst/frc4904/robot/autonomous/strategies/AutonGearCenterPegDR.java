package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonGearCenterPegDR extends CommandGroup {
	public static final double DISTANCE_TO_GEAR2_IN_INCHES = 114.3;
	public static final double ROBOT_LENGTH_IN_INCHES = 40;
	// Geario is in the back, invert values
	public static final double TIME_BACK_TO_CLEAR_PEG = 2;
	public static final double DRIVE_SPEED = -0.3;
	public static final double TIME_TO_DRIVE_FORWARD_TO_GET_TO_THE_GEAR_PEG = 4;
	public static final double WIGGLE_TIME = 1.5;
	public static final double WIGGLE_SPEED = 0.3;

	public AutonGearCenterPegDR() {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonGearCenterPegDR.DRIVE_SPEED, 0,
			AutonGearCenterPegDR.TIME_TO_DRIVE_FORWARD_TO_GET_TO_THE_GEAR_PEG));
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()), AutonGearCenterPegDR.WIGGLE_TIME));
		addParallel(new GearioOuttake(), 2);
		addParallel(new RunAllSequential(new WaitCommand(1),
			new ChassisConstant(RobotMap.Component.chassis, 0, -AutonGearCenterPegDR.DRIVE_SPEED, 0,
				AutonGearCenterPegDR.TIME_BACK_TO_CLEAR_PEG)));
	}

	public class WiggleApproach implements ChassisController {
		@Override
		public double getX() {
			return 0;
		}

		@Override
		public double getY() {
			return AutonGearCenterPegDR.DRIVE_SPEED;
		}

		@Override
		public double getTurnSpeed() {
			return Math.sin(System.currentTimeMillis() / 64) / 2.0;
		}
	}
}
