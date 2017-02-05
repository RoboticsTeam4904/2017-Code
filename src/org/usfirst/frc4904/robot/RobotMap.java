package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.Lidar;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.NavX;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.PositionSensorMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.CapSpeedModifier;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
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
			// SOME MOTORS AREN'T EXACT - work in progress
			public static final int leftDriveMotor = 0;
			public static final int rightDriveMotor = 1;
			public static final int flywheelLeftMotor = 2; // WIP
			public static final int flywheelRightMotor = 3; // WIP
			public static final int bioTopMotor = 4; // WIP
			public static final int bioLeftMotor = 5; // WIP
			public static final int bioMainMotor = 6; // WIP
			public static final int lidarMotor = 9;// WIP
			public static final int gearFlap = 7;
		}
		
		public static class Serial {
			public static final int navX = 0;
			public static final int ballInnie = 2;
			// public static final int trayInnie = 3; ----- PROJECT CURRENTLY PAUSED
			public static final int flywheelLeftMotor = 4;
		}
		
		public static class CAN {
			public static final int leftEncoder = 0x602;
			public static final int rightEncoder = 0x603;
			// public static final int trayEncoder = 0x604; ---- not being used anymore
			public static final int flywheelEncoder = 0x605;
			public static final int elevatorEncoder = 0x606;
			public static final int lidarTurnEncoder = 0x607;
		}
		
		public static class CANMotor {}
		
		public static class PCM {}
		
		public static class Pneumatics {
			public static final int bioShifterUp = 2;
			public static final int bioShifterDown = 3;
			public static int gearSolenoidUp = 0;// ports are WIP
			public static int gearSolenoidDown = 1;// ports are WIP
		}
	}
	
	public static class Constant {
		public static class HumanInput {
			public static final double X_SPEED_SCALE = 1;
			public static final double Y_SPEED_SCALE = 1;
			public static final double SPEED_GAIN = 1;
			public static final double SPEED_EXP = 2;
			public static final double TURN_GAIN = 1;
			public static final double TURN_EXP = 2;
			public static final double TURN_SPEED_SCALE = 1;
			public static final double XBOX_MINIMUM_THRESHOLD = 0.1;
			public static final double OPERATOR_JOYSTICK_MINIMUM_THRESHOLD = 0.1;
		}
		
		public static class RobotMetric {
			public static final double WIDTH = 0;// all in inches
			public static final double LENGTH = 0;
			public static final double WHEEL_ENCODER_PPR = 0;
			public static final double WHEEL_DIAMETER = 0;
			public static final double WHEEL_CIRCUMFERENCE = RobotMetric.WHEEL_DIAMETER * Math.PI;
		}
		
		public static class AutonomousMetric {
			public static final double WAIT_TIME = 0;
		}
		
		public static class FieldMetric {}
		
		public static class Network {
			public static final String VISION_GOAL_NETWORK_TABLE_ADDRESS = "GRIP/myContoursReport";
		}
		
		public static class Component {}
		
		public static class Pneumatics {}
		public static int lidarTurnP = 0;// put these in the constants section of RobotMap
		public static int lidarTurnI = 0;
		public static int lidarTurnD = 0;
	}
	
	public static class Component {
		public static PDP pdp;
		public static PositionSensorMotor leftWheel;
		public static PositionSensorMotor rightWheel;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static CustomEncoder elevatorEncoder;
		public static Chassis chassis;
		public static NavX navX;
		public static CustomPIDController chassisDrivePID;
		public static BallIO ballIO;
		public static GearIO gearIntakeOuttake;
		public static SolenoidShifters gearSlotOpener;
		public static Servo gearFlap;
		public static Subsystem[] mainSubsystems;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		// lidar
		public static CustomPIDController lidarMC;
		public static CANEncoder lidarTurnEncoder;
		public static Lidar lidar;
		public static CapSpeedModifier lidarCap;
		// Vision
	}
	
	public static class HumanInput {
		public static class Driver {
			public static CustomXbox xbox;
		}
		
		public static class Operator {
			public static CustomJoystick stick;
		}
	}
	
	public RobotMap() {
		Component.pdp = new PDP();
		// Chassis
		Component.leftWheelEncoder = new CANEncoder(Port.CAN.leftEncoder);
		Component.leftWheelEncoder.setReverseDirection(true);
		Component.leftWheel = new PositionSensorMotor("leftWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.leftWheelEncoder), new VictorSP(Port.PWM.leftDriveMotor));
		Component.leftWheel.disableMotionController(); // TODO add encoders
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.rightWheel = new PositionSensorMotor("rightWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.rightWheelEncoder), new VictorSP(Port.PWM.rightDriveMotor));
		Component.rightWheel.disableMotionController(); // TODO add encoders
		// Component.rightWheel.setInverted(false);
		// Ball Dumper
		Component.elevatorEncoder = new CANEncoder(Port.CAN.elevatorEncoder);
		// Ball-Intake-Outtake
		Motor bioTopMotor = new Motor(new VictorSP(Port.PWM.bioTopMotor));
		Motor bioLeftMotor = new Motor(new VictorSP(Port.PWM.bioLeftMotor));
		Motor bioMainMotor = new Motor(new VictorSP(Port.PWM.bioMainMotor));
		Component.ballIO = new BallIO(bioTopMotor, bioLeftMotor, bioMainMotor, new DoubleSolenoid(Port.Pneumatics.bioShifterDown, Port.Pneumatics.bioShifterUp));
		Component.leftWheel = new PositionSensorMotor("leftWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.leftWheelEncoder), new VictorSP(Port.PWM.leftDriveMotor));
		Component.leftWheel.disableMotionController();
		Component.navX = new NavX(SerialPort.Port.kOnboard);
		// Component.leftWheel.setInverted(true);
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.rightWheel = new PositionSensorMotor("rightWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.rightWheelEncoder), new VictorSP(Port.PWM.rightDriveMotor));
		Component.rightWheel.disableMotionController();
		// Component.rightWheel.setInverted(false);
		// Gear
		Component.gearFlap = new Servo(Port.PWM.gearFlap);
		Component.gearSlotOpener = new SolenoidShifters((Port.Pneumatics.gearSolenoidUp), (Port.Pneumatics.gearSolenoidDown));
		// Flywheel
		Motor leftFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelLeftMotor));
		leftFlywheelMotor.setInverted(true);
		Motor rightFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelRightMotor));
		rightFlywheelMotor.setInverted(false);
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Operator.stick.setDeadzone(Constant.HumanInput.OPERATOR_JOYSTICK_MINIMUM_THRESHOLD);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
		Component.navX = new NavX(SerialPort.Port.kOnboard);
		Component.mainSubsystems = new Subsystem[] {Component.gearIntakeOuttake, Component.ballIO};
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(DefaultOperator.JOYSTICK_MIN_THRESH);
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(DefaultDriver.CONTROLLER_MIN_THRESH);
		// Main Subsystems
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
