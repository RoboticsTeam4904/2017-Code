package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.standard.custom.ChassisController;

public class WiggleApproach implements ChassisController {
	public static final double WIGGLE_SPEED = -0.3;
	public static final double WIGGLE_PERIOD = 1.0 / 64.0;
	public static final double WIGGLE_AMPLITUDE = 0.5;
	protected final double wiggleSpeed;
	protected final double wigglePeriod;
	protected final double wiggleAmplitude;

	public WiggleApproach(double wiggleSpeed, double wigglePeriod, double wiggleAmplitude) {
		this.wiggleSpeed = wiggleSpeed;
		this.wigglePeriod = wigglePeriod;
		this.wiggleAmplitude = wiggleAmplitude;
	}

	public WiggleApproach() {
		wiggleSpeed = WiggleApproach.WIGGLE_SPEED;
		wigglePeriod = WiggleApproach.WIGGLE_PERIOD;
		wiggleAmplitude = WiggleApproach.WIGGLE_AMPLITUDE;
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
		return Math.sin(System.currentTimeMillis() * wigglePeriod) * wiggleAmplitude;
	}
}