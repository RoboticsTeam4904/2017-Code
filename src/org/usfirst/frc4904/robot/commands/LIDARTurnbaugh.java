package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.LIDAR;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

/**
 * Initializes the LIDAR and enables it to be controlled.
 *
 */

public class LIDARTurnbaugh extends MotorConstant {
	public LIDARTurnbaugh() {
		super(RobotMap.Component.lidar, LIDAR.TARGET_MRPM);// set the LIDAR
															// motor and speed
	}

	@Override
	protected void initialize() {
		try {
			RobotMap.Component.lidar.reset();
		} catch (InvalidSensorException e) {
			LogKitten.e("LIDAR error!");
			LogKitten.e(e);
			LogKitten.e(e.getStackTrace());
		}
		RobotMap.Component.lidar.enableMotionController();
		super.initialize();
	}
}
