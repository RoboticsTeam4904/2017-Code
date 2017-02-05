package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

public class LidarTurner extends Command {
	public LidarTurner() {
		requires(RobotMap.Component.lidar);
	}
	
	@Override
	protected void initialize() {
		RobotMap.Component.lidar.enableMotionController();
		RobotMap.Component.lidar.set(240000);
	}
	
	@Override
	protected void execute() {
		LogKitten.wtf(RobotMap.Component.lidarTurnEncoder.pidGet() + " " + RobotMap.Component.lidarMC.getError() + " " + RobotMap.Component.lidarMC.checkException());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
