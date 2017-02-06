package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.robot.vision.AligningCamera;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AlignmentSystem implements PIDSource {
	public static enum AlignmentState {
		IDLE, ACTIVE;
	}
	protected final FusedSensor<Double> sensor;
	protected final FusibleNavX navX;
	protected AlignmentState state;
	protected PIDSourceType pidSourceType;

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

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		return getDegrees();
	}
}
