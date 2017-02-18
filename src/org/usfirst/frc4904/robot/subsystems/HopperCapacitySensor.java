package org.usfirst.frc4904.robot.subsystems;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class HopperCapacitySensor extends CANSensor {

	public HopperCapacitySensor() {
		super("HopperCapacitySensor", RobotMap.Port.CAN.hopperSensor);
	}

	public double getCapacity() throws InvalidSensorException {
		LogKitten.wtf("Capacity sensor value: " + super.readSensor().toString());
		return 0.0;
	}
}
