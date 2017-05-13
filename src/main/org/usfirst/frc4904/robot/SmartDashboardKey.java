package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	SUBSYSTEM_SUMMARY("Subsystem summary"), VISION_AUTOCALIBRATION("Autocalibration"), VISION_AUTOCALIBRATION_COMPLETE(
		"Autocalibration complete");
	public final String key;

	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
