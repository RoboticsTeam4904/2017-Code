package org.usfirst.frc4904.robot.commands;


import java.util.LinkedList;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.Util;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShifter extends Command {
	public CustomEncoder leftEncoder;
	public CustomEncoder rightEncoder;
	public SolenoidShifters shifter = RobotMap.Component.shifter;
	public int iteration = 0;
	public LinkedList<Double> speeds;
	public double speed;
	public double speedDiffAvg = 0;
	public static final double ENCODER_PPR = 0;// WIP
	public static final double WHEEL_DIAMETER = 0;// WIP
	public static final double WHEEL_CIRCUMFERENCE = AutoShifter.WHEEL_DIAMETER * Math.PI;
	public static final double RAPID_ACCELERATION = 8;// WIP
	public static final double RAPID_DECELERATION = 2;//
	public static final double RATE_DIFF = 100; // encoder ticks
	public static final double SUPER_SLOW_RATE = 2; // ft per sec
	public static final double SLOW_THROTTLE = 0.4;
	
	public AutoShifter() {
		leftEncoder = RobotMap.Component.leftWheelEncoder;
		rightEncoder = RobotMap.Component.rightWheelEncoder;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		iteration += 1;
		speed = leftEncoder.getRate();
		speeds.add(speed);
		while (speeds.size() > 5) {
			speeds.remove(speeds.getFirst());
		}
		speedDiffAvg = speeds.getLast() - speeds.getFirst() / (speeds.size() - 1);// acceleration
		if (new Util.Range(-AutoShifter.RATE_DIFF, AutoShifter.RATE_DIFF).contains(Math.abs(leftEncoder.getRate() - rightEncoder.getRate()))) {// only if driving straight
			double throttle = 0.0;
			for (double motorSpeed : RobotMap.Component.chassis.getMotorSpeeds()) {
				throttle += motorSpeed;
			}
			throttle = throttle / RobotMap.Component.chassis.getMotorSpeeds().length;
			if (leftEncoder.getRate() > AutoShifter.SUPER_SLOW_RATE * 2 && speedDiffAvg > AutoShifter.RAPID_ACCELERATION && throttle > AutoShifter.SLOW_THROTTLE * 2) {// 4 ft/s
				shifter.shift(SolenoidShifters.ShiftState.UP);
			}
			if (leftEncoder.getRate() < AutoShifter.SUPER_SLOW_RATE * 4 && throttle > AutoShifter.SLOW_THROTTLE * 1.875 && speedDiffAvg < AutoShifter.RAPID_DECELERATION) {
				shifter.shift(SolenoidShifters.ShiftState.DOWN);
			}
			if (leftEncoder.getRate() < AutoShifter.SUPER_SLOW_RATE && throttle < AutoShifter.SLOW_THROTTLE) {
				shifter.shift(SolenoidShifters.ShiftState.DOWN);
			}
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}
}
