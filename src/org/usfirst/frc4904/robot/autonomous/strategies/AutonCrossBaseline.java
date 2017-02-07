package org.usfirst.frc4904.robot.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;

/**
 *
 */
public class AutonCrossBaseline extends ChassisMoveDistance {
	
	public static final double WHEEL_ENCODER_PPR = 0; // ticks per rotation
	public static final double WHEEL_DIAMETER = 0; // everything inches
	public static final double WHEEL_CIRCUMFERENCE = AutonCrossBaseline.WHEEL_DIAMETER * Math.PI;
	public static final double DISTANCE_BASELINE = 84 * AutonCrossBaseline.WHEEL_ENCODER_PPR / AutonCrossBaseline.WHEEL_CIRCUMFERENCE;// inches-- goes a little past baseline to be safe
	
	public AutonCrossBaseline() {
		super(RobotMap.Component.chassis, AutonCrossBaseline.DISTANCE_BASELINE, RobotMap.Component.chassisDrivePID, RobotMap.Component.leftWheelEncoder);
	}
}