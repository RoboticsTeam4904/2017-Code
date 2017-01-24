package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisShift;
import org.usfirst.frc4904.standard.humaninput.Driver;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;

public class HardMode extends Driver {
	public HardMode() {
		super("Hard Mode");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Driver.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.Component.chassis)));
		RobotMap.HumanInput.Driver.xbox.a.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		RobotMap.HumanInput.Driver.xbox.b.whenPressed(new ChassisShift(RobotMap.Component.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		if (RobotMap.HumanInput.Driver.xbox.x.get()) {
			return (RobotMap.HumanInput.Driver.xbox.lt.getX() + RobotMap.HumanInput.Driver.xbox.rt.getX() - 1) * RobotMap.Constant.HumanInput.Y_SPEED_SCALE;
		} else {
			return 0;
		}
	}
	
	@Override
	public double getTurnSpeed() {
		if (RobotMap.HumanInput.Driver.xbox.x.get()) {
			return (RobotMap.HumanInput.Driver.xbox.lt.getX() - RobotMap.HumanInput.Driver.xbox.rt.getX()) * RobotMap.Constant.HumanInput.TURN_SPEED_SCALE;
		} else {
			return 0;
		}
	}
}
