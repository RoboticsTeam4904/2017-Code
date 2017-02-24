package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class HopperCapacitySensor extends CANSensor {
	protected static final double BALLS_PER_LAYER = 12; // actually 14.3 but balls won't land perfectly
	protected static final double SENSOR_HEIGHT_ABOVE_HOPPER_INCHES = 36.8;
	protected static final double MAX_SENSOR_VALUE_INCHES = 83.82;
	protected static final double BALL_HEIGHT_INCHES = 12.7;
	protected static final double TEENSY_OUTPUT_TO_INCHES_CONVERSION_FACTOR = 1.0 / 100.0;

	public HopperCapacitySensor(int port) {
		super("HopperCapacitySensor", port);
	}

	public double getCapacity() throws InvalidSensorException {
		// final double hopperHeight = 41.9;
		double teensyOutputLeft = (readSensor()[0]) * HopperCapacitySensor.TEENSY_OUTPUT_TO_INCHES_CONVERSION_FACTOR;
		double teensyOutputRight = (readSensor()[1]) * HopperCapacitySensor.TEENSY_OUTPUT_TO_INCHES_CONVERSION_FACTOR;
		// divide by 100 to get accurate ball
		double rightBalls = (HopperCapacitySensor.SENSOR_HEIGHT_ABOVE_HOPPER_INCHES
			* (HopperCapacitySensor.MAX_SENSOR_VALUE_INCHES - teensyOutputLeft)
			/ HopperCapacitySensor.MAX_SENSOR_VALUE_INCHES)
			/ HopperCapacitySensor.BALL_HEIGHT_INCHES;
		double leftBalls = (HopperCapacitySensor.SENSOR_HEIGHT_ABOVE_HOPPER_INCHES
			* (HopperCapacitySensor.MAX_SENSOR_VALUE_INCHES - teensyOutputRight)
			/ HopperCapacitySensor.MAX_SENSOR_VALUE_INCHES)
			/ HopperCapacitySensor.BALL_HEIGHT_INCHES;
		double averageBalls = (rightBalls + leftBalls) / 2;
		double output = HopperCapacitySensor.BALLS_PER_LAYER * averageBalls;
		return output;
	}
}
