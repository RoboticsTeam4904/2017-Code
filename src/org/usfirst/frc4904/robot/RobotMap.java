package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.commands.RampSet;
import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.robot.subsystems.AutoSolenoidShifters;
import org.usfirst.frc4904.robot.subsystems.Climber;
import org.usfirst.frc4904.robot.subsystems.FloorIO;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.GearIO.RampState;
import org.usfirst.frc4904.robot.subsystems.LIDAR;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.FusibleNavX;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.controllers.TeensyController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CANTalonEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.EncoderPair;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDriveShifting;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.EnableableModifier;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
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
			public static final int flywheelMotorA = 6;
			public static final int flywheelMotorB = 7;
			public static final int indexerMotor = 8;
			public static final int floorioRoller = 1;
			public static final int windexerMotor = 9;
		}

		public static class PWM {
			public static final int leftDriveA = 1;
			public static final int leftDriveB = 2;
			public static final int rightDriveA = 3;
			public static final int rightDriveB = 4;
			public static final int climbMotorA = 5;
			public static final int climbMotorB = 6;
			public static final int gearioIntakeRoller = 7;
			public static final int lidarMotor = 9;
		}

		public static class CAN {
			public static final int matchConfigBroadcast = 0x600;
			public static final int lidarEncoder = 0x607;
			public static final int leftEncoder = 0x611;
			public static final int rightEncoder = 0x610;
		}

		public static class Pneumatics {
			public static final int shifterUp = 4;
			public static final int shifterDown = 5;
			public static final int gearioGullWingsUp = 2;
			public static final int gearioGullWingsDown = 3;
			public static final int gearioRampUp = 0;
			public static final int gearioRampDown = 1;
			public static final int floorioPistonUp = 6;
			public static final int floorioPistonDown = 7;
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
		public static EnableableModifier rightWheelAccelerationCap;
		public static EnableableModifier leftWheelAccelerationCap;
		public static Motor leftWheel;
		public static Motor rightWheel;
		public static AutoSolenoidShifters shifter;
		public static TankDriveShifting chassis;
		public static GearIO gearIO;
		public static FloorIO floorIO;
		public static Climber climber;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		public static TeensyController teensyStick;
		public static FusibleNavX navx;
		public static AligningCamera gearAlignCamera;
		public static CANEncoder lidarEncoder;
		public static CustomPIDController lidarMC;
		public static LIDAR lidar;
		public static MotionController chassisDriveMC;
		public static CustomPIDController chassisTurnMC;
		public static Flywheel flywheel;
		public static Shooter shooter;
		public static Subsystem[] mainSubsystems;
	}

	public RobotMap() {
		// Chassis
		Component.pdp = new PDP();
		Component.leftWheelEncoder = new CANEncoder("LeftEncoder", Port.CAN.leftEncoder);
		Component.rightWheelEncoder = new CANEncoder("RightEncoder", Port.CAN.rightEncoder);
		Component.leftWheelEncoder.setDistancePerPulse(Metrics.WHEEL_INCHES_PER_PULSE);
		Component.rightWheelEncoder.setDistancePerPulse(Metrics.WHEEL_INCHES_PER_PULSE);
		Component.rightWheelEncoder.setReverseDirection(true);
		Component.leftWheelAccelerationCap = new EnableableModifier(new AccelerationCap(Component.pdp));
		Component.leftWheelAccelerationCap.enable();
		Component.rightWheelAccelerationCap = new EnableableModifier(new AccelerationCap(Component.pdp));
		Component.rightWheelAccelerationCap.enable();
		Component.leftWheel = new Motor("LeftWheel", Component.leftWheelAccelerationCap,
			new VictorSP(Port.PWM.leftDriveA), new VictorSP(Port.PWM.leftDriveB));
		Component.leftWheel.setInverted(true);
		Component.rightWheel = new Motor("RightWheel", Component.rightWheelAccelerationCap,
			new VictorSP(Port.PWM.rightDriveA), new VictorSP(Port.PWM.rightDriveB));
		Component.rightWheel.setInverted(true);
		Component.shifter = new AutoSolenoidShifters(Port.Pneumatics.shifterUp, Port.Pneumatics.shifterDown);
		Component.chassis = new TankDriveShifting("2017-Chassis", Component.leftWheel, Component.rightWheel, Component.shifter);
		// GearIO
		Motor gearioIntakeRoller = new Motor("GearioIntakeRoller", new VictorSP(Port.PWM.gearioIntakeRoller));
		DoubleSolenoid gearioGullWings = new DoubleSolenoid(Port.Pneumatics.gearioGullWingsUp,
			Port.Pneumatics.gearioGullWingsDown);
		DoubleSolenoid gearioRamp = new DoubleSolenoid(Port.Pneumatics.gearioRampUp,
			Port.Pneumatics.gearioRampDown);
		Component.gearIO = new GearIO(gearioIntakeRoller, gearioGullWings, gearioRamp);
		new RampSet(RampState.EXTENDED).start();
		// FloorIO
		Motor floorioRoller = new Motor("FloorioRoller", new CANTalon(Port.CANMotor.floorioRoller));
		floorioRoller.setInverted(true);
		DoubleSolenoid floorioPiston = new DoubleSolenoid(Port.Pneumatics.floorioPistonUp, Port.Pneumatics.floorioPistonDown);
		Component.floorIO = new FloorIO(floorioRoller, floorioPiston);
		// Climber
		Component.climber = new Climber(new VictorSP(Port.PWM.climbMotorA), new VictorSP(Port.PWM.climbMotorB));
		// Shooter
		CANTalon flywheelMotorA = new CANTalon(Port.CANMotor.flywheelMotorA);
		CANTalon flywheelMotorB = new CANTalon(Port.CANMotor.flywheelMotorB);
		CustomEncoder flywheelEncoder = new CANTalonEncoder("FlywheelEncoder", flywheelMotorA);
		flywheelEncoder.setDistancePerPulse(Flywheel.ENCODER_PPS_TO_RPM);
		Component.flywheel = new Flywheel(flywheelMotorA, flywheelMotorB, flywheelEncoder);
		Motor indexer = new Motor("Indexer", new CANTalon(Port.CANMotor.indexerMotor));
		Motor windexer = new Motor("Windexer", new CANTalon(Port.CANMotor.windexerMotor));
		Component.shooter = new Shooter(Component.flywheel, indexer, windexer);
		// Controls
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(HumanInterfaceConfig.XBOX_DEADZONE);
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(HumanInterfaceConfig.JOYSTICK_DEADZONE);
		Component.teensyStick = new TeensyController(Port.HumanInput.teensyStick,
			HumanInterfaceConfig.TEENSY_STICK_NUM_BUTTONS);
		// Sensors
		Component.navx = new FusibleNavX(SerialPort.Port.kMXP);
		Component.gearAlignCamera = new AligningCamera();
		// LIDAR
		Component.lidarEncoder = new CANEncoder("LIDAREncoder", Port.CAN.lidarEncoder);
		Component.lidarEncoder.setPIDSourceType(PIDSourceType.kRate);
		Component.lidarMC = new CustomPIDController(LIDAR.TURN_P, LIDAR.TURN_I, LIDAR.TURN_D,
			LIDAR.TURN_F, Component.lidarEncoder);
		Component.lidarMC.setOutputRange(LIDAR.MIN_MOTOR_OUTPUT, LIDAR.MAX_MOTOR_OUTPUT);
		Component.lidar = new LIDAR(new Spark(Port.PWM.lidarMotor), Component.lidarMC);
		// Motion controllers
		Component.chassisTurnMC = new CustomPIDController(0.03, 0.0, -0.01, Component.navx);
		Component.chassisTurnMC.setMinimumNominalOutput(0.24);
		Component.chassisTurnMC.setInputRange(-180, 180);
		Component.chassisTurnMC.setContinuous(true);
		Component.chassisTurnMC.setAbsoluteTolerance(1.0);
		Component.chassisDriveMC = new CustomPIDController(0.001, 0.0, -0.002,
			new EncoderPair(Component.leftWheelEncoder, Component.rightWheelEncoder));
		// Main subsystems (the ones that get monitored on SmartDashboard)
		Component.mainSubsystems = new Subsystem[] {Component.chassis, Component.climber, Component.shooter, Component.lidar,
				Component.floorIO};
	}
}
