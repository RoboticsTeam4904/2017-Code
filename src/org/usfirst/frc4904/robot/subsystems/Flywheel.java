package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.motor.MotorIdle;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends VelocitySensorMotor {
	// TODO: Tune this
	public static final double SHOOTING_SPEED = 0.55;
	public static final double FLYWHEEL_P = 0.0;
	public static final double FLYWHEEL_I = 0.0;
	public static final double FLYWHEEL_D = 0.0;
	public static final double FLYWHEEL_TOLERANCE = 0;
	protected final CustomEncoder encoder;

	public Flywheel(SpeedController motorA, SpeedController motorB, CustomEncoder encoder) {
		super(new CustomPIDController(Flywheel.FLYWHEEL_P, Flywheel.FLYWHEEL_I,
			Flywheel.FLYWHEEL_D, encoder), motorA, motorB);
		this.encoder = encoder;
		this.encoder.setPIDSourceType(PIDSourceType.kRate);
		motionController.setAbsoluteTolerance(Flywheel.FLYWHEEL_TOLERANCE);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new MotorIdle(this));
	}

	public boolean isReady() {
		return motionController.onTarget();
	}
}
