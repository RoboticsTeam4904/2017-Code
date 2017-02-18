package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import edu.wpi.first.wpilibj.SpeedController;

public class LIDAR extends VelocitySensorMotor {
	public static double MIN_MOTOR_OUTPUT = 0.15;
	public static double MAX_MOTOR_OUTPUT = 0.4;
	public static double TURN_P = 0.000006; // WIP
	public static double TURN_I = 0.00000000605; // WIP
	public static double TURN_D = 0; // WIP
	public static final double TURN_F = 0.00000138;
	public static final double TARGET_MRPM = 240000;

	public LIDAR(SpeedController motor, MotionController lidarController) {
		super("LIDAR", lidarController, motor);
	}
}
