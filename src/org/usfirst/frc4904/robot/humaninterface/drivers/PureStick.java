package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;

public class PureStick extends Driver {
	public PureStick() {
		super("PureStick");
	}

	@Override
	public void bindCommands() {
		RobotMap.Component.operatorStick.button1
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
		RobotMap.Component.operatorStick.button2
			.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return RobotMap.Component.operatorStick.getY() * NathanGain.Y_SPEED_SCALE;
	}

	@Override
	public double getTurnSpeed() {
		return RobotMap.Component.operatorStick.getX() * NathanGain.TURN_SPEED_SCALE;
	}
}
