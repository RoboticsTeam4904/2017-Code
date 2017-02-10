package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.FusibleNavX.NavxMode;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AligningSystem implements PIDSource {
	public static enum AligningState {
		IDLE, ACTIVE;
	}
	protected final FusedSensor<Double> sensor;
	protected final AligningCamera camera;
	protected final FusibleNavX navX;
	protected AligningState state;
	protected PIDSourceType pidSourceType;

	public AligningSystem(AligningCamera camera, FusibleNavX navX) {
		sensor = new FusedSensor<Double>(camera, navX);
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
