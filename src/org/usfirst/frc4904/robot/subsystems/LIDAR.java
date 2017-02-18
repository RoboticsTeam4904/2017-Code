package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.CapSpeedModifier;
import edu.wpi.first.wpilibj.SpeedController;

public class LIDAR extends VelocitySensorMotor {
	public static double TURN_P = 0.000006; // WIP
	public static double TURN_I = 0.00000000605; // WIP
	public static double TURN_D = 0; // WIP
	public static final double TURN_F = 0.00000138;
	public static final double LIDAR_DIAMETER = 3.107;// inches
	public static final double LIDAR_CIRCUMFERENCE = LIDAR.LIDAR_DIAMETER * Math.PI;
	public static final double LIDAR_RPS = 4;
	public static final double LIDAR_ENCODER_PPR = 60000;// WIP
	public double lastRate;
	
	public LIDAR(SpeedController motor) {// must add lidar data later
		super("LIDAR", false, new CapSpeedModifier(0.1, 0.4), RobotMap.Component.lidarMC, motor);
	}
}
