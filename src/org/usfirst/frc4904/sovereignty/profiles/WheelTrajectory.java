package org.usfirst.frc4904.sovereignty.profiles;


public class WheelTrajectory {
	protected final MotionTrajectory motionTrajectoryProfile;
	protected final Wheel wheel;
	protected final double tickTotal;

	public static enum Wheel {
		LEFT, RIGHT;
	}

	public WheelTrajectory(MotionTrajectory motionTrajectoryProfile, Wheel wheel, double tickTotal) {
		this.motionTrajectoryProfile = motionTrajectoryProfile;
		this.wheel = wheel;
		this.tickTotal = tickTotal;
	}

	public double calcPos(double s, MotionTrajectoryPoint lastPoint) {
		return lastPoint.pos + ((calcMaxVel(s) + lastPoint.vel) / 2) * (1 / tickTotal);
	}

	/**
	 * Calculates the velocity (in the eyes of CTRE)
	 * 
	 * @param s
	 * @return
	 */
	public double calcMaxVel(double s) {
		return motionTrajectoryProfile.calcMaxSpeed(s)
			+ (motionTrajectoryProfile.calcMaxAngularVel(s) * (wheel == Wheel.RIGHT ? 1 : -1)) / 2.0;
	}

	public double calcAcc(double s, MotionTrajectoryPoint lastPoint) {
		return (calcMaxVel(s) - lastPoint.vel) / (1 / tickTotal);
	}
	// Interval, Tick, Velocity, Acceleration, Pos
}
