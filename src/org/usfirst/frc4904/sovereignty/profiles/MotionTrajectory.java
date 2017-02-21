package org.usfirst.frc4904.sovereignty.profiles;


import java.util.LinkedList;
import java.util.Map;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.sovereignty.profiles.WheelTrajectory.Wheel;

public class MotionTrajectory {
	protected final SplineGenerator splineGenerator;
	protected final double plantWidth;
	protected final double tickTime, tickTotal;
	protected final WheelTrajectory leftWheel, rightWheel;
	protected final LinkedList<SplineSegment> featureSegments;
	/**
	 * Maps a tick to a left/right-specific segment, and the time that the tick occurs at relative to
	 * the beginning time of the segment.
	 */
	protected final Map<Integer, Tuple<Double, WheelTrajectorySegment>> leftWheelTickMap, rightWheelTickMap;

	public MotionTrajectory(SplineGenerator splineGenerator, double plantWidth, double tickTime, double tickTotal) {
		this.splineGenerator = splineGenerator;
		this.plantWidth = plantWidth;
		this.tickTime = tickTime;
		this.tickTotal = tickTotal;
		// TODO: Update the threshold to reflect a real value.
		featureSegments = splineGenerator.generateFeatureSegments(1000, 0.0);
		leftWheel = new WheelTrajectory(this, featureSegments, Wheel.LEFT, tickTotal, tickTime);
		rightWheel = new WheelTrajectory(this, featureSegments, Wheel.RIGHT, tickTotal, tickTime);
		leftWheelTickMap = leftWheel.generateTickMap();
		rightWheelTickMap = rightWheel.generateTickMap();
	}

	public LinkedList<SplineSegment> generateFeatureSegments(double granularity, double curveThreshold) {
		LinkedList<SplineSegment> featureSegments = new LinkedList<>();
		double lastPercentage = 0.0;
		// Hopefully the curvature is never non-zero at the initial position of the arc.
		double lastCurve = 0.0;
		SplineSegment lastTrajectorySegment = new SplineSegment(0);
		for (double i = 0; i < 1; i += 1 / granularity) {
			double instantCurve = splineGenerator.calcCurvature(i);
			if (Math.abs(lastCurve - instantCurve) > curveThreshold) {
				double curveLen = splineGenerator.calcLength(lastPercentage, i + 1 / granularity, 1000);
				lastPercentage = i;
				lastCurve = instantCurve;
				lastTrajectorySegment.length = curveLen;
				featureSegments.add(lastTrajectorySegment);
				lastTrajectorySegment = new SplineSegment(lastTrajectorySegment.finCurve);
			}
		}
		lastTrajectorySegment.length = splineGenerator.calcLength(lastPercentage, 1, 1000);
		featureSegments.add(lastTrajectorySegment);
		return featureSegments;
	}

	public Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> calcPoint(int tick) {
		Tuple<Double, WheelTrajectorySegment> leftWheelTick = leftWheelTickMap.get(tick);
		Tuple<Double, WheelTrajectorySegment> rightWheelTick = rightWheelTickMap.get(tick);
		return new Tuple<>(leftWheelTick.getY().findSetPoint(leftWheelTick.getX(), tick),
			rightWheelTick.getY().findSetPoint(rightWheelTick.getX(), tick));
	}

	public double calcMaxSpeed(double s) {
		return RobotMap.maxVel / (1 + plantWidth * splineGenerator.calcCurvature(s) / 2);
	}

	public double calcMaxAngularVel(double s) {
		return calcTurning(s, calcMaxSpeed(s)) * plantWidth; // = theta/second * circumference/2pi = distance / second
	}

	protected double calcTurning(double s, double speed) {
		return splineGenerator.calcCurvature(s) * speed;
	}
}