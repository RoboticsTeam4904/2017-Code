package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.HighFlywheelSpinup;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;

public class HighFlywheel extends Motor {
	public static enum HighFlywheelState {
		IDLE(new Idle(RobotMap.Component.highFlywheel)), ACTIVE(new HighFlywheelSpinup());
		private Command command;

		HighFlywheelState(Command command) {
			this.command = command;
		}

		public Command getCommand() {
			return command;
		}
	}
	// TODO: Update ports
	public static final CANTalon[] MOTORS = {new CANTalon(0), new CANTalon(0)};
	// TODO: Tune this
	public static final double SHOOTING_SPEED = 0.75;
	protected HighFlywheelState state;

	public HighFlywheel() {
		super(HighFlywheel.MOTORS);
		state = HighFlywheelState.IDLE;
	}

	public HighFlywheel(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		state = HighFlywheelState.IDLE;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(state.getCommand());
	}

	public boolean isReady() {
		return get() >= HighFlywheel.SHOOTING_SPEED;
	}

	public void setState(HighFlywheelState state) {
		this.state.getCommand().cancel();
		this.state = state;
		this.state.getCommand().start();
	}

	public HighFlywheelState getState() {
		return state;
	}
}
