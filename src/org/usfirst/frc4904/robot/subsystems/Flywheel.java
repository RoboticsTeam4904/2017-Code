package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends Motor {
	public Flywheel(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void initializeSpin() {
		this.set(1.0);
	}

	public void stopSpin() {
		this.set(0.0);
	}
}
