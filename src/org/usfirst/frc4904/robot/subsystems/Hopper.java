package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hopper extends Subsystem {
	protected final DoubleSolenoid solenoid;
	protected final HopperCapacitySensor capacitySensor;
	public static final double AGITATE_DELAY_MS = 500;

	public static enum HopperState {
		BALLIO(DoubleSolenoid.Value.kReverse), SHOOTER(DoubleSolenoid.Value.kForward);
		private final DoubleSolenoid.Value value;

		private HopperState(DoubleSolenoid.Value value) {
			this.value = value;
		}

		public DoubleSolenoid.Value getValue() {
			return value;
		}
	}
	protected HopperState currentState;
	protected double currentCapacity;

	public Hopper(DoubleSolenoid solenoid, HopperCapacitySensor capacitySensor) {
		this.solenoid = solenoid;
		this.capacitySensor = capacitySensor;
		setState(HopperState.BALLIO);
	}

	public HopperState getState() {
		return currentState;
	}

	public double getCapacity() {
		try {
			currentCapacity = capacitySensor.getCapacity();
			return currentCapacity;
		}
		catch (Exception e) {
			LogKitten.ex(e);
			return 0.0;
		}
	}

	public void setState(HopperState desiredState) {
		solenoid.set(desiredState.getValue());
		currentState = desiredState;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
