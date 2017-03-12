package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.Overridable;
import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hopper extends Subsystem implements Overridable {
	protected final DoubleSolenoid solenoid;
	public static final double AGITATE_DELAY_MS = 500;
	protected boolean isOverridden;

	public static enum HopperState {
		BALLIO(DoubleSolenoid.Value.kForward), SHOOTER(DoubleSolenoid.Value.kReverse);
		private final DoubleSolenoid.Value value;

		private HopperState(DoubleSolenoid.Value value) {
			this.value = value;
		}

		public DoubleSolenoid.Value getValue() {
			return value;
		}
	}
	protected HopperState currentState;

	public Hopper(DoubleSolenoid solenoid) {
		this.solenoid = solenoid;
		setState(HopperState.BALLIO);
		isOverridden = false;
	}

	public HopperState getState() {
		return currentState;
	}

	public void setState(HopperState desiredState) {
		solenoid.set(desiredState.getValue());
		currentState = desiredState;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}

	@Override
	public void setOverridden(boolean isOverridden) {
		this.isOverridden = isOverridden;
	}

	@Override
	public boolean isOverridden() {
		return isOverridden;
	}
}
