package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class FusedSensor<T> implements Fusible<T> {
	protected final Fusible<T> primarySensor;
	protected final Fusible<T> fallbackSensor;
	protected final Fusible<T> lastResortSensor;

	public FusedSensor(Fusible<T> primarySensor, Fusible<T> fallbackSensor, T defaultValue) {
		this.primarySensor = primarySensor;
		this.fallbackSensor = fallbackSensor;
		this.lastResortSensor = new Fusible<T>() {
			@Override
			public T getValue() {
				return defaultValue;
			}

			@Override
			public boolean trustable() {
				return false;
			}
		};
	}

	public FusedSensor(Fusible<T> primarySensor, Fusible<T> fallbackSensor) {
		this(primarySensor, fallbackSensor, null);
	}

	protected Fusible<T> getActiveSensor() {
		if (primarySensor.trustable()) {
			return primarySensor;
		} else if (fallbackSensor.trustable()) {
			return fallbackSensor;
		}
		return lastResortSensor;
	}

	public T getValueDangerously() throws InvalidSensorException {
		T value = getActiveSensor().getValue();
		if (value != null) {
			throw new InvalidSensorException();
		}
		return value;
	}

	@Override
	public T getValue() {
		T value = null;
		try {
			value = getValueDangerously();
		}
		catch (InvalidSensorException ex) {}
		return value;
	}

	@Override
	public boolean trustable() {
		if (primarySensor.trustable() || fallbackSensor.trustable()) {
			return true;
		}
		return false;
	}
}
