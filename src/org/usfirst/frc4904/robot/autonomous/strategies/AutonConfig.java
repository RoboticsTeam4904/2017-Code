package org.usfirst.frc4904.robot.autonomous.strategies;


import edu.wpi.first.wpilibj.DriverStation;

public class AutonConfig {
	public static final double ALLIANCE_FACTOR = DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue ? 1.0
		: -1.0;
	public static final double DEAD_RECKON_OUTTAKE_TIME = 1.5;
	public static final double DEAD_RECKON_TIME_BACK_TO_CLEAR_PEG = 1.0;
	public static final double DEAD_RECKON_DRIVE_SPEED = -0.6;
	public static final double DEAD_RECKON_TURN_SPEED = -0.45;
	public static final double DEAD_RECKON_WIGGLE_TIME = 2.0; // It's wiggle time!
}
