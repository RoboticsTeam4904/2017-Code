package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.robot.subsystems.BallInnie;
import org.usfirst.frc4904.robot.subsystems.Dump;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Lidar;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.PositionSensorMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.CapSpeedModifier;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Port {
		public static class HumanInput {
			public static final int joystick = 0;
			public static final int xboxController = 1;
		}
		
		public static class PWM {
			public static final int leftDriveMotor = 0;
			public static final int rightDriveMotor = 1;
			public static final int ballInnie = 2;
			// public static final int trayInnie = 3; ----- PROJECT CURRENTLY PAUSED
			public static final int flywheelLeftMotor = 4;
			public static final int flywheelRightMotor = 5;
			public static final int lidarMotor = 9;// WIP
		}
		
		public static class CAN {
			public static final int leftEncoder = 0x602;
			public static final int rightEncoder = 0x603;
			public static final int trayEncoder = 0x604;
			public static final int flywheelEncoder = 0x605;
			public static final int elevatorEncoder = 0x606;
			public static final int lidarTurnEncoder = 0x607;
		}
		
		public static class CANMotor {}
		
		public static class PCM {}
		
		public static class Pneumatics {}
		public static int lidarTurnP = 0;// put these in the constants section of RobotMap
		public static int lidarTurnI = 0;
		public static int lidarTurnD = 0;
	}
	
	public static class Component {
		public static PDP pdp;
		public static Chassis chassis;
		public static PositionSensorMotor leftWheel;
		public static PositionSensorMotor rightWheel;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static Flywheel flywheel;
		public static Dump ballDumper;
		public static BallInnie ballIntake;
		public static Subsystem[] mainSubsystems;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		// lidar
		public static CustomPIDController lidarMC;
		public static CANEncoder lidarTurnEncoder;
		public static Lidar lidar;
		public static CapSpeedModifier lidarCap;
		// Vision
		public static AligningCamera alignCamera;
	}
	
	public RobotMap() {
		Component.pdp = new PDP();
		// Chassis
		Component.leftWheelEncoder = new CANEncoder(Port.CAN.leftEncoder);
		Component.leftWheelEncoder.setReverseDirection(true);
		Component.leftWheel = new PositionSensorMotor("leftWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.leftWheelEncoder), new VictorSP(Port.PWM.leftDriveMotor));
		Component.leftWheel.disableMotionController();
		; // TODO add encoders
			// Component.leftWheel.setInverted(true);
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.rightWheel = new PositionSensorMotor("rightWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.rightWheelEncoder), new VictorSP(Port.PWM.rightDriveMotor));
		Component.rightWheel.disableMotionController(); // TODO add encoders
		// Component.rightWheel.setInverted(false);
		Motor leftFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelLeftMotor));
		leftFlywheelMotor.setInverted(true);
		Motor rightFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelRightMotor));
		rightFlywheelMotor.setInverted(false);
		Component.flywheel = new Flywheel(leftFlywheelMotor, rightFlywheelMotor);
		// Human inputs
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(DefaultOperator.JOYSTICK_MIN_THRESH);
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(DefaultDriver.CONTROLLER_MIN_THRESH);
		// Main Subsystems
		Component.alignCamera = new AligningCamera(PIDSourceType.kRate);
		// lidar
		Component.lidarTurnEncoder = new CANEncoder("LidarEncoder", Port.CAN.lidarTurnEncoder, false);
		Component.lidarTurnEncoder.setPIDSourceType(PIDSourceType.kRate);
		Component.lidarMC = new CustomPIDController(Lidar.LIDAR_TURN_P, Lidar.LIDAR_TURN_I, Lidar.LIDAR_TURN_D, Lidar.LIDAR_TURN_F, Component.lidarTurnEncoder);
		Component.lidarMC.setOutputRange(0.15, 0.4);
		Component.lidarCap = new CapSpeedModifier(0.1, 0.4);
		Component.lidar = new Lidar(new Spark(Port.PWM.lidarMotor));
		Component.mainSubsystems = new Subsystem[] {};
	}
}
