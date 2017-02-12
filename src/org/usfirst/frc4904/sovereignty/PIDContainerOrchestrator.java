package org.usfirst.frc4904.sovereignty;


import java.util.HashMap;
import java.util.Map;
import org.usfirst.frc4904.sovereignty.PIDContainer.PIDContainerModifier;

public class PIDContainerOrchestrator {
	private static PIDContainerOrchestrator singleton;
	private final Map<String, PIDContainer> containers;

	private PIDContainerOrchestrator() {
		containers = new HashMap<String, PIDContainer>();
	}

	public static synchronized PIDContainerOrchestrator getInstance() {
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

	public void pushValues(PIDContainerModifier modifier) {
		containers.values().forEach((value) -> {
			value.pushValues(modifier);
		});
	}

	public void pullValues(PIDContainerModifier modifier) {
		containers.values().forEach((value) -> {
			if (!value.isLocked()) {
				value.pullValues(modifier);
			}
		});
	}
}
