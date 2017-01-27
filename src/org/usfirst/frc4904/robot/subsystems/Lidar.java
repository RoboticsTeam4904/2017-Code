package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.VelocityEncodedMotor;
import edu.wpi.first.wpilibj.SpeedController;

public class Lidar extends VelocityEncodedMotor {
	public static final double LIDAR_TURN_P = 0;// WIP
	public static final double LIDAR_TURN_I = 0;// WIP
	public static final double LIDAR_TURN_D = 0;// WIP
	public static final double LIDAR_DIAMETER = 3.107;// inches;
	public static final double LIDAR_CIRCUMFERENCE = Lidar.LIDAR_DIAMETER * Math.PI;
	public static final double LIDAR_RPS = 4;
	public static final double LIDAR_ENCODER_PPR = 10;// WIP-- carter said I could choose a value
	
	public Lidar(CustomEncoder encoder, SpeedController... motors) {// must add lidar data later
		super("LIDAR", false, new CustomPIDController(Lidar.LIDAR_TURN_P, Lidar.LIDAR_TURN_I, Lidar.LIDAR_TURN_D, encoder), motors);
	}
	
	public void setMotor() {
		enablePID();
		System.out.println(lastSpeed);// prints out last speed for testing purposes
		set(Lidar.LIDAR_RPS * Lidar.LIDAR_ENCODER_PPR);// wont work until AJ fixes error
	}
}
