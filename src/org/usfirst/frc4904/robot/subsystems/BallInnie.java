package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.SpeedController;

public class BallInnie extends Motor {
	public BallInnie(SpeedController motor) {
		super(motor);
	}

	public void initializeSpin() {
		this.set(1.0);
	}

	public void stopSpin() {
		this.set(0.0);
	}
}
