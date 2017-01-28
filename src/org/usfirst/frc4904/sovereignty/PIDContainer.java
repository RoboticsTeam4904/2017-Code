package org.usfirst.frc4904.sovereignty;


import java.util.HashMap;
import java.util.Map;

public class PIDContainer {
	private final Map<String, Double> values;
	private final String system;

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

	public PIDContainer(Map<String, Double> values, String system) {
		this.system = system;
		this.values = values;
	}

	public PIDContainer(String system) {
		this(new HashMap<String, Double>(), system);
	}

	public void pushValues(PIDContainerModifier modifier) {
		values.forEach((key, value) -> {
			modifier.pushValue(key, value);
		});
	}

	public void pullValue(PIDContainerModifier modifier) {
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
}