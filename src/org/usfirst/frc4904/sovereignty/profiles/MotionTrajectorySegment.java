package org.usfirst.frc4904.sovereignty.profiles;


import org.usfirst.frc4904.robot.RobotMap;

public class MotionTrajectorySegment {
	protected final double initVel;
	protected double finVel;
	protected double length;
	protected final double maxVel;
	protected final double maxAccel;
	protected double duration;
	protected double adjustedMaxVel;
	protected double rampUpTime;
	protected double rampDownTime;
	protected double rampUpDistance;
	protected double rampDownDistance;
	protected double cruiseDistance;
	protected double cruiseTime;
	protected boolean partial;

	public MotionTrajectorySegment(double initVel, double finVel,
		double maxVel, double length) {
		this.initVel = initVel;
		this.finVel = finVel;
		this.maxVel = maxVel;
		this.length = length;
		partial = false;
		// adjustedMaxVel = calcAdjustedMaxVel();
		maxAccel = RobotMap.maxAccel;
	}

	public MotionTrajectorySegment(double initVel, double maxVel) {
		this.initVel = initVel;
		this.maxVel = maxVel;
		partial = true;
		maxAccel = RobotMap.maxAccel;
	}

	public double calcAdjustedMaxVel(double distance) {
		return Math.min(maxVel, Math.min(maxReachableVel(length, initVel), maxReachableVel(length - distance, initVel)));
	}

	private double maxReachableVel(double distance, double initVel) {
		return Math.sqrt(2 * maxAccel * distance + initVel * initVel);
	}

	protected double Pos(double t, double initVel, double a) {
		return initVel * t + (a * t * t) / 2;
	}

	protected double Vel(double t, double initVel, double a) {
		return initVel + a * t;
	}

	protected void dividePath() {
		rampUpTime = (adjustedMaxVel - initVel) / maxAccel;
		rampDownTime = (adjustedMaxVel - finVel) / maxAccel;
		rampUpDistance = (adjustedMaxVel * adjustedMaxVel - initVel * initVel)
			/ (2 * maxAccel);
		rampDownDistance = (adjustedMaxVel * adjustedMaxVel - finVel * finVel)
			/ (2 * maxAccel);
		cruiseDistance = length - rampUpDistance - rampDownDistance;
		cruiseTime = cruiseDistance / adjustedMaxVel;
		duration = rampUpTime + rampDownTime + cruiseTime;
	}

	protected MotionTrajectoryPoint findSetPoint(double t, int tick) {
		double pos;
		double vel;
		double accel;
		if (t <= rampUpTime) {
			pos = 0.0;
			vel = initVel;
			accel = maxAccel;
		} else if (t <= rampUpTime + cruiseTime) {
			pos = rampUpDistance;
			vel = adjustedMaxVel;
			accel = 0.0;
			t -= rampUpTime;
		} else {
			pos = rampUpDistance + cruiseDistance;
			vel = adjustedMaxVel;
			accel = -maxAccel;
			t -= rampUpTime + cruiseTime;
		}
		vel = Vel(t, vel, accel);
		pos += Pos(t, vel, accel);
		return new MotionTrajectoryPoint(tick, pos, vel, accel);
	}
}
