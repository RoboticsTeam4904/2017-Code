package org.usfirst.frc4904.robot.humaninterface;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.AutoShifter;
import org.usfirst.frc4904.robot.vision.AligningCameraGRIP;
import org.usfirst.frc4904.sovereignty.strategies.GearAlign;

public class HumanInterfaceConfig {
	public static final GearAlign gearAlign = new GearAlign(new AligningCameraGRIP());
	public static final AutoShifter autoShifter = new AutoShifter(RobotMap.Component.shifter,
		RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder);

	private HumanInterfaceConfig() {}
}
