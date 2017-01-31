package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class LidarTurner extends MotorConstant {
	public LidarTurner() {
		super(RobotMap.Component.lidar, 240000);// Lidar.LIDAR_RPS * Lidar.LIDAR_ENCODER_PPR);
	}
	
	@Override
	protected void initialize() {
		RobotMap.Component.lidar.enableMotionController();
		super.initialize();
	}
	
	@Override
	protected void execute() {
		LogKitten.wtf(RobotMap.Component.lidarTurnEncoder.getRate() + " " + RobotMap.Component.lidarMC.getError());
		super.execute();
	}
}
