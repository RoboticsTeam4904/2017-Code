package org.usfirst.frc4904.robot.commands;


import java.util.Arrays;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.AutoSolenoidShifters;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Shift an instance of AutoSolenoidShifters based on conditions
 * including speed, throttle, and acceleration.
 * 
 * @see <a href="https://www.chiefdelphi.com/forums/showpost.php?s=7c250a45af41fe4d4517562e541a0f67&p=1087911&postcount=6">Chief Delphi post by apalrd</a>
 */
public class AutoShifter extends Command {
	public static final double LAST_MANUAL_SHIFT_TIME_MILLIS = 5000;
	public static final double LAST_AUTO_SHIFT_TIME_MILLIS = 250;
	public static final double MAX_WHEEL_SPEED_VALUE_DIFFERENCE_INDICATING_STRAIGHT_MOTION = 0.5;
	public static final double MEDIUM_SPEED_THRESHOLD = 40;
	public static final double MEDIUM_THROTTLE_THRESHOLD = 0.5;
	public static final double FAST_THROTTLE_THRESHOLD = 0.75;
	protected final CustomEncoder leftEncoder;
	protected final CustomEncoder rightEncoder;
	protected final AutoSolenoidShifters shifter;
	protected final ChassisShiftAsAuto shiftUpCommand;
	protected final ChassisShiftAsAuto shiftDownCommand;

	public AutoShifter(AutoSolenoidShifters shifter, CustomEncoder leftEncoder, CustomEncoder rightEncoder) {
		this.shifter = shifter;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		shiftUpCommand = new ChassisShiftAsAuto(shifter, SolenoidShifters.ShiftState.UP);
		shiftDownCommand = new ChassisShiftAsAuto(shifter, SolenoidShifters.ShiftState.DOWN);
	}

	public AutoShifter() {
		this(RobotMap.Component.shifter, RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder);
	}

	@Override
	protected void execute() {
		// If the robot has shifted recently, don't shift again.
		boolean hasManuallyShiftedRecently = shifter.timeSinceLastManualShift() <= AutoShifter.LAST_MANUAL_SHIFT_TIME_MILLIS;
		boolean hasAutomaticallyShiftedRecently = shifter.timeSinceLastAutoShift() <= AutoShifter.LAST_AUTO_SHIFT_TIME_MILLIS;
		if (hasManuallyShiftedRecently || hasAutomaticallyShiftedRecently) {
			return;
		}
		double[] motorThrottles = RobotMap.Component.chassis.getMotorSpeeds();
		// Calculate the average of the encoder speeds, which is the same as the overall actual forward speed because the turn speed is added to one side and subtracted from the other.
		// Also, take the absolute value to treat forwards & backwards the same.
		double absoluteForwardSpeed = Math.abs((leftEncoder.getRate() + rightEncoder.getRate()) / 2);
		// Calculate the average of all motor speeds, which is the same as the overall throttle (desired forward speed) because the turn speed is added to one side and subtracted from the other.
		// Also, take the absolute value to treat forwards & backwards the same.
		double absoluteThrottle = Math.abs(Arrays.stream(motorThrottles).average().getAsDouble());
		// Figure out whether the code wants the robot to go straight by looking at the difference between motor speeds of both sides.
		boolean isTurning = Math.abs(
			motorThrottles[0] - motorThrottles[1]) > AutoShifter.MAX_WHEEL_SPEED_VALUE_DIFFERENCE_INDICATING_STRAIGHT_MOTION;
		boolean isNotAboveFastThrottle = absoluteThrottle > AutoShifter.FAST_THROTTLE_THRESHOLD;
		// If the robot is trying to turn (sharply) in place, shift down (the chassis can't turn well in high gear).
		// (Don't do this if we're throttling it fast, to allow drifting and big-radius turns)
		if (isTurning && isNotAboveFastThrottle) {
			LogKitten.v("Downshifting to make turning easier.");
			shiftDownCommand.start();
			return;
		}
		// If we're pushing against something (throttling high but going slow), shift down.
		boolean isBelowMediumSpeed = absoluteForwardSpeed < AutoShifter.MEDIUM_SPEED_THRESHOLD;
		boolean isThrottleAboveMedium = absoluteThrottle > AutoShifter.MEDIUM_THROTTLE_THRESHOLD;
		if (isBelowMediumSpeed && isThrottleAboveMedium) {
			LogKitten.v("Downshifting to push something.");
			shiftDownCommand.start();
			return;
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
