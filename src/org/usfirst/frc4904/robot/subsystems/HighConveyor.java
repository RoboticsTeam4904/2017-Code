package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.HighConveyorLoad;
import org.usfirst.frc4904.robot.commands.HighConveyorLoadPassive;
import org.usfirst.frc4904.robot.commands.HighConveyorUnload;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;

public class HighConveyor extends Motor {
	public static enum HighConveyorState {
		IDLE(new Idle(RobotMap.Component.highConveyor)), UNLOAD(new HighConveyorUnload()), PASSIVE(
			new HighConveyorLoadPassive()), ACTIVE(new HighConveyorLoad());
		private Command command;

		HighConveyorState(Command command) {
			this.command = command;
		}

		public Command getCommand() {
			return command;
		}
	}
	public static final CANTalon MOTOR = new CANTalon(0);
	public static final double LOAD_SPEED = 0.5;
	public static final double LOAD_SPEED_PASSIVE = 0.33;
	public static final double UNLOAD_SPEED = -0.5;
	public static final double UNLOAD_TIME = 0.2;
	protected HighConveyorState state = HighConveyorState.IDLE;

	public HighConveyor() {
		this(HighConveyor.MOTOR);
	}

	public HighConveyor(SpeedController motor) {
		super(motor);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}

	public void setState(HighConveyorState state) {
		this.state.getCommand().cancel();
		this.state = state;
		this.state.getCommand().start();
	}

	public HighConveyorState getState() {
		return state;
	}
}
