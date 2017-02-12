package org.usfirst.frc4904.robot.humaninterface;


import org.usfirst.frc4904.sovereignty.strategies.GearAlign;

public class HumanInterfaceConfig {
	public static final GearAlign gearAlign = new GearAlign();
	public static final double JOYSTICK_DEADZONE = 0.1;

	private HumanInterfaceConfig() {}
}
