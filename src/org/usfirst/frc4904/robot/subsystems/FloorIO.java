package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FloorIO extends Subsystem {
	public final Motor roller;
	public final DoubleSolenoid piston;
	protected FloorState currentState;
	private static final DoubleSolenoid.Value LOWERED = DoubleSolenoid.Value.kForward;
	private static final DoubleSolenoid.Value RAISED = DoubleSolenoid.Value.kReverse;

	public FloorIO(Motor roller, DoubleSolenoid piston) {
		this.roller = roller;
		this.piston = piston;
		setState(FloorState.TRANSPORT);
	}

	public static enum FloorState {
		INTAKE(0.75, FloorIO.LOWERED), OUTTAKE(-0.75, FloorIO.LOWERED), TRANSPORT(0, FloorIO.RAISED);
		private final double rollerSpeed;
		private final DoubleSolenoid.Value pistonValue;

		private FloorState(double rollerSpeed, DoubleSolenoid.Value pistonValue) {
			this.rollerSpeed = rollerSpeed;
			this.pistonValue = pistonValue;
		}

		public double getRollerSpeed() {
			return rollerSpeed;
		}

		public DoubleSolenoid.Value getPistonValue() {
			return pistonValue;
		}
	}

	public FloorState getState() {
		return currentState;
	}

	public void setState(FloorState state) {
		new MotorConstant(roller, state.getRollerSpeed()).start();
		piston.set(state.getPistonValue());
		currentState = state;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
