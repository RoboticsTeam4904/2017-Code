package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeensyJoystickReader extends Command {
	public CustomJoystick teensyJoystick;

	public TeensyJoystickReader() {
		teensyJoystick = RobotMap.Component.teensyStick;
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
