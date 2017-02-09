package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class FusedSensor<T> implements Fusible<T> {
	protected final Fusible<T> primarySensor;
	protected final Fusible<T> fallbackSensor;
	protected final Fusible<T> lastResortSensor;

	public FusedSensor(T defaultValue, Fusible<T> primarySensor, Fusible<T> secondarySensor, Fusible<T>... sensors) {
		Fusible<T> lastSensor = sensors[sensors.length - 1];
		for (int i = sensors.length - 2; i >= 0; i--) {
			lastSensor = new FusedSensor<>(sensors[i], lastSensor);
		}
		lastSensor = new FusedSensor<>(secondarySensor, lastSensor);
		this.primarySensor = primarySensor;
		this.fallbackSensor = lastSensor;
		this.lastResortSensor = new Fusible<T>() {
			@Override
			public T getValue() {
				return defaultValue;
			}

			@Override
			public boolean trustable() {
				return false;
			}

			@Override
			public String getName() {
				return "LastResortSensor";
			}
		};
	}

	public FusedSensor(T defaultValue, Fusible<T> primarySensor, Fusible<T> fallbackSensor) {
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

			@Override
			public String getName() {
				return "LastResortSensor";
			}
		};
	}

	public FusedSensor(Fusible<T> primarySensor, Fusible<T> fallbackSensor) {
		this(null, primarySensor, fallbackSensor);
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
		Fusible<T> activeSensor = getActiveSensor();
		T value = activeSensor.getValue();
		if (value == null) {
			throw new InvalidSensorException(activeSensor.getName() + " returned null.");
		}
		return value;
	}

	@Override
	public T getValue() {
		T value = null;
		try {
			value = getValueDangerously();
		}
		catch (InvalidSensorException ex) {
			LogKitten.ex(ex);
		}
		return value;
	}

	@Override
	public boolean trustable() {
		if (primarySensor.trustable() || fallbackSensor.trustable()) {
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "FusedSensor";
	}
}
