package org.usfirst.frc4904.robot.commands;


import java.util.function.Supplier;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class FlywheelSpinup extends MotorConstant implements OverridableCommand {
	protected static boolean flywheelSpeedOverride = false;

	public FlywheelSpinup() {
		super("FlywheelSpinup", RobotMap.Component.flywheel, Flywheel.SHOOTING_SPEED);
	}

	public FlywheelSpinup(Supplier<Double> speedSupplier) {
		super(RobotMap.Component.flywheel, speedSupplier.get());
	}

	@Override
	public void setOverride(boolean flywheelSpeedOverride) {
		FlywheelSpinup.flywheelSpeedOverride = flywheelSpeedOverride;
	}

	@Override
	public boolean isOverridden() {
		return FlywheelSpinup.flywheelSpeedOverride;
	}
}
