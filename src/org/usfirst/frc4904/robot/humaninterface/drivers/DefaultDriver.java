package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.standard.humaninput.Driver;

public class DefaultDriver extends Driver {
	public static final double CONTROLLER_MIN_THRESH = 0.1;
	
	public DefaultDriver() {
		super("DefaultDriver");
	}
	
	@Override
	public void bindCommands() {}
	
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