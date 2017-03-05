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

public class AutonGearCenterPegTime extends CommandGroup {
	public static final double TIME_BACK_TO_CLEAR_PEG = 1;
	public static final double DRIVE_SPEED = -0.6;
	public static final double TIME_INITIAL_APPROACH = 1.5;
	public static final double WIGGLE_SPEED = -0.3;
	public static final double WIGGLE_TIME = 2.0; // It's wiggle time!
	public static final double WIGGLE_AMPLITUDE = 0.5;
	public static final double WIGGLE_PERIOD = 1.0 / 64.0;

	public AutonGearCenterPegTime() {
		addSequential(new ChassisConstant(RobotMap.Component.chassis, 0, AutonGearCenterPegTime.DRIVE_SPEED, 0,
			AutonGearCenterPegTime.TIME_INITIAL_APPROACH));
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()), AutonGearCenterPegTime.WIGGLE_TIME));
		addParallel(new RunFor(new GearioOuttake(), 2));
		addParallel(new RunAllSequential(new WaitCommand(1),
			new ChassisConstant(RobotMap.Component.chassis, 0, -AutonGearCenterPegTime.DRIVE_SPEED, 0,
				AutonGearCenterPegTime.TIME_BACK_TO_CLEAR_PEG)));
	}

	public class WiggleApproach implements ChassisController {
		@Override
		public double getX() {
			return 0;
		}

		@Override
		public double getY() {
			return AutonGearCenterPegTime.WIGGLE_SPEED;
		}

		@Override
		public double getTurnSpeed() {
			return Math.sin(System.currentTimeMillis() * AutonGearCenterPegTime.WIGGLE_PERIOD)
				* AutonGearCenterPegTime.WIGGLE_AMPLITUDE;
		}
	}
}
