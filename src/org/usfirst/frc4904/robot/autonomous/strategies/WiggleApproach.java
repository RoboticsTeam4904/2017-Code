package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.standard.custom.ChassisController;

public class WiggleApproach implements ChassisController {
	public static final double WIGGLE_SPEED = -0.5;
	public static final double WIGGLE_PERIOD = 500;
	public static final double WIGGLE_AMPLITUDE = 0.25;
	protected final double wiggleSpeed;
	protected final double wigglePeriod;
	protected final double wiggleAmplitude;

	public WiggleApproach(double wiggleSpeed, double wigglePeriod, double wiggleAmplitude) {
		this.wiggleSpeed = wiggleSpeed;
		this.wigglePeriod = wigglePeriod;
		this.wiggleAmplitude = wiggleAmplitude;
	}

	public WiggleApproach() {
		this(WiggleApproach.WIGGLE_SPEED, WiggleApproach.WIGGLE_PERIOD, WiggleApproach.WIGGLE_AMPLITUDE);
	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return wiggleSpeed;
	}

	@Override
	public double getTurnSpeed() {
		return Math.sin(System.currentTimeMillis() * ((2 * Math.PI) / wigglePeriod)) * wiggleAmplitude;
	}
}