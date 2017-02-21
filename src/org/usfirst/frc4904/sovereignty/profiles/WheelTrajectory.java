package org.usfirst.frc4904.sovereignty.profiles;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WheelTrajectory {
	protected final MotionTrajectory motionTrajectoryProfile;
	protected LinkedList<WheelTrajectorySegment> trajectorySegments;
	protected final Wheel wheel;
	protected final double tickTotal;
	protected final double tickTime;

	public static enum Wheel {
		LEFT, RIGHT;
	}

	public WheelTrajectory(MotionTrajectory motionTrajectoryProfile, LinkedList<SplineSegment> featureSegments,
		Wheel wheel, double tickTotal, double tickTime) {
		this.motionTrajectoryProfile = motionTrajectoryProfile;
		trajectorySegments = finalizeSegments(generateBackwardConsistency(generateForwardConsistency(featureSegments)));
		this.wheel = wheel;
		this.tickTotal = tickTotal;
		this.tickTime = tickTime;
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

	public LinkedList<WheelTrajectorySegment> finalizeSegments(LinkedList<WheelTrajectorySegment> trajectorySegments) {
		double timePassed = 0;
		double distanceTraveled = 0;
		for (WheelTrajectorySegment segment : trajectorySegments) {
			segment.dividePath();
			segment.context = new AbsoluteSegmentContext(timePassed, distanceTraveled);
			timePassed += segment.duration;
			distanceTraveled += segment.length;
		}
		return trajectorySegments;
	}

	public Map<Integer, Tuple<Double, WheelTrajectorySegment>> generateTickMap() {
		HashMap<Integer, Tuple<Double, WheelTrajectorySegment>> map = new HashMap<>();
		int currentSegmentIndex = 0;
		double timeOverSegment = 0.0;
		for (int i = 0; i < tickTotal; i++) {
			double timeDiff = (timeOverSegment += tickTime) - trajectorySegments.get(currentSegmentIndex).duration;
			if (timeDiff > 0) {
				timeOverSegment = timeDiff;
				currentSegmentIndex++;
			}
			map.put(i, new Tuple<Double, WheelTrajectorySegment>(timeOverSegment, trajectorySegments.get(currentSegmentIndex)));
		}
		return map;
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
