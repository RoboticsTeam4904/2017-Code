package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class BoilerApproachTurn extends ChassisTurn {
	protected final static double BOILER_OFFSET_INCHES = Math
		.sqrt(Math.pow(RobotMap.Metrics.ROBOT_LENGTH_INCHES, 2) + Math.pow(RobotMap.Metrics.ROBOT_WIDTH_INCHES, 2));

	public BoilerApproachTurn() {
		super(RobotMap.Component.chassis, 0.0, RobotMap.Component.navx, RobotMap.Component.chassisTurnMC);
	}

	@Override
	protected void initialize() {
		super.initialize();
		try {
			int[] lidarData = RobotMap.Component.lidar.getLidarSensor2().readSensor();
			super.initialAngle = ((lidarData[0] + 360) % 360 - 180);
		}
		catch (InvalidSensorException | NullPointerException e) {
			cancel();
		}
	}
}
