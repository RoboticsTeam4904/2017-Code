package org.usfirst.frc4904.sovereignty.profiles;


import org.usfirst.frc4904.robot.RobotMap;

public class SegmentProfiler {
	protected final double initVel;
	protected final double finVel;
	protected final double distance;
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

	public SegmentProfiler(MotionTrajectorySegment segment) {
		initVel = segment.initVel;
		finVel = segment.finVel;
		distance = segment.length;
		maxVel = segment.maxVel;
		adjustedMaxVel = calcAdjustedMaxVel();
		maxAccel = RobotMap.maxAccel;
	}

	double x(double t, double v0, double a) {
		return v0 * t + (a * t * t) / 2;
	}

	double v(double t, double v0, double a) {
		return v0 + a * t;
	}

	double calcMaxReachableVel() {
		return Math.sqrt(maxAccel * distance + (initVel * initVel + finVel * finVel) / 2);
	}

	double calcAdjustedMaxVel() {
		return Math.min(maxVel, calcMaxReachableVel());
	}

	void dividePath() {
		rampUpTime = (adjustedMaxVel - initVel) / maxAccel;
		rampDownTime = (adjustedMaxVel - finVel) / maxAccel;
		rampUpDistance = (adjustedMaxVel * adjustedMaxVel - initVel * initVel)
			/ (2 * maxAccel);
		rampDownDistance = (adjustedMaxVel * adjustedMaxVel - finVel * finVel)
			/ (2 * maxAccel);
		cruiseDistance = distance - rampUpDistance - rampDownDistance;
		cruiseTime = cruiseDistance / adjustedMaxVel;
		duration = rampUpTime + rampDownTime + cruiseTime;
	}

	MotionTrajectoryPoint findSetPoint(double t, int tick) {
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
		vel = v(t, vel, accel);
		pos += x(t, vel, accel);
		return new MotionTrajectoryPoint(tick, pos, vel, accel);
	}
}
