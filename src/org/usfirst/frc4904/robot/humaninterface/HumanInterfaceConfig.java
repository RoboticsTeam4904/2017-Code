package org.usfirst.frc4904.robot.humaninterface;


import org.usfirst.frc4904.robot.vision.AligningCameraGRIP;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;

public class HumanInterfaceConfig {
	public static final GearAlign gearAlign = new GearAlign(new AligningCameraGRIP());

	private HumanInterfaceConfig() {}
}
