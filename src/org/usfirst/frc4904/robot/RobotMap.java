package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.subsystems.BallIO;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
			public static final int leftEncoder = 0x602;
			public static final int rightEncoder = 0x603;
			public static final int trayEncoder = 0x604;
			public static final int flywheelEncoder = 0x605;
			public static final int elevatorEncoder = 0x606;
		}
		
		public static class CANMotor {}
		
		public static class PCM {}
		
		public static class Pneumatics {
			public static final int bioShifterUp = 2;
			public static final int bioShifterDown = 3;
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
		public static CustomEncoder leftWheelEncoder;
		public static CustomEncoder rightWheelEncoder;
		public static Flywheel flywheel;
		public static BallIO ballIO;
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
		// Chassis
		Component.leftWheelEncoder = new CANEncoder(Port.CAN.leftEncoder);
		Component.leftWheelEncoder.setReverseDirection(true);
		Component.leftWheel = new PositionEncodedMotor("leftWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.leftWheelEncoder), new VictorSP(Port.PWM.leftDriveMotor));
		Component.leftWheel.disablePID(); // TODO add encoders
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.rightWheel = new PositionEncodedMotor("rightWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.rightWheelEncoder), new VictorSP(Port.PWM.rightDriveMotor));
		Component.rightWheel.disablePID(); // TODO add encoders
		// Ball-Intake-Outtake
		Motor bioTopMotor = new Motor(new VictorSP(Port.PWM.bioTopMotor));
		Motor bioLeftMotor = new Motor(new VictorSP(Port.PWM.bioLeftMotor));
		Motor bioMainMotor = new Motor(new VictorSP(Port.PWM.bioMainMotor));
		Component.ballIO = new BallIO(bioTopMotor, bioLeftMotor, bioMainMotor, new DoubleSolenoid(Port.Pneumatics.bioShifterDown, Port.Pneumatics.bioShifterUp));
		// Flywheel
		Motor leftFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelLeftMotor));
		leftFlywheelMotor.setInverted(true);
		Motor rightFlywheelMotor = new Motor(new VictorSP(Port.PWM.flywheelRightMotor));
		rightFlywheelMotor.setInverted(false);
		Component.flywheel = new Flywheel(leftFlywheelMotor, rightFlywheelMotor);
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Operator.stick.setDeadzone(Constant.HumanInput.OPERATOR_JOYSTICK_MINIMUM_THRESHOLD);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
		Component.mainSubsystems = new Subsystem[] {Component.flywheel, Component.ballIO};
	}
}
