package org.usfirst.frc4904.robot.commands;


import java.util.LinkedList;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

public class AutoShifter extends Command {
	protected CustomEncoder leftEncoder;
	protected CustomEncoder rightEncoder;
	public SolenoidShifters shifter;
	public LinkedList<Double> speeds;
	public static final double RAPID_ACCELERATION = 8;// WIP
	public static final double RAPID_DECELERATION = 2;// WIP
	public static final double RATE_DIFF = 100; // encoder ticks--WIP
	public static final double SUPER_SLOW_RATE = 2; // ft per sec
	public static final double MEDIUM_RATE = AutoShifter.SUPER_SLOW_RATE * 2;
	public static final double FAST_RATE = AutoShifter.SUPER_SLOW_RATE * 4;
	public static final double SLOW_THROTTLE = 0.4;
	public static final double MEDIUM_THROTTLE = AutoShifter.SLOW_THROTTLE * 1.875;
	public static final double FAST_THROTTLE = AutoShifter.SLOW_THROTTLE * 2;
	public static final double SPEED_LIST_MAX_LENGTH = 5;
	public static final double LAST_SHIFT_TIME = 5000;

	public AutoShifter() {
		shifter = RobotMap.Component.chassis.getShifter();
		leftEncoder = RobotMap.Component.leftWheelEncoder;
		rightEncoder = RobotMap.Component.rightWheelEncoder;
	}

	@Override
	protected void execute() {
		double speed = (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
		speeds.add(speed);
		while (speeds.size() > AutoShifter.SPEED_LIST_MAX_LENGTH) {
			speeds.removeFirst();
		}
		double speedDiffAvg = (speeds.getLast() - speeds.getFirst()) / (speeds.size() - 1);// acceleration
		if (leftEncoder.getRate() - rightEncoder.getRate() < AutoShifter.RATE_DIFF
			&& shifter.timeSinceLastShift() > AutoShifter.LAST_SHIFT_TIME) {// only if driving straight and we haven't manually shifted in 5 seconds
			double throttle = 0.0;
			for (double motorSpeed : RobotMap.Component.chassis.getMotorSpeeds()) {
				throttle += motorSpeed;
			}
			throttle /= RobotMap.Component.chassis.getMotorSpeeds().length;
			if (Math.abs(speed) > AutoShifter.MEDIUM_RATE && speedDiffAvg > AutoShifter.RAPID_ACCELERATION
				&& throttle > AutoShifter.FAST_THROTTLE) {// 4 ft/s
				shifter.shift(SolenoidShifters.ShiftState.UP, true);
			}
			if (Math.abs(speed) < AutoShifter.FAST_RATE && speedDiffAvg < AutoShifter.RAPID_DECELERATION
				&& throttle > AutoShifter.MEDIUM_THROTTLE) {
				shifter.shift(SolenoidShifters.ShiftState.DOWN, true);
			}
			if (Math.abs(speed) < AutoShifter.SUPER_SLOW_RATE && throttle < AutoShifter.SLOW_THROTTLE) {
				shifter.shift(SolenoidShifters.ShiftState.DOWN, true);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
}
