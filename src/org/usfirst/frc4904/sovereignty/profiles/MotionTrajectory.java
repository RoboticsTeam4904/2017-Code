package org.usfirst.frc4904.sovereignty.profiles;


import java.util.LinkedList;
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
		leftWheel = new WheelTrajectory(this, Wheel.LEFT, tickTotal);
		rightWheel = new WheelTrajectory(this, Wheel.RIGHT, tickTotal);
	}

	public LinkedList<MotionTrajectorySegment> generateFeatureSegments(double granularity, double curveThreshold) {
		LinkedList<MotionTrajectorySegment> featureSegments = new LinkedList<>();
		double maxVelocity = 0.0; // idk.
		double totalDisplacement = 0.0;
		// Hopefully the curvature is never non-zero at the initial position of the arc.
		double lastCurve = 0.0;
		MotionTrajectorySegment lastTrajectorySegment = new MotionTrajectorySegment(0, maxVelocity);
		for (int i = 0; i < granularity; i++) {
			double instantCurve = splineGenerator.calcCurvature(i / granularity);
			if (Math.abs(lastCurve - instantCurve) > curveThreshold) {
				double instantLen = splineGenerator.calcLength(1000, totalDisplacement, i / granularity);
				totalDisplacement += instantLen;
				lastCurve = instantCurve;
				lastTrajectorySegment.length = instantLen;
				lastTrajectorySegment.finVel = lastTrajectorySegment.calcAdjustedMaxVel(instantLen);
				lastTrajectorySegment.partial = false;
				featureSegments.add(lastTrajectorySegment);
				lastTrajectorySegment = new MotionTrajectorySegment(lastTrajectorySegment.finVel, maxVelocity);
			}
		}
		if (lastTrajectorySegment.partial) {
			lastTrajectorySegment.length = splineGenerator.calcLength(1000, totalDisplacement, 1);
			lastTrajectorySegment.finVel = lastTrajectorySegment.calcAdjustedMaxVel(lastTrajectorySegment.length);
			lastTrajectorySegment.partial = false;
			featureSegments.add(lastTrajectorySegment);
		}
		return featureSegments;
	}

	// public LinkedList<MotionTrajectorySegment> generateSegments(LinkedList<MotionTrajectorySegment> featureSegments)
	public double fromTickToS(double t) {
		return t / tickTotal;
	}

	public Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> calcPoint(int t,
		Tuple<MotionTrajectoryPoint, MotionTrajectoryPoint> lastPoint) {
		double s = fromTickToS(t);
		MotionTrajectoryPoint leftPoint = new MotionTrajectoryPoint(t, leftWheel.calcPos(s, lastPoint.getX()),
			leftWheel.calcSpeed(s), leftWheel.calcAcc(s, lastPoint.getX()));
		MotionTrajectoryPoint rightPoint = new MotionTrajectoryPoint(t, rightWheel.calcPos(s, lastPoint.getY()),
			rightWheel.calcSpeed(s), rightWheel.calcAcc(s, lastPoint.getY()));
		return new Tuple<>(leftPoint, rightPoint);
	}

	public double calcSpeed(double s) {
		return splineGenerator.calcSpeed(s);
	}

	public double calcAngularVel(double s) {
		return calcTurning(s) * plantWidth; // = theta/second * circumference/2pi = distance / second
	}

	protected double calcTurning(double s) {
		return splineGenerator.calcCurvature(s) * calcSpeed(s);
	}
}
