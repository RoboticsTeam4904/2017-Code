package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.LogKitten.KittenLevel;
import org.usfirst.frc4904.standard.commands.KittenCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestSubsystems extends CommandGroup {
	public AutoTestSubsystems() {
		addSequential(new KittenCommand("djnakfnjkasnfjkaNFDAKSLFNASKFNJKSANFAJSK", KittenLevel.WTF));
		// addSequential(
		// new CheckMotorConstant(RobotMap.Component.leftWheel, RobotMap.Component.leftWheelEncoder, 0.2, 2, 0.1, 2));
		// addSequential(
		// new CheckMotorConstant(RobotMap.Component.rightWheel, RobotMap.Component.rightWheelEncoder, 0.2, 2, 0.1, 2));
	}
}
