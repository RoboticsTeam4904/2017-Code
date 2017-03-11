package org.usfirst.frc4904.robot.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.autonomous.strategies.AutonConfig;
import org.usfirst.frc4904.robot.autonomous.strategies.WiggleApproach;
import org.usfirst.frc4904.robot.commands.GearioOuttake;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearPegWiggleAndWithdraw extends CommandGroup {
	public GearPegWiggleAndWithdraw() {
		addSequential(
			new RunFor(new ChassisMove(RobotMap.Component.chassis, new WiggleApproach()),
				AutonConfig.DEAD_RECKON_WIGGLE_TIME));
		addParallel(new RunFor(new GearioOuttake(), AutonConfig.DEAD_RECKON_OUTTAKE_TIME));
		addParallel(new RunAllSequential(
			new WaitCommand(AutonConfig.DEAD_RECKON_OUTTAKE_TIME - AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG),
			new ChassisConstant(RobotMap.Component.chassis, 0, -AutonConfig.DEAD_RECKON_DRIVE_SPEED, 0,
				AutonConfig.DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG)));
	}
}
