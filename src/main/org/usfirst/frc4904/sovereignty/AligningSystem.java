package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.FusibleNavX.NavxMode;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AligningSystem implements PIDSource, Fusible<Double> {
	public static enum AligningState {
		IDLE, ACTIVE;
	}
	public static final double ANGLE_TOLERANCE = 2;
	protected final FusedSensor<Double> sensorSystem;
	protected final AligningCamera camera;
	protected final FusibleNavX navX;
	protected AligningState state;
	protected PIDSourceType pidSourceType;

	public AligningSystem(AligningCamera camera, FusibleNavX navX) {
		sensorSystem = new FusedSensor<Double>(camera, navX);
		this.camera = camera;
		this.navX = navX;
		state = AligningState.IDLE;
	}

	public void start() {
		state = AligningState.ACTIVE;
		navX.setMode(NavxMode.ALIGNMENT);
		navX.setTargetAngle(camera.getDegrees());
	}

	public void cancel() {
		state = AligningState.IDLE;
		navX.setMode(NavxMode.STANDARD);
	}

	public double getDegrees() {
		Fusible<Double> sensor = sensorSystem.getActiveSensor();
		Double value = sensor.getValue();
		if (value == null) {
			return 0.0d;
		}
		if (sensor instanceof AligningCamera) {
			navX.setTargetAngle(value);
			navX.zeroRelativeOffset();
		}
		return value;
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

	@Override
	public Double getValue() {
		return sensorSystem.getValue();
	}

	@Override
	public boolean trustable() {
		return sensorSystem.trustable();
	}
}
