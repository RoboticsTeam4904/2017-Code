package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.RampSet;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import org.usfirst.frc4904.standard.subsystems.OverridableSubsystem;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIO extends OverridableSubsystem {
	public final Motor intakeRoller;
	public final DoubleSolenoid gullWings;
	public final Ramp ramp;
	protected GearState currentState;
	protected RampState currentRampState;

	public GearIO(Motor intakeRoller, DoubleSolenoid gullWings, DoubleSolenoid ramp) {
		this.intakeRoller = intakeRoller;
		this.gullWings = gullWings;
		this.ramp = new Ramp(ramp);
		setState(GearState.TRANSPORT);
		setRampState(GearIO.RampState.EXTENDED);
	}

	public static enum GearState {
		INTAKE(0.75, GearState.GULL_WINGS_CLOSED), OUTTAKE(0, GearState.GULL_WINGS_OPEN), TRANSPORT(0,
			GearState.GULL_WINGS_CLOSED), GEARCLEAR(1, GearState.GULL_WINGS_CLOSED);
		private static final DoubleSolenoid.Value GULL_WINGS_CLOSED = DoubleSolenoid.Value.kForward;
		private static final DoubleSolenoid.Value GULL_WINGS_OPEN = DoubleSolenoid.Value.kReverse;
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
		new MotorConstant(intakeRoller, state.getIntakeRollerSpeed()).start();
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

	private class Ramp extends Subsystem {
		protected final DoubleSolenoid solenoid;

		public Ramp(DoubleSolenoid solenoid) {
			this.solenoid = solenoid;
		}

		public void set(DoubleSolenoid.Value value) {
			solenoid.set(value);
		}

		@Override
		protected void initDefaultCommand() {
			setDefaultCommand(new RampSet(GearIO.RampState.EXTENDED));
		}
	}
}
