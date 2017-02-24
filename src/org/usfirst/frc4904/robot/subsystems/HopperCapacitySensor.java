package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class HopperCapacitySensor extends CANSensor {
	public HopperCapacitySensor(int port) {
		super("HopperCapacitySensor", port);
	}

	public double getCapacity() throws InvalidSensorException {
		double rightBalls;
		double leftBalls;
		double ballsperlayer = 12; // actually 14.3 but balls won't land perfectly
		double averageBalls;
		double teensyOutputLeft;
		double teensyOutputRight;
		// final double hopperHeight = 41.9;
		final double sensorHeight = 36.8;
		final double hypotunuseLength = 83.82;
		final double ballHeight = 12.7;
		double output;
		teensyOutputLeft = (readSensor()[0]) / 100.0;
		teensyOutputRight = (readSensor()[1]) / 100.0;
		// divide by 100 to get accurate ball
		rightBalls = (sensorHeight * (hypotunuseLength - teensyOutputLeft) / hypotunuseLength) / ballHeight;
		leftBalls = (sensorHeight * (hypotunuseLength - teensyOutputRight) / hypotunuseLength) / ballHeight;
		averageBalls = (rightBalls + leftBalls) / 2;
		output = ballsperlayer * averageBalls;
		return output;
	}
}
