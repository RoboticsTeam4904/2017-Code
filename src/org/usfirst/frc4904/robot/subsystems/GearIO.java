package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIO extends Subsystem {
	protected final Motor intakeRoller;
	protected final DoubleSolenoid gullWings;
	protected final DoubleSolenoid ramp;
	protected GearState currentState;
	protected RampState currentRampState;

	public GearIO(Motor intakeRoller, DoubleSolenoid gullWings, DoubleSolenoid ramp) {
		this.intakeRoller = intakeRoller;
		this.gullWings = gullWings;
		this.ramp = ramp;
		setState(GearState.TRANSPORT);
		setRampState(RampState.EXTENDED);
	}

	public static enum GearState {
		INTAKE(0.75, DoubleSolenoid.Value.kForward), OUTTAKE(0, DoubleSolenoid.Value.kReverse), TRANSPORT(0,
			DoubleSolenoid.Value.kForward);
		private final double intakeRollerSpeed;
		private final DoubleSolenoid.Value gullWingsValue;

		private GearState(double intakeRollerSpeed, DoubleSolenoid.Value gullWingsValue) {
			this.intakeRollerSpeed = intakeRollerSpeed;
			this.gullWingsValue = gullWingsValue;
		}

		public double getIntakeRollerSpeed() {
			return intakeRollerSpeed;
		}

		public DoubleSolenoid.Value getGullWingsValue() {
			return gullWingsValue;
		}
	}

	public static enum RampState {
		EXTENDED(DoubleSolenoid.Value.kForward), RETRACTED(DoubleSolenoid.Value.kReverse);
		private final DoubleSolenoid.Value value;

		private RampState(DoubleSolenoid.Value value) {
			this.value = value;
		}

		public DoubleSolenoid.Value getValue() {
			return value;
		}
	}

	public GearState getState() {
		return currentState;
	}

	public void setState(GearState state) {
		intakeRoller.set(state.getIntakeRollerSpeed());
		gullWings.set(state.getGullWingsValue());
		currentState = state;
	}

	public RampState getRampState() {
		return currentRampState;
	}

	public void setRampState(RampState state) {
		ramp.set(state.getValue());
		currentRampState = state;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
