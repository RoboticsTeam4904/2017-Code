package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.motor.MotorIdle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends Motor {
	// TODO: Tune this
	public static final double SHOOTING_SPEED = 0.75;

	public Flywheel(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new MotorIdle(this));
	}

	public boolean isReady() {
		return get() >= Flywheel.SHOOTING_SPEED;
	}
}
