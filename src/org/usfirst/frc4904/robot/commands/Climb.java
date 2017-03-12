package org.usfirst.frc4904.robot.commands;


import java.util.function.Supplier;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

public class Climb extends MotorSet {
	protected final Supplier<Double> axis;
	protected static final double MINIMUM_AXIS_VALUE_TO_FLIP_GEARIO_RAMP = 0.25;

	public Climb(Supplier<Double> axis) {
		super(RobotMap.Component.climber);
		this.axis = axis;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		set(Math.max(0, axis.get())); // No, really, always feed positive values
										// to the climber motor
		if (axis.get() >= Climb.MINIMUM_AXIS_VALUE_TO_FLIP_GEARIO_RAMP && RobotMap.Component.gearIO.isNotOverridden()) {
			new SetRampState(GearIO.RampState.RETRACTED).start();
		}
		super.execute();
	}
}
