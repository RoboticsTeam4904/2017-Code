package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;
import org.usfirst.frc4904.standard.custom.sensors.PIDSensor;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TrimmablePIDSensor implements PIDSensor {
	
	private double trimIncrement = 0.0;
	private double trimValue = 0.0;
	private final PIDSensor sensor;
	
	public TrimmablePIDSensor(PIDSensor sensor) {
		this.sensor = sensor;
	}
	
	public TrimmablePIDSensor(PIDSource source) {
		this(new PIDSensor.PIDSourceWrapper(source));
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		sensor.setPIDSourceType(pidSource);
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return sensor.getPIDSourceType();
	}
	
	@Override
	public double pidGet() {
		return sensor.pidGet() + trimValue;
	}
	
	@Override
	public double pidGetSafely() throws InvalidSensorException {
		return sensor.pidGetSafely() + trimValue;
	}
	
	public void setTrimIncrement(double increment) {
		trimIncrement = increment;
	}
	
	public double getTrimIncrement() {
		return trimIncrement;
	}
	
	public void adjustTrim(boolean positive) {
		adjustTrim(positive ? trimIncrement : -trimIncrement);
	}
	
	public void adjustTrim(double trim) {
		trimValue += trim;
	}
	
	public void setTrim(double trim) {
		trimValue = trim;
	}
}
