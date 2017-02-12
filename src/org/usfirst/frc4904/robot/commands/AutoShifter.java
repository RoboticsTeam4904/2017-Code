package org.usfirst.frc4904.robot.commands;


import java.util.LinkedList;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.NavX;
import org.usfirst.frc4904.standard.subsystems.chassis.AutoSolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

public class AutoShifter extends Command {
	protected final CustomEncoder leftEncoder;
	protected final CustomEncoder rightEncoder;
	protected final AutoSolenoidShifters shifter;
	protected final NavX navX;
	protected final LinkedList<Double> speeds = new LinkedList<Double>();
	public static final double RAPID_ACCELERATION = 0.24881632653;// in Gs--TODO
	public static final double RAPID_DECELERATION = -0.06216;// in Gs--TODO
	public static final double RATE_DIFF = 100; // encoder ticks--WIP
	public static final double SUPER_SLOW_RATE = 2; // ft per sec
	public static final double MEDIUM_RATE = AutoShifter.SUPER_SLOW_RATE * 2;
	public static final double FAST_RATE = AutoShifter.SUPER_SLOW_RATE * 4;
	public static final double SLOW_THROTTLE = 0.4;
	public static final double MEDIUM_THROTTLE = AutoShifter.SLOW_THROTTLE * 1.875;
	public static final double FAST_THROTTLE = AutoShifter.SLOW_THROTTLE * 2;
	public static final double SPEED_LIST_MAX_LENGTH = 5;
	public static final double LAST_MANUAL_SHIFT_TIME = 5000; // TODO
	public static final double LAST_AUTO_SHIFT_TIME = 500;// TODO

	public AutoShifter(AutoSolenoidShifters shifter, CustomEncoder leftEncoder, CustomEncoder rightEncoder, NavX navX) {
		this.shifter = shifter;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.navX = navX;
	}

	public AutoShifter() {
		this(RobotMap.Component.shifter, RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder,
			RobotMap.Component.navx);
	}

	@Override
	protected void execute() {
		double speed = (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
		double acceleration = navX.getWorldLinearAccelY();
		speeds.add(speed);
		while (speeds.size() > AutoShifter.SPEED_LIST_MAX_LENGTH) {
			speeds.removeFirst();
		}
		if (leftEncoder.getRate() - rightEncoder.getRate() < AutoShifter.RATE_DIFF
			&& shifter.timeSinceLastManualShift() > AutoShifter.LAST_MANUAL_SHIFT_TIME
			&& shifter.timeSinceLastAutoShift() > AutoShifter.LAST_AUTO_SHIFT_TIME) {// only if driving straight and we haven't manually shifted in 5 seconds or autoshifted in 0.5 seconds
			double throttle = 0.0;
			for (double motorSpeed : RobotMap.Component.chassis.getMotorSpeeds()) {
				throttle += motorSpeed;
			}
			throttle /= RobotMap.Component.chassis.getMotorSpeeds().length;
			if (Math.abs(speed) > AutoShifter.MEDIUM_RATE && (acceleration) > AutoShifter.RAPID_ACCELERATION
				&& throttle > AutoShifter.FAST_THROTTLE) {
				shifter.shift(SolenoidShifters.ShiftState.UP, true);
			} else if ((Math.abs(speed) < AutoShifter.FAST_RATE && (acceleration) < AutoShifter.RAPID_DECELERATION
				&& throttle > AutoShifter.MEDIUM_THROTTLE)
				|| (Math.abs(speed) < AutoShifter.SUPER_SLOW_RATE && throttle < AutoShifter.SLOW_THROTTLE)) {
				shifter.shift(SolenoidShifters.ShiftState.DOWN, true);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
