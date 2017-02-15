package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.HumanInterfaceConfig;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.robot.subsystems.Climber;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Hopper;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.sovereignty.FusibleNavX;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
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
		}

		public static class CANMotor {
			public static final int ballioDirectionalRoller = 1;
			public static final int ballioHopperRollers = 2;
			public static final int ballioElevatorAndIntakeRoller = 3;
			public static final int climbMotorA = 4;
			public static final int climbMotorB = 5;
			public static final int flywheelMotorA = 6; // WIP
			public static final int flywheelMotorB = 7; // WIP
			public static final int indexerMotor = 8; // WIP
		}

		public static class PWM {
			public static final int leftDriveA = 1;
			public static final int leftDriveB = 2;
			public static final int rightDriveA = 3;
			public static final int rightDriveB = 4;
			public static final int ballioDoorServo = 8;
		}

		public static class CAN {
			public static final int leftEncoder = 0x611;
			public static final int rightEncoder = 0x612;
			public static final int trayEncoder = 0x604;
			public static final int flywheelEncoder = 0x605;
			public static final int elevatorEncoder = 0x606;
		}

		public static class Pneumatics {
			public static final int solenoidUp = 0;
			public static final int solenoidDown = 1;
			public static final int hopperDown = 4;
			public static final int hopperUp = 5;
		}
	}

	public static class Metrics {
		public static final double WHEEL_PULSES_PER_REVOLUTION = 1024;
	}

	public static class Component {
		public static PDP pdp;
		public static SolenoidShifters shifter;
		public static TankDriveShifting chassis;
		public static Motor leftWheel;
		public static Motor rightWheel;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static MotionController chassisDriveMC;
		public static BallIO ballIO;
		public static Hopper hopper;
		public static Subsystem[] mainSubsystems;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
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
		Component.leftWheelEncoder = new CANEncoder("LeftEncoder", Port.CAN.leftEncoder, false);
		Component.rightWheelEncoder = new CANEncoder("RightEncoder", Port.CAN.rightEncoder, false);
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
		Motor ballioDirectionalRoller = new Motor(new CANTalon(Port.CANMotor.ballioDirectionalRoller));
		ballioDirectionalRoller.setInverted(true);
		Motor ballioHopperRollers = new Motor(new CANTalon(Port.CANMotor.ballioHopperRollers));
		ballioHopperRollers.setInverted(true);
		Motor ballioElevatorAndIntakeRoller = new Motor(new CANTalon(Port.CANMotor.ballioElevatorAndIntakeRoller));
		ServoSubsystem ballioDoorServo = new ServoSubsystem(new Servo(Port.PWM.ballioDoorServo));
		Component.ballIO = new BallIO(ballioDirectionalRoller, ballioElevatorAndIntakeRoller, ballioHopperRollers,
			ballioDoorServo);
		// Climber
		Component.climber = new Climber(new CANTalon(Port.CANMotor.climbMotorA), new CANTalon(Port.CANMotor.climbMotorB));
		// Shooter
		Motor flywheelMotorA = new Motor(new CANTalon(Port.CANMotor.flywheelMotorA));
		Motor flywheelMotorB = new Motor(new CANTalon(Port.CANMotor.flywheelMotorB));
		CustomEncoder flywheelEncoder = new CANEncoder("FlywheelEncoder", Port.CAN.flywheelEncoder, false);
		Component.flywheel = new Flywheel(flywheelMotorA, flywheelMotorB, flywheelEncoder);
		Motor indexer = new Motor(new CANTalon(Port.CANMotor.indexerMotor));
		Component.shooter = new Shooter(Component.flywheel, indexer);
		// Hopper
		Component.hopper = new Hopper(new DoubleSolenoid(Port.Pneumatics.hopperDown, Port.Pneumatics.hopperUp));
		// Human inputs
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(HumanInterfaceConfig.JOYSTICK_DEADZONE);
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(HumanInterfaceConfig.XBOX_DEADZONE);
		// Main Subsystems
		Component.alignCamera = new AligningCamera(PIDSourceType.kRate);
		Component.mainSubsystems = new Subsystem[] {Component.chassis, Component.ballIO, Component.climber, Component.shooter,
				Component.hopper};
	}
}
