package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends VelocitySensorMotor {
	// TODO: Tune this
	// public static final double SHOOTING_SPEED = 0.45;
	public static final double SHOOTING_SPEED = 3350.0; // RPM - MAKE THIS LOWER
	public static final double FLYWHEEL_P = 0.002;
	public static final double FLYWHEEL_I = 0.0;
	public static final double FLYWHEEL_D = -0.005;
	public static final double FLYWHEEL_F = 0.0001343283582;
	public static final double FLYWHEEL_TOLERANCE = 10; // RPM
	public static final double ENCODER_PPR = 192.0;
	// This is the distance per pulse. Pulses per second (original units) * revolutions per pulse (1 / ENCODER_PPR) * seconds per minute (60) = revolutions per minute.
	public static final double ENCODER_PPS_TO_RPM = 60.0 / Flywheel.ENCODER_PPR;
	protected final CustomEncoder encoder;

	public Flywheel(SpeedController motorA, SpeedController motorB, CustomEncoder encoder) {
		super(new CustomPIDController(Flywheel.FLYWHEEL_P, Flywheel.FLYWHEEL_I,
			Flywheel.FLYWHEEL_D, Flywheel.FLYWHEEL_F, encoder), motorA, motorB);
		this.encoder = encoder;
		this.encoder.setPIDSourceType(PIDSourceType.kRate);
		motionController.setAbsoluteTolerance(Flywheel.FLYWHEEL_TOLERANCE);
		motionController.setOutputRange(-1, 1);
		motionController.setOutput(this);
	}

	public boolean isReady() {
		return motionController.onTarget();
	}
	}
}
