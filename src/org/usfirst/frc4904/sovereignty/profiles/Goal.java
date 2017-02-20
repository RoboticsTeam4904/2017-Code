package org.usfirst.frc4904.sovereignty.profiles;


public class Goal {
	// TODO: These numbers mean nothing.
	public static final Goal gearGoal = new Goal("Gear", 10, -2, 135);
	public final String name;
	public final double posX;
	public final double posY;
	public final double theta;

	public Goal(String name, double posX, double posY, double theta) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.theta = theta;
	}
}
