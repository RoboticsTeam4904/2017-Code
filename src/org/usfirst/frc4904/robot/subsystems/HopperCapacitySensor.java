package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class HopperCapacitySensor extends CANSensor {
	protected static final double BALLS_PER_LAYER = 12; // actually 14.3 but balls won't land perfectly
	protected static final double SENSOR_HEIGHT_ABOVE_HOPPER_INCHES = 36.8;
	protected static final double MAX_SENSOR_VALUE_INCHES = 83.82;
	protected static final double BALL_HEIGHT_INCHES = 12.7;
	protected static final double TEENSY_OUTPUT_TO_INCHES_CONVERSION_FACTOR = 1.0 / 100.0;
	protected static final int NUM_SENSORS = 2;

	public HopperCapacitySensor(int port) {
		super("HopperCapacitySensor", port);
	}

	public double getCapacity() throws InvalidSensorException {
		int[] rawSensorOutput = readSensor();
		double ballsSum = 0;
		for (int i = 0; i < HopperCapacitySensor.NUM_SENSORS; i++) {
			double sensorOutput = rawSensorOutput[i] * HopperCapacitySensor.TEENSY_OUTPUT_TO_INCHES_CONVERSION_FACTOR;
			double balls = HopperCapacitySensor.SENSOR_HEIGHT_ABOVE_HOPPER_INCHES
				* (1 - (sensorOutput / HopperCapacitySensor.MAX_SENSOR_VALUE_INCHES)) / HopperCapacitySensor.BALL_HEIGHT_INCHES;
			ballsSum += balls;
		}
		double averageBalls = ballsSum / HopperCapacitySensor.NUM_SENSORS;
		double output = HopperCapacitySensor.BALLS_PER_LAYER * averageBalls;
		return output;
	}
}
