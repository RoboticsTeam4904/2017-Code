package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.robot.subsystems.Climber;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.GearIO;
import org.usfirst.frc4904.robot.subsystems.Hopper;
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
import org.usfirst.frc4904.standard.custom.sensors.EncoderGroup;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
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
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Port { // TODO: Update ports to match those on Timón.
		public static class HumanInput {
			public static final int joystick = 0;
			public static final int xboxController = 1;
			public static final int teensyStick = 2;
		}

		public static class CANMotor {
			public static final int ballioDirectionalRoller = 1;
			public static final int ballioHopperRollers = 2;
			public static final int ballioElevatorAndIntakeRoller = 3;
			public static final int climbMotorA = 4;
			public static final int climbMotorB = 5;
			public static final int flywheelMotorA = 6;
			public static final int flywheelMotorB = 7;
			public static final int indexerMotor = 8;
		}

		public static class PWM {
			// SOME MOTORS AREN'T EXACT - work in progress
			public static int leftDriveA = 1;
			public static int leftDriveB = 2;
			public static int rightDriveA = 3;
			public static int rightDriveB = 4;
			public static final int climbMotorA = 5;
			public static final int climbMotorB = 6;
			public static final int gearioIntakeRoller = 7;
			public static final int ballioDoorServo = 8;
			public static final int lidarMotor = 9; // WIP
		}

		public static class CAN {
			public static final int matchConfigBroadcast = 0x600;
			public static final int flywheelEncoder = 0x605;
			public static final int lidarTurnEncoder = 0x607;
			public static final int leftEncoder = 0x611;
			public static final int rightEncoder = 0x612;
		}

		public static class Pneumatics {
			// Shifter - blue solenoid
			public static final int solenoidUp = 6;
			public static final int solenoidDown = 7;
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
	}

	public static class Component {
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		public static TeensyController teensyStick;
		public static PDP pdp;
		public static Motor leftWheel;
		public static Motor rightWheel;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static SolenoidShifters shifter;
		public static TankDriveShifting chassis;
		public static MotionController chassisDriveMC;
		public static BallIO ballIO;
		public static GearIO gearIO;
		public static Hopper hopper;
		public static Subsystem[] mainSubsystems;
		public static CustomPIDController lidarMC;
		public static CANEncoder lidarTurnEncoder;
		public static LIDAR lidar;
		public static FusibleNavX navx;
		public static Climber climber;
		public static MotionController chassisTurnMC;
		public static AligningCamera alignCamera;
		public static Flywheel flywheel;
		public static Shooter shooter;
	}

	public RobotMap() {
		Component.pdp = new PDP();
		Component.shifter = new SolenoidShifters(Port.Pneumatics.solenoidUp, Port.Pneumatics.solenoidDown);
		Component.navx = new FusibleNavX(SerialPort.Port.kMXP);
		Component.chassisDriveMC = new CustomPIDController(0.01, 0.0, -0.02, RobotMap.Component.navx);
		Component.chassisDriveMC.setInputRange(-180, 180);
		Component.chassisDriveMC.setContinuous(true);
		Component.leftWheelEncoder = new CANEncoder("LeftEncoder", Port.CAN.leftEncoder);
		Component.rightWheelEncoder = new CANEncoder("RightEncoder", Port.CAN.rightEncoder);
		Component.leftWheelEncoder.setDistancePerPulse(Metrics.WHEEL_PULSES_PER_REVOLUTION);
		Component.rightWheelEncoder.setDistancePerPulse(Metrics.WHEEL_PULSES_PER_REVOLUTION);
		Component.chassisDriveMC = new CustomPIDController(0.001, 0.0, -0.002,
			new EncoderGroup(100, Component.leftWheelEncoder, Component.rightWheelEncoder));
		Component.leftWheel = new Motor("LeftWheel", false, new AccelerationCap(Component.pdp),
			new VictorSP(Port.PWM.leftDriveA), new VictorSP(Port.PWM.leftDriveB));
		Component.leftWheel.setInverted(true);
		Component.rightWheel = new Motor("RightWheel", false, new AccelerationCap(Component.pdp),
			new VictorSP(Port.PWM.rightDriveA), new VictorSP(Port.PWM.rightDriveB));
		Component.rightWheel.setInverted(true);
		Component.chassis = new TankDriveShifting("2017-Chassis", Component.leftWheel, Component.rightWheel, Component.shifter);
		// BallIO
		Motor ballioDirectionalRoller = new Motor("BallioDirectionalRoller",
			new CANTalon(Port.CANMotor.ballioDirectionalRoller));
		ballioDirectionalRoller.setInverted(true);
		Motor ballioHopperRollers = new Motor("BallioHopperRollers", new CANTalon(Port.CANMotor.ballioHopperRollers));
		ballioHopperRollers.setInverted(true);
		Motor ballioElevatorAndIntakeRoller = new Motor("BallioElevatorAndIntakeRoller",
			new CANTalon(Port.CANMotor.ballioElevatorAndIntakeRoller));
		ServoSubsystem ballioDoorServo = new ServoSubsystem(new Servo(Port.PWM.ballioDoorServo));
		Component.ballIO = new BallIO(ballioDirectionalRoller, ballioElevatorAndIntakeRoller, ballioHopperRollers,
			ballioDoorServo);
		// GearIO
		Motor gearioIntakeRoller = new Motor(new VictorSP(Port.PWM.gearioIntakeRoller));
		DoubleSolenoid gearioGullWings = new DoubleSolenoid(Port.Pneumatics.gearioGullWingsUp,
			Port.Pneumatics.gearioGullWingsDown);
		DoubleSolenoid gearioRamp = new DoubleSolenoid(Port.Pneumatics.gearioRampUp,
			Port.Pneumatics.gearioRampDown);
		Component.gearIO = new GearIO(gearioIntakeRoller, gearioGullWings, gearioRamp);
		// Climber
		Component.climber = new Climber(new VictorSP(Port.PWM.climbMotorA), new VictorSP(Port.PWM.climbMotorB));
		// Shooter
		CANTalon flywheelMotorA = new CANTalon(Port.CANMotor.flywheelMotorA);
		CANTalon flywheelMotorB = new CANTalon(Port.CANMotor.flywheelMotorB);
		CustomEncoder flywheelEncoder = new CANTalonEncoder("FlywheelEncoder", flywheelMotorA);
		Component.flywheel = new Flywheel(flywheelMotorA, flywheelMotorB, flywheelEncoder);
		Component.flywheel.disableMotionController(); // TODO Remove once flywheel PID is tuned
		Motor indexer = new Motor("Indexer", new CANTalon(Port.CANMotor.indexerMotor));
		Component.shooter = new Shooter(Component.flywheel, indexer);
		// Hopper
		Component.hopper = new Hopper(new DoubleSolenoid(Port.Pneumatics.hopperDown, Port.Pneumatics.hopperUp));
		// Human inputs
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(HumanInterfaceConfig.JOYSTICK_DEADZONE);
		Component.teensyStick = new TeensyController(Port.HumanInput.teensyStick, 30);
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(HumanInterfaceConfig.XBOX_DEADZONE);
		// Main Subsystems
		Component.alignCamera = new AligningCamera(PIDSourceType.kRate);
		// LIDAR
		Component.lidarTurnEncoder = new CANEncoder("LIDAREncoder", Port.CAN.lidarTurnEncoder, false);
		Component.lidarTurnEncoder.setPIDSourceType(PIDSourceType.kRate);
		Component.lidarMC = new CustomPIDController(LIDAR.TURN_P, LIDAR.TURN_I, LIDAR.TURN_D,
			LIDAR.TURN_F, Component.lidarTurnEncoder);
		Component.lidarMC.setOutputRange(LIDAR.MIN_MOTOR_OUTPUT, LIDAR.MAX_MOTOR_OUTPUT);
		Component.lidar = new LIDAR(new Spark(Port.PWM.lidarMotor), Component.lidarMC);
		Component.mainSubsystems = new Subsystem[] {Component.chassis, Component.ballIO, Component.climber, Component.shooter,
				Component.hopper,
				Component.lidar};
	}
}
