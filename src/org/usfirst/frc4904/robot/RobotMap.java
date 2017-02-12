package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
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
import org.usfirst.frc4904.standard.subsystems.motor.PositionSensorMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import com.ctre.CANTalon;
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
			public static final int teensyStick = 2;
		}

		public static class CANMotor {
			public static final int ballioDirectionalRoller = 1;
			public static final int ballioHopperRollers = 2;
			public static final int ballioElevatorAndIntakeRoller = 3;
		}

		public static class PWM {
			// SOME MOTORS AREN'T EXACT - work in progress
			public static int leftDriveA = 1;
			public static int leftDriveB = 2;
			public static int rightDriveA = 3;
			public static int rightDriveB = 4;
			public static final int flywheelLeftMotor = 5; // WIP
			public static final int flywheelRightMotor = 6; // WIP
			public static final int ballioDoorServo = 8;
		}

		public static class Serial {
			public static final int navX = 0;
		}

		public static class CAN {
			public static final int leftEncoder = 0x602;
			public static final int rightEncoder = 0x603;
			public static final int flywheelEncoder = 0x605;
		}

		public static class Pneumatics {
			public static final int solenoidUp = 4;
			public static final int solenoidDown = 5;
			public static final int bioShifterUp = 2;
			public static final int bioShifterDown = 3;
			public static int gearSolenoidUp = 0;// ports are WIP
			public static int gearSolenoidDown = 1;// ports are WIP
		}
	}

	public static class Metrics {
		public static final double WHEEL_PULSES_PER_REVOLUTION = 1024;
	}

	public static class Component {
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		public static CustomJoystick teensyStick;
		public static PDP pdp;
		public static PositionSensorMotor leftWheel;
		public static PositionSensorMotor rightWheel;
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static NavX navX;
		public static CustomPIDController chassisDrivePID;
		public static SolenoidShifters shifter;
		public static TankDriveShifting chassis;
		public static MotionController chassisDriveMC;
		public static SolenoidShifters gearSlotOpener;
		public static Servo gearFlap;
		public static Subsystem[] mainSubsystems;
		public static MotionController chassisTurnMC;
	}

	public static class HumanInput {
		public static class Driver {
			public static CustomXbox xbox;
		}

		public static class Operator {
			public static CustomJoystick stick;
			public static CustomJoystick teensyStick;
		}
		public static NavX navx;
		public static MotionController chassisTurnMC;
		public static AligningCamera alignCamera;
	}

	public RobotMap() {
		Component.pdp = new PDP();
		// Chassis
		Component.leftWheel = new PositionSensorMotor("LeftWheel", false, new AccelerationCap(Component.pdp),
			Component.chassisDriveMC,
			new VictorSP(Port.PWM.leftDriveA), new VictorSP(Port.PWM.leftDriveB));
		Component.leftWheel.disableMotionController();
		Component.rightWheel = new PositionSensorMotor("RightWheel", false, new AccelerationCap(Component.pdp),
			Component.chassisDriveMC,
			new VictorSP(Port.PWM.rightDriveA), new VictorSP(Port.PWM.rightDriveB));
		Component.rightWheel.disableMotionController();
		Component.navX = new NavX(SerialPort.Port.kOnboard);
		// Gear
		// Flywheel
		Motor leftFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelLeftMotor));
		leftFlywheelMotor.setInverted(true);
		Motor rightFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelRightMotor));
		rightFlywheelMotor.setInverted(false);
		// Human inputs
		Component.navX = new NavX(SerialPort.Port.kOnboard);
		Component.shifter = new SolenoidShifters(Port.Pneumatics.solenoidUp, Port.Pneumatics.solenoidDown);
		Component.navX = new NavX(SerialPort.Port.kMXP);
		Component.leftWheelEncoder = new CANEncoder("LeftEncoder", Port.CAN.leftEncoder, false);
		Component.rightWheelEncoder = new CANEncoder("RightEncoder", Port.CAN.rightEncoder, false);
		Component.leftWheelEncoder.setDistancePerPulse(Metrics.WHEEL_PULSES_PER_REVOLUTION);
		Component.rightWheelEncoder.setDistancePerPulse(Metrics.WHEEL_PULSES_PER_REVOLUTION);
		Component.chassisDriveMC = new CustomPIDController(0.001, 0.0, -0.002,
			new EncoderGroup(100, Component.leftWheelEncoder, Component.rightWheelEncoder));
		// Ball-Intake-Outtake
		Motor ballioDirectionalRoller = new Motor(new CANTalon(Port.CANMotor.ballioDirectionalRoller));
		ballioDirectionalRoller.setInverted(true);
		Motor ballioHopperRollers = new Motor(new CANTalon(Port.CANMotor.ballioHopperRollers));
		ballioHopperRollers.setInverted(true);
		Component.chassis = new TankDriveShifting("2017-Chassis", Component.leftWheel, Component.rightWheel, Component.shifter);
		// Human inputs
		Component.operatorStick = new CustomJoystick(Port.HumanInput.joystick);
		Component.teensyStick = new CustomJoystick(Port.HumanInput.teensyStick);
		Component.operatorStick.setDeadzone(DefaultOperator.JOYSTICK_MIN_THRESH);
		Component.driverXbox = new CustomXbox(Port.HumanInput.xboxController);
		Component.driverXbox.setDeadZone(DefaultDriver.XBOX_MINIMUM_THRESHOLD);
		// Main Subsystems
		Component.mainSubsystems = new Subsystem[] {};
	}
}
