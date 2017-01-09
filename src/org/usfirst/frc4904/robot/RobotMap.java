package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.DefaultDriver;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
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
	
	public RobotMap() {
		Component.pdp = new PDP();
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.JOYSTICK);
		HumanInput.Operator.stick.setDeadzone(DefaultOperator.JOYSTICK_MIN_THRESH);
		HumanInput.Driver.xbox = new CustomXbox(Port.CONTROLLER);
		HumanInput.Driver.xbox.setDeadZone(DefaultDriver.CONTROLLER_MIN_THRESH);
		// Main Subsystems
		Component.mainSubsystems = new Subsystem[] {};
	}
}
