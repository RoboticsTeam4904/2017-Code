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

	public PIDContainerWrapper(CustomPIDController controller, String name) {
		this.controller = controller;
		container = new PIDContainer(name)
			.set(PIDValueType.P, controller.getP())
			.set(PIDValueType.I, controller.getI())
			.set(PIDValueType.D, controller.getD())
			.set(PIDValueType.F, controller.getF())
			.set(PIDValueType.SETPOINT, controller.getSetpoint())
			.set(PIDValueType.TOLERANCE, controller.getAbsoluteTolerance());
	}

	public PIDContainer getContainer() {
		return container;
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
