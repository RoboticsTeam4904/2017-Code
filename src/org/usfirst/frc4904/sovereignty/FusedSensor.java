package org.usfirst.frc4904.sovereignty;


public class FusedSensor<T> implements FusibleSensor<T> {
	protected final FusibleSensor<T> primarySensor;
	protected final FusibleSensor<T> fallbackSensor;
	protected final FusibleSensor<T> lastResortSensor;

	public FusedSensor(FusibleSensor<T> primarySensor, FusibleSensor<T> fallbackSensor, T defaultValue) {
		this.primarySensor = primarySensor;
		this.fallbackSensor = fallbackSensor;
		this.lastResortSensor = new FusibleSensor<T>() {
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

	public FusedSensor(FusibleSensor<T> primarySensor, FusibleSensor<T> fallbackSensor) {
		this(primarySensor, fallbackSensor, null);
	}

	protected FusibleSensor<T> getActiveSensor() {
		if (primarySensor.trustable()) {
			return primarySensor;
		} else if (fallbackSensor.trustable()) {
			return fallbackSensor;
		}
		return lastResortSensor;
	}

	@Override
	public T getValue() {
		return getActiveSensor().getValue();
	}

	@Override
	public boolean trustable() {
		if (primarySensor.trustable() || fallbackSensor.trustable()) {
			return true;
		}
		return false;
	}
}
