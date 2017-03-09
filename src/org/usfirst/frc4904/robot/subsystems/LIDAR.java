package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.LIDARTurnbaugh;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LIDAR extends Subsystem {
	public static double MIN_MOTOR_OUTPUT = 0.15;
	public static double MAX_MOTOR_OUTPUT = 0.4;
	public static double TURN_P = 0.000006; // WIP
	public static double TURN_I = 0.00000000605; // WIP
	public static double TURN_D = 0; // WIP
	public static final double TURN_F = 0.00000138;
	public static final double TARGET_MRPM = 240000;
	protected final VelocitySensorMotor lidarMotor;
	protected final CustomPIDController lidarMotorController;
	protected final CANSensor lidarSensor;

	public LIDAR(SpeedController motor, int lidarID, int lidarEncoderID) {
		CANEncoder lidarEncoder = new CANEncoder(lidarEncoderID);
		lidarEncoder.setPIDSourceType(PIDSourceType.kRate);
		lidarMotorController = new CustomPIDController(LIDAR.TURN_P, LIDAR.TURN_I, LIDAR.TURN_D,
			lidarEncoder);
		lidarMotor = new VelocitySensorMotor(lidarMotorController, motor);
		lidarSensor = new CANSensor("LIDAR", lidarID);
	}

	public CANSensor getLidarSensor() {
		return lidarSensor;
	}

	public VelocitySensorMotor getLidarMotor() {
		return lidarMotor;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LIDARTurnbaugh());
	}
}
