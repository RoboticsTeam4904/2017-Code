package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class HighConveyor extends Motor {
	public static enum HighConveyorState {
		IDLE, ACTIVE;
	}
	public static final CANTalon MOTOR = new CANTalon(0);
	public static final double LOAD_SPEED = 0.5;
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

	public HighConveyorState getState() {
		return state;
	}
}
