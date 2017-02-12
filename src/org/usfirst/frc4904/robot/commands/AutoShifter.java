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
		double throttle = 0.0;
		for (double motorSpeed : RobotMap.Component.chassis.getMotorSpeeds()) {
			throttle += motorSpeed;
		}
		throttle /= RobotMap.Component.chassis.getMotorSpeeds().length;
		boolean isNotGoingStraight = leftEncoder.getRate() - rightEncoder.getRate() < AutoShifter.RATE_DIFF;
		boolean hasManualShiftedRecently = shifter.timeSinceLastManualShift() <= AutoShifter.LAST_MANUAL_SHIFT_TIME;
		boolean hasAutoShiftedRecently = shifter.timeSinceLastAutoShift() <= AutoShifter.LAST_AUTO_SHIFT_TIME;
		boolean isGoingBackwards = speed < 0;
		if (isNotGoingStraight || hasManualShiftedRecently || hasAutoShiftedRecently) {
			return;
		}
		if (isGoingBackwards) {
			shiftDown();
		} else {
			double absoluteSpeed = Math.abs(speed);
			boolean fasterThanMedium = absoluteSpeed > AutoShifter.MEDIUM_RATE;
			boolean acceleratingRapidly = acceleration > AutoShifter.RAPID_ACCELERATION;
			boolean throttleIsHigh = throttle > AutoShifter.FAST_THROTTLE;
			if (fasterThanMedium && acceleratingRapidly && throttleIsHigh) {
				shiftUp();
				return;
			}
			boolean slowerThanFast = absoluteSpeed < AutoShifter.FAST_RATE;
			boolean goingSuperSlowly = absoluteSpeed < AutoShifter.SUPER_SLOW_RATE;
			boolean rapidlyDecelerating = acceleration < AutoShifter.RAPID_DECELERATION;
			boolean throttleIsMedium = throttle > AutoShifter.MEDIUM_THROTTLE;
			boolean throttleIsSlow = throttle < AutoShifter.SLOW_THROTTLE;
			if (slowerThanFast && rapidlyDecelerating && throttleIsMedium || goingSuperSlowly && throttleIsSlow) {
				shiftDown();
			}
		}
	}

	private void shiftUp() {
		shifter.shift(SolenoidShifters.ShiftState.UP, true);
	}

	private void shiftDown() {
		shifter.shift(SolenoidShifters.ShiftState.DOWN, true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
