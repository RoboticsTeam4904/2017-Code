package org.usfirst.frc4904.sovereignty;


import java.util.HashMap;
import java.util.Map;
import org.usfirst.frc4904.sovereignty.PIDContainerOrchestrator.PIDContainer.PIDValueModifier;

public class PIDContainerOrchestrator {
	private static PIDContainerOrchestrator singleton;
	private final Map<String, PIDContainer> containers;

	private PIDContainerOrchestrator() {
		containers = new HashMap<String, PIDContainer>();
	}

	public static PIDContainerOrchestrator getInstance() {
		if (PIDContainerOrchestrator.singleton == null) {
			PIDContainerOrchestrator.singleton = new PIDContainerOrchestrator();
		}
		return PIDContainerOrchestrator.singleton;
	}

	public PIDContainer createContainer(String system, boolean locked) {
		PIDContainer container = new PIDContainer(system, locked);
		addContainer(container);
		return container;
	}

	public void addContainer(PIDContainer container) {
		containers.put(container.getSystem(), container);
	}

	public void addContainer(String system, PIDContainer container) {
		containers.put(system, container);
	}

	public void getContainer(String system) {
		containers.get(system);
	}

	public void pushValues(PIDValueModifier modifier) {
		containers.forEach((key, value) -> {
			value.pushValues(modifier);
		});
	}

	public void pullValues(PIDValueModifier modifier) {
		containers.forEach((key, value) -> {
			if (!value.isLocked()) {
				value.pullValue(modifier);
			}
		});
	}

	public static class PIDContainer {
		private final Map<String, Double> values;
		private final String system;
		private final boolean locked;

		public static interface PIDValueModifier {
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

		public void pushValues(PIDValueModifier modifier) {
			values.forEach((key, value) -> {
				modifier.pushValue(key, value);
			});
		}

		public void pullValue(PIDValueModifier modifier) {
			values.forEach((key, value) -> {
				if (!locked) {
					values.replace(key, modifier.pullValue(key));
				}
			});
		}

		public boolean isLocked() {
			return locked;
		}

		public String getSystem() {
			return system;
		}

		public PIDContainer set(PIDValueType valueType, double value) {
			if (!locked) {
				values.put(system + "_" + valueType.getKey(), value);
			}
			return this;
		}

		public double get(PIDValueType valueType) {
			return values.getOrDefault(valueType, 0.0);
		}
	}
}
