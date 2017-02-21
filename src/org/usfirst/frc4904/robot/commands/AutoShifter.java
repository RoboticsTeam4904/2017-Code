package org.usfirst.frc4904.robot.commands;


import java.util.Arrays;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.AutoSolenoidShifters;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.NavX;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.EnableableModifier;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Shift an instance of AutoSolenoidShifters based on conditions
 * including speed, throttle, and acceleration.
 * 
 * @see <a href="https://www.chiefdelphi.com/forums/showpost.php?s=7c250a45af41fe4d4517562e541a0f67&p=1087911&postcount=6">Chief Delphi post by apalrd</a>
 */
public class AutoShifter extends Command {
	protected final CustomEncoder leftEncoder;
	protected final CustomEncoder rightEncoder;
	protected final AutoSolenoidShifters shifter;
	protected final NavX navX;
	public static final double RAPID_ACCELERATION_THRESHOLD_GS = 0.24881632653;
	public static final double RAPID_DECELERATION_THRESHOLD_GS = -0.06216;
	public static final double MIN_ENCODER_DISCREPANCY_INDICATING_TURN = 100; // encoder ticks--WIP
	public static final double SUPER_SLOW_RATE = 24; // inches per sec, according to the encoders
	public static final double MEDIUM_RATE = AutoShifter.SUPER_SLOW_RATE * 2;
	public static final double FAST_RATE = AutoShifter.SUPER_SLOW_RATE * 4;
	public static final double SLOW_THROTTLE = 0.4;
	public static final double MEDIUM_THROTTLE = AutoShifter.SLOW_THROTTLE * 1.875;
	public static final double FAST_THROTTLE = AutoShifter.SLOW_THROTTLE * 2;
	public static final double LAST_MANUAL_SHIFT_TIME_MILLIS = 5000; // TODO
	public static final double LAST_AUTO_SHIFT_TIME_MILLIS = 500;// TODO
	protected final HighGearShifterRamping highGearShiftRamp;
	protected final ChassisShiftAsAuto shiftUpCommand;
	protected final ChassisShiftAsAuto shiftDownCommand;

	public AutoShifter(AutoSolenoidShifters shifter, CustomEncoder leftEncoder, CustomEncoder rightEncoder, NavX navX) {
		this.shifter = shifter;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.navX = navX;
		highGearShiftRamp = new HighGearShifterRamping();
		shiftUpCommand = new ChassisShiftAsAuto(shifter, SolenoidShifters.ShiftState.UP);
		shiftDownCommand = new ChassisShiftAsAuto(shifter, SolenoidShifters.ShiftState.DOWN);
	}

	public AutoShifter() {
		this(RobotMap.Component.shifter, RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder,
			RobotMap.Component.navx);
	}

	public EnableableModifier getHighGearShiftRampingModifier() {
		return highGearShiftRamp.getEnableableModifier();
	}

	@Override
	protected void execute() {
		// Calculate the average of the encoder speeds, which is the same as the overall actual forward speed because the turn speed is added to one side and subtracted from the other.
		double forwardSpeed = (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
		// Calculate the average of all motor speeds, which is the same as the overall throttle (desired forward speed) because the turn speed is added to one side and subtracted from the other.
		double throttle = Arrays.stream(RobotMap.Component.chassis.getMotorSpeeds()).average().getAsDouble();
		boolean isNotGoingStraight = Math.abs(leftEncoder.getRate()
			- rightEncoder.getRate()) < AutoShifter.MIN_ENCODER_DISCREPANCY_INDICATING_TURN;
		boolean hasManuallyShiftedRecently = shifter.timeSinceLastManualShift() <= AutoShifter.LAST_MANUAL_SHIFT_TIME_MILLIS;
		boolean hasAutomaticallyShiftedRecently = shifter.timeSinceLastAutoShift() <= AutoShifter.LAST_AUTO_SHIFT_TIME_MILLIS;
		// If the robot isn't going straight, or has shifted recently, don't shift again.
		if (isNotGoingStraight || hasManuallyShiftedRecently || hasAutomaticallyShiftedRecently) {
			return;
		}
		// Determine whether the robot is going forward or backwards by the sign on the forward speed.
		boolean isGoingForward = forwardSpeed > 0;
		double acceleration = navX.getWorldLinearAccelY();
		double relativeAcceleration = isGoingForward ? acceleration : acceleration * -1;
		// Also take the absolute value to treat forwards & backwards the same.
		double absoluteForwardSpeed = Math.abs(forwardSpeed);
		boolean isAboveMediumSpeed = absoluteForwardSpeed > AutoShifter.MEDIUM_RATE;
		boolean isAcceleratingRapidly = relativeAcceleration > AutoShifter.RAPID_ACCELERATION_THRESHOLD_GS;
		boolean isThrottleFast = throttle > AutoShifter.FAST_THROTTLE;
		// If we're flooring it and nothing's in our way, shift up.
		if (isAboveMediumSpeed && isAcceleratingRapidly && isThrottleFast) {
			shiftUpCommand.start();
			highGearShiftRamp.didShiftUp();
			return;
		}
		boolean isThrottlelessThanSlow = throttle < AutoShifter.SLOW_THROTTLE;
		boolean isSpeedSuperSlow = absoluteForwardSpeed < AutoShifter.SUPER_SLOW_RATE;
		// If we're just driving very slowly, shift down.
		if (isSpeedSuperSlow && isThrottlelessThanSlow) {
			shiftDownCommand.start();
			return;
		}
		boolean isBelowFastSpeed = absoluteForwardSpeed < AutoShifter.FAST_RATE;
		boolean isRapidlyDecelerating = relativeAcceleration < AutoShifter.RAPID_DECELERATION_THRESHOLD_GS;
		boolean isThrottleGreaterThanMedium = throttle > AutoShifter.MEDIUM_THROTTLE;
		// If we're throttling high but going slow (pushing something we just hit)
		if (isRapidlyDecelerating && isThrottleGreaterThanMedium) {
			shiftDownCommand.start();
			return;
		}
	}

	protected static class HighGearShifterRamping implements SpeedModifier {
		public static int RAMP_TIME_MILLIS = 500;
		long lastShiftTime = 0;
		EnableableModifier eModifier;

		public HighGearShifterRamping() {
			eModifier = new EnableableModifier(this);
		}

		public EnableableModifier getEnableableModifier() {
			return eModifier;
		}

		void didShiftUp() {
			lastShiftTime = System.currentTimeMillis();
			eModifier.enable();
		}

		@Override
		public double modify(double speed) {
			long timeSinceShift = System.currentTimeMillis() - lastShiftTime;
			if (timeSinceShift >= HighGearShifterRamping.RAMP_TIME_MILLIS) {
				eModifier.disable();
			}
			double approxPrevVelocity = speed / RobotMap.Metrics.HIGH_TO_LOW_GEAR_RATIO;
			double velocityGap = speed - approxPrevVelocity;
			double percentRamped = (double) timeSinceShift / (double) HighGearShifterRamping.RAMP_TIME_MILLIS;
			return approxPrevVelocity + percentRamped * velocityGap;
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
