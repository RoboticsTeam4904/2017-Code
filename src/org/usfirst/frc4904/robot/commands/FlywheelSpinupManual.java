package org.usfirst.frc4904.robot.commands;


import java.util.function.Supplier;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

public class FlywheelSpinupManual extends MotorSet {
	public Supplier<Double> speedSupplier;
	protected final static double MANUAL_SPEED_SLIDER_THRESHOLD = 0.1;
	protected final static double DEFAULT_MANUAL_SPEED = 0.45;

	public FlywheelSpinupManual(Supplier<Double> speedSupplier) {
		super(RobotMap.Component.flywheel);
		this.speedSupplier = speedSupplier;
	}

	@Override
	protected void execute() {
		super.execute();
		double setValue = speedSupplier.get();
		if (setValue < FlywheelSpinupManual.MANUAL_SPEED_SLIDER_THRESHOLD) {
			setValue = FlywheelSpinupManual.DEFAULT_MANUAL_SPEED;
		}
		set(setValue);
	}
}
