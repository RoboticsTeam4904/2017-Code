package org.usfirst.frc4904.sovereignty.profiles;


public class MotionTrajectorySegment {
	protected final double initVel;
	protected final double finVel;
	protected final double maxVel;
	protected final double length;

	public MotionTrajectorySegment(double initVel, double finVel, double maxVel, double length) {
		this.initVel = initVel;
		this.finVel = finVel;
		this.maxVel = maxVel;
		this.length = length;
	}
}
