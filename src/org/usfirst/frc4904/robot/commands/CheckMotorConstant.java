package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheckMotorConstant extends Command { // TODO: Add comments to make clearer.
	CustomEncoder encoder;
	Motor motor;
	double firstMotorSpeed;
	double secondMotorSpeed;
	double firstEncoderReading;
	double secondEncoderReading;
	double secondsPerReading;
	boolean finished;
	double allowedError;

	public CheckMotorConstant(Motor motor, CustomEncoder encoder, double firstMotorSpeed, double multiplier,
		double allowedError, double secondsPerReading) {
		super("Checking motor " + motor.getName());
		requires(motor);
		this.encoder = encoder;
		this.motor = motor;
		this.firstMotorSpeed = firstMotorSpeed;
		secondMotorSpeed = firstMotorSpeed * multiplier;
		this.allowedError = allowedError;
		this.secondsPerReading = secondsPerReading;
		finished = false;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if (timeSinceInitialized() < secondsPerReading) {
			motor.set(firstMotorSpeed);
			firstEncoderReading = encoder.getRate();
		} else if (timeSinceInitialized() > secondsPerReading && timeSinceInitialized() < secondsPerReading * 2) {
			motor.set(secondMotorSpeed);
			secondEncoderReading = encoder.getRate();
		} else {
			motor.set(0);
			if (Math.abs(
				1 - ((secondMotorSpeed / firstMotorSpeed) / (secondEncoderReading / firstEncoderReading))) < allowedError) {
				LogKitten.wtf(motor.getName() + " checks successful.");
			} else {
				LogKitten.wtf("WARNING: " + motor.getName() + " checks failed.");
			}
			finished = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
