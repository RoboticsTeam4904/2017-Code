package org.usfirst.frc4904.robot.humaninterface;


import org.usfirst.frc4904.robot.vision.AligningCameraGRIP;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;

public class HumanInterfaceConfig {
	public static final GearAlign gearAlign = new GearAlign(new AligningCameraGRIP());
	public static final double XBOX_MINIMUM_THRESHOLD = 0.1;

	private HumanInterfaceConfig() {}
}
