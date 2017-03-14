package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	SUBSYSTEM_SUMMARY("Subsystem summary"), VISION_AUTOCALIBRATION("Autocalibration"), VISION_AUTOCALIBRATION_COMPLETE(
		"Autocalibration complete"), AUTON_APPROACH_CHOOSER("Auton approach chooser"), AUTON_REALIGN_CHOOSER(
			"Auton realign chooser"), AUTON_FINISH_CHOOSER("Auton finish chooser");
	public final String key;

	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
