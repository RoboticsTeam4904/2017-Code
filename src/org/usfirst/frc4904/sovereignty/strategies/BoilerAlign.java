package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BoilerAlign extends CommandGroup {
	public BoilerAlign() {
		// Stage 1: center on the boiler using the first set of LiDAR values
		addSequential(new LIDARTurn(RobotMap.Component.lidar.getLidarSensor1()));
		addSequential(new LIDARDrive(RobotMap.Component.lidar.getLidarSensor1()));
		// Stage 2: drive to the boiler using the second set of LiDAR values
		addSequential(new LIDARTurn(RobotMap.Component.lidar.getLidarSensor2()));
		addSequential(new LIDARDrive(RobotMap.Component.lidar.getLidarSensor2()));
	}
}
