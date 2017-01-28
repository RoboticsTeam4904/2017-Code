package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.EncoderGroup;
import org.usfirst.frc4904.standard.custom.sensors.NavX;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.SolenoidShifters;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDriveShifting;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
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

		public static class Motors {
			public static class CAN {
				public static int leftDriveA = 1;
				public static int leftDriveB = 2;
				public static int rightDriveA = 3;
				public static int rightDriveB = 4;
			}
		}

		public static class PWM {
			// THE MOTORS AREN'T EXACT - Work In Progress
			public static final int leftDriveMotor = 0;
			public static final int rightDriveMotor = 1;
			public static final int flywheelLeftMotor = 2;
			public static final int flywheelRightMotor = 3;
			public static final int bioTopMotor = 4;
			public static final int bioLeftMotor = 5;
			public static final int bioMainMotor = 6;
		}

		public static class CAN {
			public static final int leftEncoder = 0x611;
			public static final int rightEncoder = 0x612;
			public static final int trayEncoder = 0x604;
			public static final int flywheelEncoder = 0x605;
			public static final int elevatorEncoder = 0x606;
		}

		public static class CANMotor {}

		public static class PCM {}

		public static class Pneumatics {
			public static final int bioShifterUp = 2;
			public static final int bioShifterDown = 3;
			public static final int solenoidUp = 0;
			public static final int solenoidDown = 1;
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
	}

	public static class PCM {}

	public static class Pneumatics {
		public static int solenoidUp = 0;
		public static int solenoidDown = 1;
	}

	public static class Metrics {
		public static final double WHEEL_PULSES_PER_REVOLUTION = 1024;
	}

	public static class Component {
		public static CustomXbox xbox;
		public static CustomJoystick stick;
		public static PDP pdp;
		public static TankDriveShifting chassis;
		public static Motor leftWheel;
		public static Motor rightWheel;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static MotionController chassisEncoderMC;
		public static Flywheel flywheel;
		public static BallIO ballIO;
		public static Subsystem[] mainSubsystems;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		public static AligningCamera alignCamera;
		public static CustomPIDController chassisMC;
		public static NavX navx;
		public static SolenoidShifters shifter;
	}

	public RobotMap() {
		Component.pdp = new PDP();
		Component.shifter = new SolenoidShifters(Port.Pneumatics.solenoidUp, Port.Pneumatics.solenoidDown);
		Component.navx = new NavX(SerialPort.Port.kMXP);
		Component.chassisMC = new CustomPIDController(0.01, 0.0, -0.02, RobotMap.Component.navx);
		Component.chassisMC.setInputRange(-180, 180);
		Component.chassisMC.setContinuous(true);
		Component.leftWheelEncoder = new CANEncoder("LeftEncoder", Port.CAN.leftEncoder, false);
		Component.rightWheelEncoder = new CANEncoder("RightEncoder", Port.CAN.rightEncoder, false);
		Component.chassisEncoderMC = new CustomPIDController(0.001, 0.0, -0.002,
			new EncoderGroup(100, Component.leftWheelEncoder, Component.rightWheelEncoder));
		Component.leftWheel = new Motor("LeftWheel", false, new AccelerationCap(Component.pdp),
			new CANTalon(Port.Motors.CAN.leftDriveA), new CANTalon(Port.Motors.CAN.leftDriveB));
		Component.rightWheel = new Motor("RightWheel", false, new AccelerationCap(Component.pdp),
			new CANTalon(Port.Motors.CAN.rightDriveA), new CANTalon(Port.Motors.CAN.rightDriveB));
		Component.leftWheelEncoder = new CANEncoder(Port.CAN.leftEncoder);
		// Ball-Intake-Outtake
		Motor bioTopMotor = new Motor(new VictorSP(Port.PWM.bioTopMotor));
		Motor bioLeftMotor = new Motor(new VictorSP(Port.PWM.bioLeftMotor));
		Motor bioMainMotor = new Motor(new VictorSP(Port.PWM.bioMainMotor));
		Component.ballIO = new BallIO(bioTopMotor, bioLeftMotor, bioMainMotor,
			new DoubleSolenoid(Port.Pneumatics.bioShifterDown, Port.Pneumatics.bioShifterUp));
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.leftWheelEncoder.setDistancePerPulse(Metrics.WHEEL_PULSES_PER_REVOLUTION);
		Component.rightWheelEncoder.setDistancePerPulse(Metrics.WHEEL_PULSES_PER_REVOLUTION);
		Component.chassis = new TankDriveShifting("2017-Chassis", Component.leftWheel, Component.rightWheel,
			Component.shifter);
		// Human inputs
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.operatorStick.setDeadzone(DefaultOperator.JOYSTICK_MIN_THRESH);
		Component.xbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.stick = new CustomJoystick(Port.HumanInput.joystick);
		Component.xbox.setDeadZone(DefaultDriver.XBOX_MINIMUM_THRESHOLD);
		// Main Subsystems
		Component.alignCamera = new AligningCamera(PIDSourceType.kRate);
		Component.mainSubsystems = new Subsystem[] {Component.chassis};
	}
}
