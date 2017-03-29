package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.LIDAR;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class LIDARTurnbaugh extends MotorConstant {
	public LIDARTurnbaugh() {
		super(RobotMap.Component.lidar.getLidarMotor(), LIDAR.TARGET_MRPM);
	}

	@Override
	protected void initialize() {
		try {
			RobotMap.Component.lidar.getLidarMotor().reset();
		}
		catch (InvalidSensorException e) {
			LogKitten.e("LIDAR error!");
			LogKitten.e(e);
			LogKitten.e(e.getStackTrace());
		}
		RobotMap.Component.lidar.getLidarMotor().enableMotionController();
		super.initialize();
	}
}
