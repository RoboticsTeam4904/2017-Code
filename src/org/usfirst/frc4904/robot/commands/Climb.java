package org.usfirst.frc4904.robot.commands;


import java.util.function.Supplier;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

public class Climb extends MotorSet {
	protected final Supplier<Double> axis;

	public Climb(Supplier<Double> axis) {
		super(RobotMap.Component.climber);
		this.axis = axis;
	}

	@Override
	protected void initialize() {
		if (RobotMap.Component.gearIO.isNotOverridden()) {
			RobotMap.Component.gearIO.setRampState(GearIO.RampState.RETRACTED);
		}
	}

	@Override
	protected void execute() {
		set(Math.max(0, axis.get())); // No, really, always feed positive values
										// to the climber motor
		super.execute();
	}
}
