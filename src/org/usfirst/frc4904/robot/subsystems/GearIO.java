package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.OverridableCommand;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIO extends Subsystem implements OverridableCommand {
	protected final Motor intakeRoller;
	protected final DoubleSolenoid gullWings;
	protected final DoubleSolenoid ramp;
	protected GearState currentState;
	protected RampState currentRampState;
	public static boolean rampOverride;

	public GearIO(Motor intakeRoller, DoubleSolenoid gullWings, DoubleSolenoid ramp) {
		this.intakeRoller = intakeRoller;
		this.gullWings = gullWings;
		this.ramp = ramp;
		setState(GearState.TRANSPORT);
		if (GearIO.rampOverride == false) {
			setRampState(RampState.EXTENDED);
		}
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

	@Override
	public boolean getOverride() {
		return GearIO.rampOverride;
	}

	@Override
	public void setOverride(boolean setValue) {
		GearIO.rampOverride = setValue;
	}
}
