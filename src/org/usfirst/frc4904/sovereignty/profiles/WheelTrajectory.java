package org.usfirst.frc4904.sovereignty.profiles;


import java.util.LinkedList;

public class WheelTrajectory {
	protected final MotionTrajectory motionTrajectoryProfile;
	protected final LinkedList<WheelTrajectorySegment> trajectorySegments;
	protected final Wheel wheel;
	protected final double tickTotal;

	public static enum Wheel {
		LEFT, RIGHT;
	}

	public WheelTrajectory(MotionTrajectory motionTrajectoryProfile, LinkedList<SplineSegment> featureSegments,
		Wheel wheel, double tickTotal) {
		this.motionTrajectoryProfile = motionTrajectoryProfile;
		trajectorySegments = generateBackwardConsistency(generateForwardConsistency(featureSegments));
		this.wheel = wheel;
		this.tickTotal = tickTotal;
	}

	public LinkedList<WheelTrajectorySegment> generateForwardConsistency(LinkedList<SplineSegment> featureSegments) {
		LinkedList<WheelTrajectorySegment> trajectorySegments = new LinkedList<>();
		WheelTrajectorySegment lastSegment = new WheelTrajectorySegment(0, calcMaxVel(0));
		for (int i = 0; i < featureSegments.size(); i++) {
			lastSegment.length = featureSegments.get(i).length;
			lastSegment.finVel = lastSegment.calcReachableEndVel();
			trajectorySegments.add(lastSegment);
			if (i != featureSegments.size() - 1) {
				lastSegment = new WheelTrajectorySegment(lastSegment.finVel, calcMaxVel(featureSegments.get(i).finPercentage));
			}
		}
		return trajectorySegments;
	}

	public LinkedList<WheelTrajectorySegment> generateBackwardConsistency(
		LinkedList<WheelTrajectorySegment> trajectorySegments) {
		for (int i = trajectorySegments.size() - 1; i > 0; i--) {
			trajectorySegments.get(i).initVel = trajectorySegments.get(i).calcReachableStartVel();
		}
		return trajectorySegments;
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
