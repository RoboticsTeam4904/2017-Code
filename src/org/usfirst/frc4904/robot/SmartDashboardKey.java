package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	SUBSYSTEM_SUMMARY("Subsystem summary"), VISION_AUTOCALIBRATION("Autocalibration"), VISION_AUTOCALIBRATION_COMPLETE(
		"Autocalibration complete"), BOILER_CENTER_ANGLE(
			"LIDAR Boiler Center Angle"), GEAR_PEG_CENTER_ANGLE("Gear Peg Center Angle");
	public final String key;

	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
