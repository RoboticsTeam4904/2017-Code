package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.robot.vision.AligningCamera;

public class AlignmentSystem {
	public enum AlignmentState {
		IDLE, ACTIVE;
	}
	protected final FusedSensor<Double> sensor;
	protected final FusibleNavX navX;
	protected AlignmentState state;

	public AlignmentSystem(AligningCamera camera, FusibleNavX navX) {
		sensor = new FusedSensor<Double>(camera, navX, Double.NaN);
		this.navX = navX;
		state = AlignmentState.IDLE;
	}

	public void start() {
		state = AlignmentState.ACTIVE;
		navX.zeroRelativeOffset();
	}

	public void cancel() {
		state = AlignmentState.IDLE;
	}

	public double getDegrees() {
		return sensor.getValue();
	}
}
