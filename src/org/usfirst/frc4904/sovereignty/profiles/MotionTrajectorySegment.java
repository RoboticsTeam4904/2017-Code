package org.usfirst.frc4904.sovereignty.profiles;


public class MotionTrajectorySegment {
	protected double initVel;
	protected double finVel;
	protected double maxVel;
	protected double length;
	protected boolean partial;

	public MotionTrajectorySegment(double initVel, double finVel,
		double maxVel, double length) {
		this.initVel = initVel;
		this.finVel = finVel;
		this.maxVel = maxVel;
		this.length = length;
		partial = false;
	}

	public MotionTrajectorySegment(double initVel, double maxVel) {
		this.initVel = initVel;
		this.maxVel = maxVel;
		partial = true;
	}
}
