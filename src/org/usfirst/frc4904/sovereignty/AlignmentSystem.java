package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.FusibleNavX.NavxMode;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AlignmentSystem implements PIDSource {
	public static enum AlignmentState {
		IDLE, ACTIVE;
	}
	protected final FusedSensor<Double> sensor;
	protected final AligningCamera camera;
	protected final FusibleNavX navX;
	protected AlignmentState state;
	protected PIDSourceType pidSourceType;

	public AlignmentSystem(AligningCamera camera, FusibleNavX navX) {
		sensor = new FusedSensor<Double>(camera, navX);
		this.camera = camera;
		this.navX = navX;
		state = AlignmentState.IDLE;
	}

	public void start() {
		state = AlignmentState.ACTIVE;
		navX.setMode(NavxMode.ALIGNMENT);
		navX.setTargetAngle(camera.getDegrees());
	}

	public void cancel() {
		state = AlignmentState.IDLE;
		navX.setMode(NavxMode.STANDARD);
	}

	public double getDegrees() {
		try {
			return sensor.getValueDangerously();
		}
		catch (InvalidSensorException ex) {
			LogKitten.ex(ex);
			return Double.NaN;
		}
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
