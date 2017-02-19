package org.usfirst.frc4904.robot;

public enum SmartDashboardKey {
	HOPPER("Hopper Capacity"), SUBSYSTEM_SUMMARY("Subsystem summary");
	public final String key;

	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
