package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import edu.wpi.first.wpilibj.PIDSource;

public class Climb extends MotorSet {
	protected final PIDSource axis;

	public Climb(PIDSource axis) {
		super(RobotMap.Component.climber);
		this.axis = axis;
	}

	@Override
	public void execute() {
		set(Math.max(0, axis.pidGet())); // No, really, always feed positive values to the climber motor
		super.execute();
	}
}
