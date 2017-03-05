package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.robot.subsystems.AutoSolenoidShifters;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.robot.subsystems.Climber;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.Hopper;
import org.usfirst.frc4904.robot.subsystems.LIDAR;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.FusibleNavX;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.controllers.TeensyController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.EncoderPair;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDriveShifting;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.ServoSubsystem;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RobotMap is a map of the physical robot. It stores all port numbers used for all
 * sensors and actuators, which provides flexibility changing wiring and makes checking
 * the wiring easier. Whole-robot metrics such as robot dimensions can also be stored here,
 * But any subsystem-specific constants should be stored in the respective subsystem's
 * class. Finally, this class stores the instances of all the robot's components (subsystems,
 * sensors, motion controllers, controls, etc.).
 */
public class RobotMap {
	public static class Port {
		public static class HumanInput {
			public static final int joystick = 0;
			public static final int xboxController = 1;
			public static final int teensyStick = 2;
		}

		public static class CANMotor {
			public static final int ballioDirectionalRoller = 1;
			public static final int ballioHopperRollers = 2;
			public static final int ballioElevatorAndIntakeRoller = 3;
			public static final int flywheelLeftMotor = 6; // WIP
			public static final int flywheelRightMotor = 7; // WIP
		}

		public static class PWM {
			public static final int leftDriveA = 1;
			public static final int leftDriveB = 2;
			public static final int rightDriveA = 3;
			public static final int rightDriveB = 4;
			public static final int climbMotorA = 5;
			public static final int climbMotorB = 6;
			public static final int gearioIntakeRoller = 7;
			public static final int ballioDoorServo = 8;
			public static final int lidarMotor = 9;
		}

		public static class CAN {
			public static final int matchConfigBroadcast = 0x600;
			public static final int lidarEncoder = 0x607;
			public static final int leftEncoder = 0x610;
			public static final int rightEncoder = 0x611;
		}

		public static class Pneumatics {
			// Shifter - blue solenoid
			public static final int shifterUp = 6;
			public static final int shifterDown = 7;
			// GearIO gull wings - red solenoid
			public static final int gearioGullWingsUp = 0;
			public static final int gearioGullWingsDown = 1;
			// GearIO ramp - yellow solenoid
			public static final int gearioRampUp = 2;
			public static final int gearioRampDown = 3;
			// Hopper - green solenoid
			public static final int hopperDown = 4;
			public static final int hopperUp = 5;
		}
	}

	public static class Metrics {
		public static final double WHEEL_PULSES_PER_REVOLUTION = 1024;
		public static final double WHEEL_DIAMETER_INCHES = 3.5;
		public static final double WHEEL_CIRCUMFERENCE_INCHES = Metrics.WHEEL_DIAMETER_INCHES * Math.PI;
		public static final double WHEEL_INCHES_PER_PULSE = Metrics.WHEEL_CIRCUMFERENCE_INCHES
			/ Metrics.WHEEL_PULSES_PER_REVOLUTION;
		public static final double HIGH_TO_LOW_GEAR_RATIO = 3.15;
	}

	public static class Component {
		public static PDP pdp;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static Motor leftWheel;
		public static Motor rightWheel;
		public static AutoSolenoidShifters shifter;
		public static TankDriveShifting chassis;
		public static BallIO ballIO;
		public static GearIO gearIO;
		public static Climber climber;
		public static Hopper hopper;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		public static TeensyController teensyStick;
		public static FusibleNavX navx;
		public static AligningCamera gearAlignCamera;
		public static CANEncoder lidarEncoder;
		public static CustomPIDController lidarMC;
		public static LIDAR lidar;
		public static MotionController chassisDriveMC;
		public static MotionController chassisTurnMC;
		public static Subsystem[] mainSubsystems;
	}

