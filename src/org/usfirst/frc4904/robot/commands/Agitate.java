package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Agitate extends CommandGroup {
	public Agitate() {
		addSequential(new MotorConstant(RobotMap.Component.ballIO.hopperRollers, -BallIO.HOPPER_ROLLERS_FORWARD_SPEED));
	}
}
