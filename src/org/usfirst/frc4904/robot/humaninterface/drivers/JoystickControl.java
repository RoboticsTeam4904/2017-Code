package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;

public class JoystickControl extends Driver {
	public JoystickControl() {
		super("JoystickControl");
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.Component.chassis)));
		RobotMap.Component.xbox.rb
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.Component.xbox.lb
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return RobotMap.Component.xbox.leftStick.getY() * NathanGain.Y_SPEED_SCALE;
	}

	@Override
	public double getTurnSpeed() {
		return RobotMap.Component.xbox.rightStick.getX() * NathanGain.TURN_SPEED_SCALE;
	}
}
