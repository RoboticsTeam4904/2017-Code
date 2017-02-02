package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.ServoSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIO extends Subsystem {
	public final Motor directionalRoller;
	public final Motor elevatorAndIntakeRoller;
	public final Motor hopperRollers;
	public final ServoSubsystem doorServo;

	public static enum DoorState {
		INTAKE(0), OUTTAKE(71.03);
		public final double angle; // the architecture allowing the enum states to have values

		private DoorState(double angle) {
			this.angle = angle;
		}
	}
	protected DoorState currentState;

	public DoorState getState() {
		return currentState;
	}

	public void doorSet(DoorState desiredState) {
		doorServo.setAngle(desiredState.angle);
		currentState = desiredState;
	}

	public BallIO(Motor directionalRoller, Motor elevatorAndIntakeRoller, Motor hopperRollers, ServoSubsystem doorServo) {
		this.directionalRoller = directionalRoller;
		this.elevatorAndIntakeRoller = elevatorAndIntakeRoller;
		this.hopperRollers = hopperRollers;
		this.doorServo = doorServo;
		doorSet(BallIO.DoorState.INTAKE);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
