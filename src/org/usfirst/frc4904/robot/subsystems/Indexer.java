package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.motor.MotorIdle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.SpeedController;

public class Indexer extends Motor {
	public static final double LOAD_SPEED = 0.5;
	public static final double UNLOAD_SPEED = -0.5;
	public static final double UNLOAD_TIME = 0.2;

	public Indexer(SpeedController motor) {
		super(motor);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MotorIdle(this));
	}
}
