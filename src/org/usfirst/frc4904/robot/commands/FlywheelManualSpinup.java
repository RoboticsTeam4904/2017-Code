package org.usfirst.frc4904.robot.commands;


import java.util.function.Supplier;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

public class FlywheelManualSpinup extends MotorSet {
	public Supplier<Double> speedSupplier;

	public FlywheelManualSpinup(Supplier<Double> speedSupplier) {
		super(RobotMap.Component.flywheel);
		this.speedSupplier = speedSupplier;
	}

	@Override
	protected void execute() {
		super.execute();
		set(speedSupplier.get());
	}
}
