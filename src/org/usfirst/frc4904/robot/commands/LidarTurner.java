package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class LidarTurner extends Command {
	public LidarTurner() {
		requires(RobotMap.Component.lidar);
	}
	
	@Override
	protected void execute() {
		RobotMap.Component.lidar.setMotor();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
