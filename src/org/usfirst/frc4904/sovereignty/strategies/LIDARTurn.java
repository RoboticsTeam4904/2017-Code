package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurn;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class LIDARTurn extends ChassisTurn {
	protected final static double BOILER_OFFSET_INCHES = Math
		.sqrt(Math.pow(RobotMap.Metrics.ROBOT_LENGTH_INCHES, 2) + Math.pow(RobotMap.Metrics.ROBOT_WIDTH_INCHES, 2));
	protected final CANSensor lidarSensor;

	public LIDARTurn(CANSensor lidarSensor) {
		super(RobotMap.Component.chassis, 0.0, RobotMap.Component.navx, RobotMap.Component.chassisTurnMC);
		this.lidarSensor = lidarSensor;
	}

	@Override
	protected void initialize() {
		super.initialize();
		try {
			int[] lidarData = lidarSensor.readSensor();
			initialAngle = ((lidarData[0] + 360) % 360 - 180);
		}
		catch (InvalidSensorException | NullPointerException e) {
			cancel();
		}
	}
}
