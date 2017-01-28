package org.usfirst.frc4904.sovereignty;


import java.util.HashMap;
import java.util.Map;

public class PIDContainer {
	private final Map<String, Double> values;
	private final String system;
	private final boolean locked;

	public static interface PIDContainerModifier {
		void pushValue(String key, double value);

		double pullValue(String key);
	}

	// TODO: Consider renaming @ajnadel
	public static enum PIDValueType {
		P, I, D, F, SETPOINT, TOLERANCE;
		public String getKey() {
			return name();
		}
	}

	public PIDContainer(Map<String, Double> values, String system, boolean locked) {
		this.system = system;
		this.values = values;
		this.locked = locked;
	}

	public PIDContainer(String system, boolean locked) {
		this(new HashMap<String, Double>(), system, locked);
	}

	public void pushValues(PIDContainerModifier modifier) {
		values.forEach((key, value) -> {
			modifier.pushValue(key, value);
		});
	}

	public void pullValues(PIDContainerModifier modifier) {
		values.forEach((key, value) -> {
			values.replace(key, modifier.pullValue(key));
		});
	}

	public String getSystem() {
		return system;
	}

	public PIDContainer set(PIDValueType valueType, double value) {
		values.put(system + "_" + valueType.getKey(), value);
		return this;
	}

	public double get(PIDValueType valueType) {
		return values.getOrDefault(valueType, 0.0);
	}

	public boolean isLocked() {
		return locked;
	}
}