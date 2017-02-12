package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hopper extends Subsystem {
	protected final DoubleSolenoid leftSolenoid;
	protected final DoubleSolenoid rightSolenoid;

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

	public Hopper(DoubleSolenoid leftSolenoid, DoubleSolenoid rightSolenoid) {
		this.leftSolenoid = leftSolenoid;
		this.rightSolenoid = rightSolenoid;
		setState(HopperState.BALLIO);
	}

	public HopperState getState() {
		return currentState;
	}

	public void setState(HopperState desiredState) {
		leftSolenoid.set(desiredState.getValue());
		rightSolenoid.set(desiredState.getValue());
		currentState = desiredState;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
