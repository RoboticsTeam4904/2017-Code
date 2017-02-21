package org.usfirst.frc4904.sovereignty.profiles;


import java.util.LinkedList;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.sovereignty.profiles.WheelTrajectory.Wheel;

public class MotionTrajectory {
	protected final SplineGenerator splineGenerator;
	protected final double plantWidth;
	protected final double tickTime, tickTotal;
	protected final WheelTrajectory leftWheel, rightWheel;

	public MotionTrajectory(SplineGenerator splineGenerator, double plantWidth, double tickTime, double tickTotal) {
		this.splineGenerator = splineGenerator;
		this.plantWidth = plantWidth;
		this.tickTime = tickTime;
		this.tickTotal = tickTotal;
		// TODO: Update the threshold to reflect a real value.
		LinkedList<SplineSegment> featureSegments = splineGenerator.generateFeatureSegments(1000, 0.0);
		leftWheel = new WheelTrajectory(this, featureSegments, Wheel.LEFT, tickTotal);
		rightWheel = new WheelTrajectory(this, featureSegments, Wheel.RIGHT, tickTotal);
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
				double curveLen = splineGenerator.calcLength(1000, lastPercentage, i + 1 / granularity);
				lastPercentage = i;
				lastCurve = instantCurve;
				lastTrajectorySegment.length = curveLen;
				featureSegments.add(lastTrajectorySegment);
				lastTrajectorySegment = new SplineSegment(lastTrajectorySegment.finCurve);
			}
		}
		lastTrajectorySegment.length = splineGenerator.calcLength(1000, lastPercentage, 1);
		featureSegments.add(lastTrajectorySegment);
		return featureSegments;
	}

	public double fromTickToS(double t) {
		return t / tickTotal;
	}

	public Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> calcPoint(int t,
		Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> lastPoint) {
		double s = fromTickToS(t);
		MotionTrajectoryPoint leftPoint = new MotionTrajectoryPoint(t, leftWheel.calcPos(s, lastPoint.getX()),
			leftWheel.calcMaxVel(s), leftWheel.calcAcc(s, lastPoint.getX()));
		MotionTrajectoryPoint rightPoint = new MotionTrajectoryPoint(t, rightWheel.calcPos(s, lastPoint.getY()),
			rightWheel.calcMaxVel(s), rightWheel.calcAcc(s, lastPoint.getY()));
		return new Tuple<>(leftPoint, rightPoint);
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