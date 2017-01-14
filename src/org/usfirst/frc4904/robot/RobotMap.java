package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.robot.vision.AligningCamera;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static class Port {
		public static final int JOYSTICK = 0;
		public static final int CONTROLLER = 1;
	}
	
	public static class Component {
		public static PDP pdp;
		public static Chassis chassis;
		public static Subsystem[] mainSubsystems;
		public static CustomXbox driverXbox;
		public static CustomJoystick operatorStick;
		// Vision
		public static AligningCamera alignCamera;
	}
	
	public RobotMap() {
		Component.pdp = new PDP();
		// Human inputs
		Component.operatorStick = new CustomJoystick(Port.JOYSTICK);
		Component.operatorStick.setDeadzone(DefaultOperator.JOYSTICK_MIN_THRESH);
		Component.driverXbox = new CustomXbox(Port.CONTROLLER);
		Component.driverXbox.setDeadZone(DefaultDriver.CONTROLLER_MIN_THRESH);
		// Main Subsystems
		Component.mainSubsystems = new Subsystem[] {};
		Component.alignCamera = new AligningCamera(PIDSourceType.kRate);
	}
}
