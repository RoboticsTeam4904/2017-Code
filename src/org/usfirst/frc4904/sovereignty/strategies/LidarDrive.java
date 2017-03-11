package org.usfirst.frc4904.sovereignty.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMoveDistance;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;

public class LidarDrive extends ChassisMoveDistance {
	protected final static double BOILER_OFFSET_INCHES = Math
		.sqrt(Math.pow(RobotMap.Metrics.ROBOT_LENGTH_INCHES, 2) + Math.pow(RobotMap.Metrics.ROBOT_WIDTH_INCHES, 2));
	protected final CANSensor lidarSensor;

	public LidarDrive(CANSensor lidarSensor) {
		super(RobotMap.Component.chassis, 0.0, RobotMap.Component.chassisDriveMC);
		this.lidarSensor = lidarSensor;
	}

	@Override
	public void initialize() { // TODO: ChassisMoveDistance initialize should be protected
		super.initialize();
		try {
			int[] lidarData = lidarSensor.readSensor();
			super.motionController.setSetpoint(motionController.getSensorValue() + lidarData[1]);
		}
		catch (InvalidSensorException | NullPointerException e) {
			cancel();
		}
	}
}
