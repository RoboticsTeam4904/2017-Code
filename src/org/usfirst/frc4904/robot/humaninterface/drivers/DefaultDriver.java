package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.commands.AutoShifter;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class DefaultDriver extends Driver {
	public static final double XBOX_MINIMUM_THRESHOLD = 0.1;

	public DefaultDriver() {
		super("DefaultDriver");
	}

	@Override
	public void bindCommands() {
		AutoShifter shiftCommand = new AutoShifter();
		shiftCommand.start();
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

	@Override
	public double getTurnSpeed() {
		return 0;
	}
}