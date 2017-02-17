package org.usfirst.frc4904.robot.commands;


import java.util.Arrays;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.AutoSolenoidShifters;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.NavX;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
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
	protected final ChassisShiftAsAuto shiftUpCommand;
	protected final ChassisShiftAsAuto shiftDownCommand;

	public AutoShifter(AutoSolenoidShifters shifter, CustomEncoder leftEncoder, CustomEncoder rightEncoder, NavX navX) {
		this.shifter = shifter;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.navX = navX;
		shiftUpCommand = new ChassisShiftAsAuto(shifter, SolenoidShifters.ShiftState.UP);
		shiftDownCommand = new ChassisShiftAsAuto(shifter, SolenoidShifters.ShiftState.DOWN);
	}

	public AutoShifter() {
		this(RobotMap.Component.shifter, RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder,
			RobotMap.Component.navx);
	}

	@Override
	protected void execute() {
		double speed = (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
		double acceleration = navX.getWorldLinearAccelY();
		double throttle = Arrays.stream(RobotMap.Component.chassis.getMotorSpeeds()).average().getAsDouble(); // calculate the average all motor speeds
		boolean isNotGoingStraight = leftEncoder.getRate()
			- rightEncoder.getRate() < AutoShifter.MIN_ENCODER_DISCREPANCY_INDICATING_TURN;
		boolean hasManualShiftedRecently = shifter.timeSinceLastManualShift() <= AutoShifter.LAST_MANUAL_SHIFT_TIME_MILLIS;
		boolean hasAutoShiftedRecently = shifter.timeSinceLastAutoShift() <= AutoShifter.LAST_AUTO_SHIFT_TIME_MILLIS;
		// If the robot isn't going straight, or has shifted recently, don't shift again.
		if (isNotGoingStraight || hasManualShiftedRecently || hasAutoShiftedRecently) {
			return;
		}
		double absoluteSpeed = Math.abs(speed);
		boolean aboveMediumSpeed = absoluteSpeed > AutoShifter.MEDIUM_RATE;
		boolean acceleratingRapidly = acceleration > AutoShifter.RAPID_ACCELERATION_THRESHOLD_GS;
		boolean throttleIsFast = throttle > AutoShifter.FAST_THROTTLE;
		// If we're flooring it, shift up.
		if (aboveMediumSpeed && acceleratingRapidly && throttleIsFast) {
			shiftUpCommand.start();
			return;
		}
		boolean belowFastSpeed = absoluteSpeed < AutoShifter.FAST_RATE;
		boolean speedIsSuperSlow = absoluteSpeed < AutoShifter.SUPER_SLOW_RATE;
		boolean rapidlyDecelerating = acceleration < AutoShifter.RAPID_DECELERATION_THRESHOLD_GS;
		boolean greaterThanMediumThrottle = throttle > AutoShifter.MEDIUM_THROTTLE;
		boolean lessThanSlowThrottle = throttle < AutoShifter.SLOW_THROTTLE;
		// If we're throttling high but going slow (pushing something we just hit), or if we're just driving very slowly, shift down.
		if (belowFastSpeed && rapidlyDecelerating && greaterThanMediumThrottle
			|| speedIsSuperSlow && lessThanSlowThrottle) {
			shiftDownCommand.start();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
