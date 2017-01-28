package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.sovereignty.PIDContainer.PIDValueType;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.PIDSensor;
import edu.wpi.first.wpilibj.PIDSource;

public class PIDContainerWrapper {
	private final PIDContainer container;
	private final CustomPIDController controller;

	public PIDContainerWrapper(PIDContainer container, PIDSource source) {
		this(container, new CustomPIDController(source));
	}

	public PIDContainerWrapper(PIDContainer container, PIDSensor sensor) {
		this(container, new CustomPIDController(sensor));
	}

	public PIDContainerWrapper(PIDContainer container, CustomPIDController controller) {
		this.container = container;
		this.controller = controller;
	}

	public CustomPIDController getController() {
		return controller;
	}

	public void updateController() {
		controller.setPIDF(container.get(PIDValueType.P), container.get(PIDValueType.I), container.get(PIDValueType.D),
			container.get(PIDValueType.F));
		controller.setSetpoint(container.get(PIDValueType.SETPOINT));
		controller.setAbsoluteTolerance(container.get(PIDValueType.TOLERANCE));
	}
}