	public RobotMap() {
		// Chassis
		Component.pdp = new PDP();
		Component.leftWheelEncoder = new CANEncoder("LeftEncoder", Port.CAN.leftEncoder);
		Component.rightWheelEncoder = new CANEncoder("RightEncoder", Port.CAN.rightEncoder);
		Component.leftWheelEncoder.setDistancePerPulse(Metrics.WHEEL_INCHES_PER_PULSE);
		Component.rightWheelEncoder.setDistancePerPulse(Metrics.WHEEL_INCHES_PER_PULSE);
		Component.leftWheel = new Motor("LeftWheel", new AccelerationCap(Component.pdp),
			new VictorSP(Port.PWM.leftDriveA), new VictorSP(Port.PWM.leftDriveB));
		Component.leftWheel.setInverted(true);
		Component.rightWheel = new Motor("RightWheel", new AccelerationCap(Component.pdp),
			new VictorSP(Port.PWM.rightDriveA), new VictorSP(Port.PWM.rightDriveB));
		Component.rightWheel.setInverted(true);
		Component.shifter = new AutoSolenoidShifters(Port.Pneumatics.shifterUp, Port.Pneumatics.shifterDown);
		Component.chassis = new TankDriveShifting("2017-Chassis", Component.leftWheel, Component.rightWheel, Component.shifter);
		// BallIO
		Motor ballioDirectionalRoller = new Motor("BallioDirectionalRoller",
			new CANTalon(Port.CANMotor.ballioDirectionalRoller));
		ballioDirectionalRoller.setInverted(true);
		Motor ballioHopperRollers = new Motor("BallioHopperRollers", new CANTalon(Port.CANMotor.ballioHopperRollers));
		ballioHopperRollers.setInverted(true);
		Motor ballioElevatorAndIntakeRoller = new Motor("BallioElevatorAndIntakeRoller", new AccelerationCap(Component.pdp),
			new CANTalon(Port.CANMotor.ballioElevatorAndIntakeRoller));
		ServoSubsystem ballioDoorServo = new ServoSubsystem(new Servo(Port.PWM.ballioDoorServo));
		Component.ballIO = new BallIO(ballioDirectionalRoller, ballioElevatorAndIntakeRoller, ballioHopperRollers,
			ballioDoorServo);
		// GearIO
		Motor gearioIntakeRoller = new Motor("GearioIntakeRoller", new VictorSP(Port.PWM.gearioIntakeRoller));
		DoubleSolenoid gearioGullWings = new DoubleSolenoid(Port.Pneumatics.gearioGullWingsUp,
			Port.Pneumatics.gearioGullWingsDown);
		DoubleSolenoid gearioRamp = new DoubleSolenoid(Port.Pneumatics.gearioRampUp,
			Port.Pneumatics.gearioRampDown);
		Component.gearIO = new GearIO(gearioIntakeRoller, gearioGullWings, gearioRamp);
		// Climber
		Component.climber = new Climber(new VictorSP(Port.PWM.climbMotorA), new VictorSP(Port.PWM.climbMotorB));
		// Hopper
		Component.hopper = new Hopper(new DoubleSolenoid(Port.Pneumatics.hopperDown, Port.Pneumatics.hopperUp));
		// Controls
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(HumanInterfaceConfig.XBOX_DEADZONE);
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(HumanInterfaceConfig.JOYSTICK_DEADZONE);
		Component.teensyStick = new TeensyController(Port.HumanInput.teensyStick,
			HumanInterfaceConfig.TEENSY_STICK_NUM_BUTTONS);
		// Sensors
		Component.navx = new FusibleNavX(SerialPort.Port.kMXP);
		Component.gearAlignCamera = new AligningCamera(PIDSourceType.kRate);
		// LIDAR
		Component.lidarEncoder = new CANEncoder("LIDAREncoder", Port.CAN.lidarEncoder);
		Component.lidarEncoder.setPIDSourceType(PIDSourceType.kRate);
		Component.lidarMC = new CustomPIDController(LIDAR.TURN_P, LIDAR.TURN_I, LIDAR.TURN_D,
			LIDAR.TURN_F, Component.lidarEncoder);
		Component.lidarMC.setOutputRange(LIDAR.MIN_MOTOR_OUTPUT, LIDAR.MAX_MOTOR_OUTPUT);
		Component.lidar = new LIDAR(new Spark(Port.PWM.lidarMotor), Component.lidarMC);
		// Motion controllers
		Component.chassisTurnMC = new CustomPIDController(0.01, 0.0, -0.02, Component.navx);
		Component.chassisTurnMC.setInputRange(-180, 180);
		Component.chassisTurnMC.setContinuous(true);
		Component.chassisDriveMC = new CustomPIDController(0.0, 0.0, -0.0,
			new EncoderPair(Component.leftWheelEncoder, Component.rightWheelEncoder));
		Component.chassisDriveMC.setAbsoluteTolerance(1.0);
		// Main subsystems (the ones that get monitored on SmartDashboard)
		Component.mainSubsystems = new Subsystem[] {Component.chassis, Component.ballIO, Component.climber, Component.hopper,
				Component.lidar};
	}
}
