package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
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
			public static final int trayInnie = 3;
			public static final int flywheelAMotor = 4;
			public static final int flywheelBMotor = 5;
			public static final int vomitElevator = 6;
			public static final int vomitOuttakeRoller = 7;
		}
		
		public static class CAN {
			public static final int leftEncoder = 0x602;
			public static final int rightEncoder = 0x603;
			public static final int trayEncoder = 0x604;
			public static final int flywheelEncoder = 0x605;
			public static final int elevatorEncoder = 0x606;
		}
		
		public static class CANMotor {}
		
		public static class PCM {}
		
		public static class Pneumatics {
			public static final int gearXAxisUp = 0;
			public static final int gearXAxisDown = 1;
			public static final int gearYAxisRight = 2;
			public static final int gearYAxisLeft = 3;
		}
	}
	
	public static class Constant {
		public static class HumanInput {
			public static final double X_SPEED_SCALE = 1;
			public static final double Y_SPEED_SCALE = 1;
			public static final double TURN_SPEED_SCALE = 1;
			public static final double XBOX_MINIMUM_THRESHOLD = 0.1;
			public static final double OPERATOR_JOYSTICK_MINIMUM_THRESHOLD = 0.1;
		}
		
		public static class RobotMetric {
			public static final double WIDTH = 0;
			public static final double LENGTH = 0;
			public static final double WHEEL_ENCODER_PPR = 0;
			public static final double WHEEL_DIAMETER = 0;
			public static final double WHEEL_CIRCUMFERENCE = RobotMetric.WHEEL_DIAMETER * Math.PI;
		}
		
		public static class AutonomousMetric {}
		
		public static class FieldMetric {}
		
		public static class Network {
			public static final String VISION_GOAL_NETWORK_TABLE_ADDRESS = "GRIP/myContoursReport";
		}
		
		public static class Component {}
	}
	
	public static class Component {
		public static PDP pdp;
		public static PositionEncodedMotor leftWheel;
		public static PositionEncodedMotor rightWheel;
		public static Subsystem[] mainSubsystems;
	}
	
	public static class HumanInput {
		public static class Driver {
			public static CustomXbox xbox;
		}
		
		public static class Operator {
			public static CustomJoystick stick;
		}
	}
	public static CustomPIDController timPID;
	
	public RobotMap() {
		Component.pdp = new PDP();
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Operator.stick.setDeadzone(Constant.HumanInput.OPERATOR_JOYSTICK_MINIMUM_THRESHOLD);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
		// Main Subsystems
		Component.mainSubsystems = new Subsystem[] {};
	}
}
